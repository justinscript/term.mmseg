/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zxc Sep 3, 2014 2:44:36 PM
 */
public class DictionaryTest {

    private void printMemory() {
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        long max = rt.maxMemory();
        System.out.println(String.format("total=%dk, free=%dk, max=%dk, use=%dk", total / 1024, free / 1024,
                                         max / 1024, (total - free) / 1024));
    }

    @Test
    public void testloadDicMemoryUse() {
        printMemory();
        MMSegDictionary.getInstance();
        printMemory();
    }

    @Test
    public void testloadDic() {
        MMSegDictionary dic = MMSegDictionary.getInstance();
        MMSegDictionary dic2 = MMSegDictionary.getInstance();
        Assert.assertTrue(dic == dic2);

        dic.destroy();
        // reload
        dic2 = MMSegDictionary.getInstance();
        Assert.assertTrue(dic != dic2);
        dic2.destroy();
    }

    @Test
    public void testloadDicByPath() {
        MMSegDictionary dic = MMSegDictionary.getInstance("src");
        MMSegDictionary dic2 = MMSegDictionary.getInstance("./src");
        Assert.assertTrue(dic == dic2);

        Assert.assertFalse(dic.match("自定义词"));

        dic.destroy();
    }

    @Test
    public void testloadMultiDic() {
        MMSegDictionary dic = MMSegDictionary.getInstance();

        Assert.assertTrue(dic.match("自定义词"));
    }

    @Test
    public void testMatch() {
        MMSegDictionary dic = MMSegDictionary.getInstance();

        Assert.assertTrue(dic.match("词典"));

        Assert.assertFalse(dic.match("人个"));
        Assert.assertFalse(dic.match("三个人"));

        Assert.assertFalse(dic.match(""));
        Assert.assertFalse(dic.match("人"));

    }

    @Test
    public void testFileHashCode() throws IOException {
        File f = new File("data");
        File f1 = new File("./data");
        Assert.assertFalse(f.equals(f1));

        f1 = f.getAbsoluteFile();
        Assert.assertFalse(f.equals(f1));

        Assert.assertTrue(f.getCanonicalFile().equals(f1.getCanonicalFile()));

        f1 = new File("data");
        Assert.assertTrue(f.equals(f1));
    }
}
