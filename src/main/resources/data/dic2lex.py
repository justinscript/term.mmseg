# -*- coding: utf-8 -*-
#!/usr/bin/python

### by zxc

import os, sys, codecs, re, shutil
import collections

DIC_LEX_FILE_M = {
    'administrative.dic': 'lex-admin.lex',
    'words-appellation.dic': 'lex-main.lex',
    'words-brand.dic': 'lex-main.lex',
    'words-company.dic': 'lex-company.lex',
    'words-comupter-science.dic': 'lex-main.lex',
    'words-contemporary-words.dic': 'lex-main.lex',
    'words-festival.dic': 'lex-festival.lex',
    'words-language.dic': 'lex-lang.lex',
    'words-name-foreign.dic': 'lex-flname.lex',
    'words-nation.dic': 'lex-nation.lex',
    'words-org-domestic.dic': 'lex-main.lex',
    'words-org-foreign.dic': 'lex-org.lex',
    'words-prop.dic': 'lex-main.lex',
    'words-star-domestic.dic': 'lex-main.lex',
    'words-star-foreign.dic': 'lex-main.lex',
    'words-taobao.dic': 'lex-main.lex',
    'words-t-base.dic': 'lex-main.lex',
    'words-x-confucian-family-name.dic': 'lex-lname.lex',
    'words-x-for-combinatorics.dic': 'lex-ecmixed.lex',
    'words-x-noise-charactor.dic': 'lex-stopword.lex',
    'words-x-noise-word.dic': 'lex-main.lex',
    'words-x-unit.dic': 'lex-units.lex',
}
DIC_FILE_PATH = '%s/workspace/yue.fbiz.segdic' % os.getenv("HOME")
LEX_FILE_PATH = '%s/workspace/yue.fbiz.jcsegdic' % os.getenv("HOME")
LEX_ORIGIN_DIR_PATH = 'jcseglexicon'

EN_PUN_MIX_WORD_R = re.compile(ur'^[\u0041-\u005A\u0061-\u007A\u0030-\u0039]+[\u0021-\u00BB\u2010-\u2642\u3001-\u301E]+$', flags=re.UNICODE)
EN_CN_MIX_WORD_R = re.compile(ur'^[\u0041-\u005A\u0061-\u007A\u0030-\u0039]+[^\u0041-\u005A\u0061-\u007A\u0030-\u0039]+$', flags=re.UNICODE)
CN_EN_MIX_WORD_R = re.compile(ur'^[\u4E00-\u9FA5]+[^\u4E00-\u9FA5]+$', flags=re.UNICODE)

EN_CN_EN_MIX_WORD_R =  re.compile(ur'^[\u0041-\u005A\u0061-\u007A\u0030-\u0039]+[^\u0041-\u005A\u0061-\u007A\u0030-\u0039]+[\u0041-\u005A\u0061-\u007A\u0030-\u0039]+$', flags=re.UNICODE)
CN_EN_CN_MIX_WORD_R = re.compile(ur'^[\u4E00-\u9FA5]+[^\u4E00-\u9FA5]+[\u4E00-\u9FA5]+$', flags=re.UNICODE)

def _append_words_to_file(words, filepath):
    print words
    print filepath
    with codecs.open(filepath, encoding='utf-8', mode='a') as writeF:
       writeF.write(''.join(words))

if __name__ == '__main__':
    lex_uniqline_dic = {}
    for origin_lex_filename in os.listdir(DIC_FILE_PATH + '/' + LEX_ORIGIN_DIR_PATH):
        abs_origin_lex_filepath = DIC_FILE_PATH + '/' + LEX_ORIGIN_DIR_PATH + '/' + origin_lex_filename
        if not os.path.isfile(abs_origin_lex_filepath) or not re.match('.+\.lex$', abs_origin_lex_filepath):
            continue
        abs_desc_lex_filepath = LEX_FILE_PATH + '/' + origin_lex_filename
        shutil.copyfile(abs_origin_lex_filepath, abs_desc_lex_filepath)
        print 'cp %s %s' % (abs_origin_lex_filepath, abs_desc_lex_filepath)
        lex_uniqline = {}; lex_uniqline_dic[origin_lex_filename] = lex_uniqline
        with codecs.open(abs_desc_lex_filepath, encoding='utf-8') as readF:
            for line in readF:
                unicode_line = unicode(line.strip())
                unicode_line = unicode_line.split('/')[0]
                if EN_CN_EN_MIX_WORD_R.match(unicode_line):
                    print unicode_line
                if CN_EN_CN_MIX_WORD_R.match(unicode_line):
                    print unicode_line
                lex_uniqline[unicode_line] = 1
    print '================================'
    first_time_append = {}
    en_pun_mix_word_dict = collections.OrderedDict(); en_cn_mix_word_dict = collections.OrderedDict(); cn_en_mix_word_dict = collections.OrderedDict()
    for origin_dic_filename in os.listdir(DIC_FILE_PATH):
        abs_origin_dic_filepath = DIC_FILE_PATH + '/' + origin_dic_filename
        if not os.path.isfile(abs_origin_dic_filepath) or not re.match('.+\.dic$', abs_origin_dic_filepath):
            continue
        desc_lex_filename = 'lex-admin.lex'
        if origin_dic_filename in DIC_LEX_FILE_M:
            desc_lex_filename = DIC_LEX_FILE_M[origin_dic_filename]
        abs_desc_lex_filepath = LEX_FILE_PATH + '/' + desc_lex_filename
        dic_lines = []
        with codecs.open(abs_origin_dic_filepath, encoding='utf-8') as readF:
            for line in readF:
                unicode_line = unicode(line.strip())
                unicode_line = unicode_line.split('/')[0]
		if unicode_line.startswith('#'):
            		continue
                if EN_PUN_MIX_WORD_R.match(unicode_line):
                    if unicode_line not in en_pun_mix_word_dict and unicode_line not in lex_uniqline_dic['lex-en-pun.lex']:
                        en_pun_mix_word_dict[line] = 1
                    continue
                if EN_CN_MIX_WORD_R.match(unicode_line):
                    unicode_line = unicode_line.lower()
                    if unicode_line not in en_cn_mix_word_dict and unicode_line not in lex_uniqline_dic['lex-ecmixed.lex']:
                        en_cn_mix_word_dict[line.lower()] = 1
                    continue
                if CN_EN_MIX_WORD_R.match(unicode_line):
                    if unicode_line not in cn_en_mix_word_dict and unicode_line not in lex_uniqline_dic['lex-cemixed.lex']:
                        cn_en_mix_word_dict[line] = 1
                    continue
                if EN_CN_EN_MIX_WORD_R.match(unicode_line):
                    print unicode_line
                    print u'英文中文英文混合的词组'
                    continue
                if CN_EN_CN_MIX_WORD_R.match(unicode_line):
                    print unicode_line
                    print u'中文英文中文混合的词组'
                    continue
                dic_lines.append(line)
        with codecs.open(abs_desc_lex_filepath, encoding='utf-8', mode='a') as writeF:
            if desc_lex_filename not in first_time_append:
                writeF.write('\n### new lex from dic ###\n')
                first_time_append[desc_lex_filename] = 1
            for line in dic_lines:
                line = line.replace(u'\ufeff', '')
                unicode_line = unicode(line.strip())
                unicode_line = unicode_line.split('/')[0]
                if unicode_line == '' or unicode_line == '\n':
                    continue
                if not line.endswith('\n'):
                    line = line + '\n'
                if unicode_line != '' and unicode_line not in lex_uniqline_dic[desc_lex_filename]:
                    writeF.write(line)
                    lex_uniqline_dic[desc_lex_filename][line] = 1
            writeF.flush()
    _append_words_to_file(en_pun_mix_word_dict.keys(), LEX_FILE_PATH + '/lex-en-pun.lex')
    _append_words_to_file(en_cn_mix_word_dict.keys(), LEX_FILE_PATH + '/lex-ecmixed.lex')
    _append_words_to_file(cn_en_mix_word_dict.keys(), LEX_FILE_PATH + '/lex-cemixed.lex')






