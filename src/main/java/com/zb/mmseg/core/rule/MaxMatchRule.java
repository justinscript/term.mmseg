/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core.rule;

import com.zb.mmseg.core.Chunk;

/**
 * Maximum Matching.
 * <p/>
 * chuck中各个词的长度之和
 * 
 * @see http://technology.chtsai.org/mmseg/
 * @author zxc Sep 3, 2014 2:45:49 PM
 */
public class MaxMatchRule extends Rule {

    private int maxLen;

    public void addChunk(Chunk chunk) {
        if (chunk.getLen() >= maxLen) {
            maxLen = chunk.getLen();
            super.addChunk(chunk);
        }
    }

    @Override
    protected boolean isRemove(Chunk chunk) {

        return chunk.getLen() < maxLen;
    }

    public void reset() {
        maxLen = 0;
        super.reset();
    }
}
