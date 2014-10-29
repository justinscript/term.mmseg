/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core.rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zb.mmseg.core.Chunk;

/**
 * 过虑规则的抽象类。
 * 
 * @author zxc Sep 3, 2014 2:45:49 PM
 */
public abstract class Rule {

    protected List<Chunk> chunks;

    public void addChunks(List<Chunk> chunks) {
        for (Chunk chunk : chunks) {
            addChunk(chunk);
        }
    }

    /**
     * 添加 chunk
     * 
     * @throws NullPointerException, if chunk == null.
     * @author zb 2009-3-16 上午11:34:17
     */
    public void addChunk(Chunk chunk) {
        chunks.add(chunk);
    }

    /**
     * @return 返回规则过虑后的结果。
     * @author zb 2009-3-16 上午11:33:10
     */
    public List<Chunk> remainChunks() {
        for (Iterator<Chunk> it = chunks.iterator(); it.hasNext();) {
            Chunk chunk = it.next();
            if (isRemove(chunk)) {
                it.remove();
            }
        }
        return chunks;
    }

    /**
     * 判断 chunk 是否要删除。
     * 
     * @author zb 2009-3-16 上午11:33:30
     */
    protected abstract boolean isRemove(Chunk chunk);

    public void reset() {
        chunks = new ArrayList<Chunk>();
    }
}
