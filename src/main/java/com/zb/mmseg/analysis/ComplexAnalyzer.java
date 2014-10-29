/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.analysis;

import java.io.File;

import com.zb.mmseg.core.ComplexSeg;
import com.zb.mmseg.core.MMSegDictionary;
import com.zb.mmseg.core.Seg;

/**
 * mmseg 的 complex analyzer
 * 
 * @author zxc Sep 3, 2014 2:39:35 PM
 */
public class ComplexAnalyzer extends MMSegAnalyzer {

    public ComplexAnalyzer() {
        super();
    }

    public ComplexAnalyzer(String path) {
        super(path);
    }

    public ComplexAnalyzer(MMSegDictionary dic) {
        super(dic);
    }

    public ComplexAnalyzer(File path) {
        super(path);
    }

    protected Seg newSeg() {
        return new ComplexSeg(dic);
    }
}
