package com.cnews.guji.smart.ui.model.source;

import com.cnews.guji.smart.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 资源整合器
 * @param
 */
public class NewsQuerySourceHelper {

    public static int getSplashGif() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(R.mipmap.splash_ad_b);
        list.add(R.mipmap.gif_cc);
        list.add(R.mipmap.gif_dd);
        list.add(R.mipmap.splash_ad_f);
        list.add(R.mipmap.splash_ad_g);
        list.add(R.mipmap.gif_aa);
        list.add(R.mipmap.gif_bb);
        list.add(R.mipmap.gif_cc);
        list.add(R.mipmap.gif_dd);
        list.add(R.mipmap.gif_ee);
        return list.get(new Random().nextInt(list.size()));
    }
}
