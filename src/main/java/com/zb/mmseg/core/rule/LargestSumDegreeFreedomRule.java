/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core.rule;

import com.zb.mmseg.core.Chunk;

/**
 * Largest Sum of Degree of Morphemic Freedom of One-Character.
 * <p/>
 * 各单字词词频的对数之和*100
 * 
 * @see http://technology.chtsai.org/mmseg/
 * @author zxc Sep 3, 2014 2:45:49 PM
 */
public class LargestSumDegreeFreedomRule extends Rule {

    private int largestSumDegree = Integer.MIN_VALUE;

    @Override
    public void addChunk(Chunk chunk) {
        if (chunk.getSumDegree() >= largestSumDegree) {
            largestSumDegree = chunk.getSumDegree();
            super.addChunk(chunk);
        }
    }

    @Override
    public void reset() {
        largestSumDegree = Integer.MIN_VALUE;
        super.reset();
    }

    @Override
    protected boolean isRemove(Chunk chunk) {

        return chunk.getSumDegree() < largestSumDegree;
    }
}
