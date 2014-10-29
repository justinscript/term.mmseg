/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.analysis;

import java.io.File;

import com.zb.mmseg.core.MMSegDictionary;
import com.zb.mmseg.core.Seg;
import com.zb.mmseg.core.SimpleSeg;

/**
 * mmseg 的 simple anlayzer.
 * 
 * @author zxc Sep 3, 2014 2:39:35 PM
 */
public class SimpleAnalyzer extends MMSegAnalyzer {

    public SimpleAnalyzer() {
        super();
    }

    public SimpleAnalyzer(String path) {
        super(path);
    }

    public SimpleAnalyzer(MMSegDictionary dic) {
        super(dic);
    }

    public SimpleAnalyzer(File path) {
        super(path);
    }

    protected Seg newSeg() {
        return new SimpleSeg(dic);
    }
}
