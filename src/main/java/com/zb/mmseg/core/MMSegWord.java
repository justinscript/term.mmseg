/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.core;

/**
 * 类似 lucene 的 token
 * 
 * @author zxc Sep 3, 2014 2:45:49 PM
 */
public class MMSegWord {

    public static final String TYPE_WORD            = "word";
    public static final String TYPE_LETTER          = "letter";
    /** 字母开头的"字母或数字" */
    public static final String TYPE_LETTER_OR_DIGIT = "letter_or_digit";
    public static final String TYPE_DIGIT           = "digit";
    /** 数字开头的"字母或数字" */
    public static final String TYPE_DIGIT_OR_LETTER = "digit_or_letter";
    public static final String TYPE_LETTER_NUMBER   = "letter_number";
    public static final String TYPE_OTHER_NUMBER    = "other_number";

    private int                degree               = -1;
    private int                startOffset;

    private char[]             sen;
    private int                offset;
    private int                len;

    private String             type                 = TYPE_WORD;        // 类似 lucene token 的 type

    /**
     * @param startOffset word 在整个文本中的偏移位置
     */
    public MMSegWord(char[] word, int startOffset) {
        super();
        this.sen = word;
        this.startOffset = startOffset;
        offset = 0;
        len = word.length;
    }

    /**
     * @param startOffset word 在整个文本中的偏移位置
     */
    public MMSegWord(char[] word, int startOffset, String wordType) {
        this(word, startOffset);
        this.type = wordType;
    }

    /**
     * sen[offset] 开始的 len 个字符才是此 word
     * 
     * @param senStartOffset sen 在整个文本中的偏移位置
     * @param offset 词在 sen 的偏移位置
     * @param len 词长
     */
    public MMSegWord(char[] sen, int senStartOffset, int offset, int len) {
        super();
        this.sen = sen;
        this.startOffset = senStartOffset;
        this.offset = offset;
        this.len = len;
    }

    public String getString() {
        return new String(getSen(), getWordOffset(), getLength());
    }

    public String toString() {
        return getString();
    }

    /**
     * 词在 char[] sen 的偏移位置
     * 
     * @see #getSen()
     */
    public int getWordOffset() {
        return offset;
    }

    public int getLength() {
        return len;
    }

    public char[] getSen() {
        return sen;
    }

    /** 此 word 在整个文本中的偏移位置 */
    public int getStartOffset() {
        return startOffset + offset;
    }

    public int getEndOffset() {
        return getStartOffset() + getLength();
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
