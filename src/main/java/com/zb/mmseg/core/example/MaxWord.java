/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core.example;

import java.io.IOException;

import com.zb.mmseg.core.MaxWordSeg;
import com.zb.mmseg.core.Seg;

/**
 * @author zxc Sep 3, 2014 2:45:49 PM
 */
public class MaxWord extends Complex {

    protected Seg getSeg() {

        return new MaxWordSeg(dic);
    }

    public static void main(String[] args) throws IOException {
        new MaxWord().run(args);
    }
}
