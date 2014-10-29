/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.analysis;

import java.io.File;

import com.zb.mmseg.core.MMSegDictionary;
import com.zb.mmseg.core.MaxWordSeg;
import com.zb.mmseg.core.Seg;

/**
 * 最多分词方式.
 * 
 * @author zxc Sep 3, 2014 2:39:35 PM
 */
public class MaxWordAnalyzer extends MMSegAnalyzer {

    public MaxWordAnalyzer() {
        super();
    }

    public MaxWordAnalyzer(String path) {
        super(path);
    }

    public MaxWordAnalyzer(MMSegDictionary dic) {
        super(dic);
    }

    public MaxWordAnalyzer(File path) {
        super(path);
    }

    protected Seg newSeg() {
        return new MaxWordSeg(dic);
    }
}
