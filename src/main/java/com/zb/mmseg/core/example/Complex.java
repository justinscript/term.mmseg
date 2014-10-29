/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import com.zb.mmseg.core.ComplexSeg;
import com.zb.mmseg.core.MMSegDictionary;
import com.zb.mmseg.core.MMSeg;
import com.zb.mmseg.core.Seg;
import com.zb.mmseg.core.MMSegWord;

/**
 * @author zxc Sep 3, 2014 2:45:56 PM
 */
public class Complex {

    protected MMSegDictionary dic;

    public Complex() {
        dic = MMSegDictionary.getInstance();
    }

    protected Seg getSeg() {
        return new ComplexSeg(dic);
    }

    public String segWords(Reader input, String wordSpilt) throws IOException {
        StringBuilder sb = new StringBuilder();
        Seg seg = getSeg(); // 取得不同的分词具体算法
        MMSeg mmSeg = new MMSeg(input, seg);
        MMSegWord word = null;
        boolean first = true;
        while ((word = mmSeg.next()) != null) {
            if (!first) {
                sb.append(wordSpilt);
            }
            String w = word.getString();
            sb.append(w);
            first = false;

        }
        return sb.toString();
    }

    public String segWords(String txt, String wordSpilt) throws IOException {
        return segWords(new StringReader(txt), wordSpilt);
    }

    private void printlnHelp() {
        System.out.println("\n\t-- 说明: 输入 QUIT 或 EXIT 退出");
        System.out.print("\nmmseg-" + this.getClass().getSimpleName().toLowerCase() + ">");
    }

    protected void run(String[] args) throws IOException {
        String txt = "京华时报２００８年1月23日报道 昨天，受一股来自中西伯利亚的强冷空气影响，本市出现大风降温天气，白天最高气温只有零下7摄氏度，同时伴有6到7级的偏北风。";

        if (args.length > 0) {
            txt = args[0];
        }

        System.out.println(segWords(txt, " | "));
        printlnHelp();
        String inputStr = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((inputStr = br.readLine()) != null) {
            if (inputStr.equals("QUIT") || inputStr.equals("EXIT")) {
                System.exit(0);
            } else if ("".equals(inputStr)) {
                printlnHelp();
            } else {
                // System.out.println(inputStr);
                System.out.println(segWords(inputStr, " | ")); // 分词
                System.out.print("\nmmseg-" + this.getClass().getSimpleName().toLowerCase() + ">");
            }
        }
    }

    public static void main(String[] args) throws IOException {

        new Complex().run(args);
    }
}
