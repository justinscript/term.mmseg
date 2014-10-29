/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core.rule;

import com.zb.mmseg.core.Chunk;

/**
 * Smallest Variance of Word Lengths.
 * <p/>
 * 标准差的平方
 * 
 * @see http://technology.chtsai.org/mmseg/
 * @author zxc Sep 3, 2014 2:45:49 PM
 */
public class SmallestVarianceRule extends Rule {

    private double smallestVariance = Double.MAX_VALUE;

    @Override
    public void addChunk(Chunk chunk) {
        if (chunk.getVariance() <= smallestVariance) {
            smallestVariance = chunk.getVariance();
            super.addChunk(chunk);
        }
    }

    @Override
    public void reset() {
        smallestVariance = Double.MAX_VALUE;
        super.reset();
    }

    @Override
    protected boolean isRemove(Chunk chunk) {

        return chunk.getVariance() > smallestVariance;
    }
}
