/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.solr;

import java.io.File;

import org.apache.lucene.analysis.util.ResourceLoader;
import org.apache.solr.core.SolrResourceLoader;

import com.zb.mmseg.core.MMSegDictionary;

/**
 * @author zxc Sep 3, 2014 2:40:27 PM
 */
public class MMSegUtils {

    public static MMSegDictionary getDict(String dicPath, ResourceLoader loader) {
        MMSegDictionary dic = null;
        if (dicPath != null) {
            File f = new File(dicPath);
            if (!f.isAbsolute() && loader instanceof SolrResourceLoader) { // 相对目录
                SolrResourceLoader srl = (SolrResourceLoader) loader;
                dicPath = srl.getInstanceDir() + dicPath;
                f = new File(dicPath);
            }

            dic = MMSegDictionary.getInstance(f);
        } else {
            dic = MMSegDictionary.getInstance();
        }
        return dic;
    }
}
