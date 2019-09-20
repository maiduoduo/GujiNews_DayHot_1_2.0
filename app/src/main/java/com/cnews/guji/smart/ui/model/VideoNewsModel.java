package com.cnews.guji.smart.ui.model;


import android.content.Context;

import com.cnews.guji.smart.common.bean.VideoNewsBean;
import com.cnews.guji.smart.common.db.VideoCategoryManager;
import com.cnews.guji.smart.ui.contract.VideoNewsContract;

/**
 * 视频数据处理
 * @author JSYL-DCL
 */
public class VideoNewsModel implements VideoNewsContract.Model {

    @Override
    public VideoNewsBean getVideoData(Context context, int flag, int videoCurrentType) {
        return VideoCategoryManager.loadVideoData(context,flag,videoCurrentType);
    }

    @Override
    public VideoNewsBean getVideoWares(Context context, int videoCurrentType) {
        return VideoCategoryManager.loadVideoWares(context);
    }

    @Override
    public VideoNewsBean getVideoMoreWares(Context context, int videoCurrentType) {
        return VideoCategoryManager.loadVideoMoreWares(context,videoCurrentType);
    }
}
