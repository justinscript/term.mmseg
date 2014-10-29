/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zb.mmseg.core.example.Simple;

/**
 * @author zxc Sep 3, 2014 2:44:20 PM
 */
public class SimpleSegTest {

    Simple segW;

    @Before
    public void setUp() throws Exception {
        segW = new Simple();
    }

    @Test
    public void testEffect() throws IOException {
        String words = segW.segWords("研究生命起源", "|");
        Assert.assertEquals("研究生|命|起源", words);
    }

    @Test
    public void testEffect1() throws IOException {
        String words = segW.segWords("为首要考虑", "|");
        Assert.assertEquals("为首|要|考虑", words);
    }

    @Test
    public void testEffect2() throws IOException {
        String words = segW.segWords("眼看就要来了", "|");
        Assert.assertEquals("眼看|就要|来|了", words);
    }

    @Test
    public void testEffect3() throws IOException {
        String words = segW.segWords("中西伯利亚", "|");
        Assert.assertEquals("中西|伯|利|亚", words);
    }

    @Test
    public void testEffect4() throws IOException {
        String words = segW.segWords("国际化", "|");
        Assert.assertEquals("国际化", words);
    }

    @Test
    public void testEffect5() throws IOException {
        String words = segW.segWords("化装和服装", "|");
        Assert.assertEquals("化装|和服|装", words);
    }

    @Test
    public void testEffect6() throws IOException {
        String words = segW.segWords("中国人民银行", "|");
        Assert.assertEquals("中国人民银行", words);
    }

    /**
     * 自扩展的词库文件
     */
    @Test
    public void testEffect7() throws IOException {
        String words = segW.segWords("白云山", "|");
        Assert.assertEquals("白云山", words);
    }

    @Test
    public void testUnitEffect() throws IOException {
        String words = segW.segWords("2008年中有很多事情", "|");
        Assert.assertEquals("2008|年|中有|很多|事情", words);
    }

    @Test
    public void testUnitEffect1() throws IOException {
        String words = segW.segWords("20分钟能完成", "|");
        Assert.assertEquals("20|分钟|能|完成", words);
    }
}
