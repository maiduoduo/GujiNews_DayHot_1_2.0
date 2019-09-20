/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.cnews.guji.smart.common.db;
import android.content.Context;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.api.ApiConstants;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.common.bean.NewsChannelTable;
import com.cnews.guji.smart.common.bean.ProfileCareBean;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.model.ProfileCareModel;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 个人数据分类获取
 */
public class ProfileCareManager<S> {

    /**
     * 加载个人数据
     * @return
     */
    public static ProfileCareBean loadProfileCareData() {
        Type type = new TypeToken<ProfileCareBean>() {}.getType();
        return (ProfileCareBean) getMultiIndexJsonData(BaseApplication.getAppContext(), NewsConstant.ASSET_PROFILE_CARE, type);
    }

    /**
     * 解析json
     */
    private static ProfileCareBean getMultiIndexJsonData(Context context, final String fileName, Type type) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
//        Type type = new TypeToken<classa>() {
//        }.getType();
        return gson.fromJson(json, type);
    }


    /**
     * 解析txt文本
     * @param clazz
     * @param fileName
     * @return
     */
    private S getMultiIndexData(Context context,final Class<S> clazz , final String fileName) {
        InputStream is = null;
        S s = null;
        try {
            is = context.getAssets().open(fileName);
            String text = FileUtils.readTextFromFile(is);
            Gson gson = new Gson();
            s = gson.fromJson(text, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;

    }
}
