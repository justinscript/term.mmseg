/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.analysis;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import com.zb.mmseg.core.MMSeg;
import com.zb.mmseg.core.Seg;
import com.zb.mmseg.core.MMSegWord;

/**
 * @author zxc Sep 3, 2014 2:39:35 PM
 */
public class MMSegTokenizer extends Tokenizer {

    private MMSeg             mmSeg;

    private CharTermAttribute termAtt;
    private OffsetAttribute   offsetAtt;
    private TypeAttribute     typeAtt;

    public MMSegTokenizer(Seg seg, Reader input) {
        super(input);
        mmSeg = new MMSeg(input, seg);

        termAtt = addAttribute(CharTermAttribute.class);
        offsetAtt = addAttribute(OffsetAttribute.class);
        typeAtt = addAttribute(TypeAttribute.class);
    }

    public void reset() throws IOException {
        // lucene 4.0
        // org.apache.lucene.analysis.Tokenizer.setReader(Reader)
        // setReader 自动被调用, input 自动被设置。
        super.reset();
        mmSeg.reset(input);
    }

    /*
     * //lucene 2.9 以下 public Token next(Token reusableToken) throws IOException { Token token = null; Word word =
     * mmSeg.next(); if(word != null) { //lucene 2.3 reusableToken.clear(); reusableToken.setTermBuffer(word.getSen(),
     * word.getWordOffset(), word.getLength()); reusableToken.setStartOffset(word.getStartOffset());
     * reusableToken.setEndOffset(word.getEndOffset()); reusableToken.setType(word.getType()); token = reusableToken;
     * //lucene 2.4 //token = reusableToken.reinit(word.getSen(), word.getWordOffset(), word.getLength(),
     * word.getStartOffset(), word.getEndOffset(), word.getType()); } return token; }
     */

    // lucene 2.9/3.0
    @Override
    public final boolean incrementToken() throws IOException {
        clearAttributes();

        if (mmSeg.getReaderStatus() == 1) {
            mmSeg.reset(this.input);
            mmSeg.setReaderStatus(0);
        }
        MMSegWord word = mmSeg.next();
        if (word != null) {
            // lucene 3.0
            // termAtt.setTermBuffer(word.getSen(), word.getWordOffset(), word.getLength());
            // lucene 3.1
            termAtt.copyBuffer(word.getSen(), word.getWordOffset(), word.getLength());
            offsetAtt.setOffset(word.getStartOffset(), word.getEndOffset());
            typeAtt.setType(word.getType());
            return true;
        } else {
            end();
            mmSeg.setReaderStatus(1);
            return false;
        }
    }
}
