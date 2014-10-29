/*
 * Copyright 2011-2016 Zuoimport java.io.IOException; import java.io.StringReader; import junit.framework.TestCase;
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

/**
 * @author zxc Sep 3, 2014 2:44:06 PM
 */
public class MMSegTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testNext() throws IOException {
        String txt = "";
        txt = "京华时报1月23日报道 昨天，受一股来自中西伯利亚的强冷空气影响，本市出现大风降温天气，白天最高气温只有零下7摄氏度，同时伴有6到7级的偏北风。";
        txt = "研究生命起源";
        txt = "手机电子书    abc   http://www.sjshu.com";
        txt = "Apple 苹果 MacBook Pro MB991CH/A 13.3m寸宽屏笔记本(Ⅱ,⑩)";
        // txt = "２００９年ゥスぁま是中 ＡＢｃｃ国абвгαβγδ首次,我的ⅠⅡⅢ在chenёlbēū全国ㄦ范围ㄚㄞㄢ内①ē②㈠㈩⒈⒑发行地方政府债券，";
        MMSegDictionary dic = MMSegDictionary.getInstance();
        Seg seg = null;
        // seg = new SimpleSeg(dic);
        seg = new ComplexSeg(dic);
        MMSeg mmSeg = new MMSeg(new StringReader(txt), seg);
        MMSegWord word = null;
        System.out.println();
        while ((word = mmSeg.next()) != null) {

            System.out.print(word.getString() + " -> " + word.getStartOffset());
            // offset += word.length;
            System.out.println(", " + word.getEndOffset() + ", " + word.getType());
        }
    }
}
