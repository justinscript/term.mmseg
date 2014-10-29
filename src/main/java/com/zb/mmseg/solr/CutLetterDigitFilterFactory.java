/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.solr;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import com.zb.mmseg.analysis.CutLetterDigitFilter;

/**
 * CutLetterDigitFilter 支持在 solr 上配置用。
 * 
 * @author zxc Sep 3, 2014 2:39:35 PM
 */
public class CutLetterDigitFilterFactory extends TokenFilterFactory {

    public CutLetterDigitFilterFactory(Map<String, String> args) {
        super(args);
    }

    @Override
    public TokenStream create(TokenStream input) {

        return new CutLetterDigitFilter(input);
    }
}
