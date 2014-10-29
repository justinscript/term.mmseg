/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core.rule;

import com.zb.mmseg.core.Chunk;

/**
 * Largest Average Word Length.
 * <p/>
 * 长度(Length)/词数
 * 
 * @see http://technology.chtsai.org/mmseg/
 * @author zxc Sep 3, 2014 2:45:49 PM
 */
public class LargestAvgLenRule extends Rule {

    private double largestAvgLen;

    @Override
    public void addChunk(Chunk chunk) {
        if (chunk.getAvgLen() >= largestAvgLen) {
            largestAvgLen = chunk.getAvgLen();
            super.addChunk(chunk);
        }
    }

    @Override
    protected boolean isRemove(Chunk chunk) {
        return chunk.getAvgLen() < largestAvgLen;
    }

    @Override
    public void reset() {
        largestAvgLen = 0;
        super.reset();
    }
}
