/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.analysis;

import java.io.File;
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;

import com.zb.mmseg.core.MMSegDictionary;
import com.zb.mmseg.core.MaxWordSeg;
import com.zb.mmseg.core.Seg;

/**
 * 默认使用 max-word
 * 
 * @see {@link SimpleAnalyzer}, {@link ComplexAnalyzer}, {@link MaxWordAnalyzer}
 * @author zxc Sep 3, 2014 2:39:35 PM
 */
public class MMSegAnalyzer extends Analyzer {

    protected MMSegDictionary dic;

    /**
     * @see MMSegDictionary#getInstance()
     */
    public MMSegAnalyzer() {
        dic = MMSegDictionary.getInstance();
    }

    /**
     * @param path 词库路径
     * @see MMSegDictionary#getInstance(String)
     */
    public MMSegAnalyzer(String path) {
        dic = MMSegDictionary.getInstance(path);
    }

    /**
     * @param path 词库目录
     * @see MMSegDictionary#getInstance(File)
     */
    public MMSegAnalyzer(File path) {
        dic = MMSegDictionary.getInstance(path);
    }

    public MMSegAnalyzer(MMSegDictionary dic) {
        super();
        this.dic = dic;
    }

    protected Seg newSeg() {
        return new MaxWordSeg(dic);
    }

    public MMSegDictionary getDict() {
        return dic;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        return new TokenStreamComponents(new MMSegTokenizer(newSeg(), reader));
    }
}
