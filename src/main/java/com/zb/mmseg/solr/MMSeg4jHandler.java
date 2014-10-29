/*
 * Copyright 2011-2016 ZuoBian.com All right reserved. This software is the confidential and proprietary information of
 * ZuoBian.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ZuoBian.com.
 */
package com.zb.mmseg.solr;

import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.core.SolrCore;
import org.apache.solr.core.SolrResourceLoader;
import org.apache.solr.handler.RequestHandlerBase;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.util.plugin.SolrCoreAware;

import com.zb.mmseg.core.MMSegDictionary;

/**
 * mmseg 的 solr handler，用于检测词库，查看状态等。
 * 
 * @author zxc Sep 3, 2014 2:39:35 PM
 */
public class MMSeg4jHandler extends RequestHandlerBase implements SolrCoreAware {

    // private File solrHome = null;
    private SolrResourceLoader loader = null;

    public String getDescription() {

        return "";
    }

    public String getSource() {

        return "$URL: http://mmseg.googlecode.com/svn/trunk/src/com/zb/mmseg/solr/MMseg4jHandler.java $";
    }

    public String getSourceId() {

        return "$Revision: 63 $";
    }

    public String getVersion() {

        return "1.8";
    }

    public void handleRequestBody(SolrQueryRequest req, SolrQueryResponse rsp) throws Exception {
        rsp.setHttpCaching(false);
        final SolrParams solrParams = req.getParams();

        String dicPath = solrParams.get("dicPath");
        MMSegDictionary dict = MMSegUtils.getDict(dicPath, loader);

        NamedList<Object> result = new NamedList<Object>();
        result.add("dicPath", dict.getDicPath().toURI());

        boolean check = solrParams.getBool("check", false); // 仅仅用于检测词库是否有变化
        // 用于尝试加载词库，有此参数, check 参数可以省略。
        boolean reload = solrParams.getBool("reload", false);

        check |= reload;

        boolean changed = false;
        boolean reloaded = false;
        if (check) {
            changed = dict.wordsFileIsChange();
            result.add("changed", changed);
        }
        if (changed && reload) {
            reloaded = dict.reload();
            result.add("reloaded", reloaded);
        }
        rsp.add("result", result);
    }

    public void inform(SolrCore core) {
        loader = core.getResourceLoader();
        // solrHome = new File(loader.getInstanceDir());
    }
}
