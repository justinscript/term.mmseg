/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core;

/**
 * 正向最大匹配的分词方式.
 * 
 * @author zxc Sep 3, 2014 2:45:49 PM
 */
public class SimpleSeg extends Seg {

    public SimpleSeg(MMSegDictionary dic) {
        super(dic);
    }

    public Chunk seg(Sentence sen) {
        Chunk chunk = new Chunk();
        char[] chs = sen.getText();
        for (int k = 0; k < 3 && !sen.isFinish(); k++) {
            int offset = sen.getOffset();
            int maxLen = 0;

            // 有了 key tree 的支持可以从头开始 max match
            maxLen = dic.maxMatch(chs, offset);

            chunk.words[k] = new MMSegWord(chs, sen.getStartOffset(), offset, maxLen + 1);

            offset += maxLen + 1;
            sen.setOffset(offset);
        }
        return chunk;
    }
}
