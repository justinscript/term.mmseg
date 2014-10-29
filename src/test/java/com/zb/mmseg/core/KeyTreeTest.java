/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core;

import junit.framework.TestCase;

import com.zb.mmseg.core.CharNode.KeyTree;

/**
 * @author zxc Sep 3, 2014 2:44:28 PM
 */
public class KeyTreeTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testMatch() {
        char[] w = "为什么".toCharArray();
        KeyTree kt = new KeyTree();
        kt.add(w);
        assertTrue(kt.match(w, 0, w.length));
        assertFalse(kt.match(w, 0, 2));
        assertFalse(kt.match("怎么样".toCharArray(), 0, 3));

        w = "国人民银行".toCharArray();
        kt.add(w);
        int tailLen = kt.maxMatch("中国人民银行".toCharArray(), 1);
        assertEquals(tailLen, w.length);
    }

    public void testMatch2() {
        MMSegDictionary dic = MMSegDictionary.getInstance();
        int tailLen = dic.maxMatch("中国人民银行".toCharArray(), 0);
        assertEquals(tailLen, 5);
    }
}
