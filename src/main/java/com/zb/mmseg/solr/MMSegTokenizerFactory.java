/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.solr;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.ResourceLoader;
import org.apache.lucene.analysis.util.ResourceLoaderAware;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeSource.AttributeFactory;

import com.zb.mmseg.analysis.MMSegTokenizer;
import com.zb.mmseg.core.ComplexSeg;
import com.zb.mmseg.core.MMSegDictionary;
import com.zb.mmseg.core.MaxWordSeg;
import com.zb.mmseg.core.Seg;
import com.zb.mmseg.core.SimpleSeg;

/**
 * @author zxc Sep 3, 2014 2:40:14 PM
 */
public class MMSegTokenizerFactory extends TokenizerFactory implements ResourceLoaderAware {

    static final Logger                 log            = Logger.getLogger(MMSegTokenizerFactory.class.getName());
    /* 线程内共享 */
    private ThreadLocal<MMSegTokenizer> tokenizerLocal = new ThreadLocal<MMSegTokenizer>();
    private MMSegDictionary                  dic            = null;

    public MMSegTokenizerFactory(Map<String, String> args) {
        super(args);
    }

    private Seg newSeg(Map<String, String> args) {
        Seg seg = null;
        log.info("create new Seg ...");
        // default max-word
        String mode = args.get("mode");
        if ("simple".equals(mode)) {
            log.info("use simple mode");
            seg = new SimpleSeg(dic);
        } else if ("complex".equals(mode)) {
            log.info("use complex mode");
            seg = new ComplexSeg(dic);
        } else {
            log.info("use max-word mode");
            seg = new MaxWordSeg(dic);
        }
        return seg;
    }

    @Override
    public Tokenizer create(AttributeFactory factory, Reader input) {
        MMSegTokenizer tokenizer = tokenizerLocal.get();
        if (tokenizer == null) {
            tokenizer = newTokenizer(input);
        } else {
            try {
                tokenizer.setReader(input);
            } catch (IOException e) {
                tokenizer = newTokenizer(input);
                log.info("MMSegTokenizer.reset i/o error by:" + e.getMessage());
            }
        }

        return tokenizer;
    }

    private MMSegTokenizer newTokenizer(Reader input) {
        MMSegTokenizer tokenizer = new MMSegTokenizer(newSeg(getOriginalArgs()), input);
        tokenizerLocal.set(tokenizer);
        return tokenizer;
    }

    @Override
    public void inform(ResourceLoader loader) {
        String dicPath = getOriginalArgs().get("dicPath");

        dic = MMSegUtils.getDict(dicPath, loader);

        log.info("dic load... in=" + dic.getDicPath().toURI());
    }
}
