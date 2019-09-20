package com.cnews.guji.smart.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.helper.glide.GlideRoundTransformUtil;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.default_img_guji)
                .error(R.drawable.default_img_guji)
                .crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, File url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.default_img_guji)
                .error(R.drawable.default_img_guji)
                .crossFade().into(imageView);
    }
    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_img_guji)
                .error(R.drawable.default_img_guji)
                .thumbnail(0.5f)
                .into(imageView);
    }
    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_img_guji)
                .error(R.drawable.default_img_guji)
                .into(imageView);
    }
    public static void display(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.default_img_guji)
                .error(R.drawable.default_img_guji)
                .crossFade().into(imageView);
    }
    public static void displayRound(Context context,ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.default_img_guji)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }
    public static void displayRound(Context context,ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.default_img_guji)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }


    public static void displayGlideGif(Context context,ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(resId)
                .asGif()//加载动态图片，若现有图片为非gif图片，则直接加载错误占位图。
                .placeholder(R.drawable.default_img_guji)
                .error(R.drawable.default_img_guji)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }


//    int mFilterColor = ContextCompat.getColor(mContext,R.color.black);
//        int blue = Color.blue(mFilterColor);
//        int green = Color.green(mFilterColor);
//        int red = Color.red(mFilterColor);
//        float[] cm = new float[]{
//                1, 0, 0, 0, red,// 红色值
//                0, 1, 0, 0, green,// 绿色值
//                0, 0, 1, 0, blue,// 蓝色值
//                0, 0, 0, 5, 1 // 透明度
//        };
//        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
//        imgTop.setColorFilter(filter);//设置图标的颜色

    /**
     * SimpleDraweeView 加载本地GIF
     */
    public static void displayGif(int drawableRes, SimpleDraweeView view) {
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(drawableRes))
                .build();
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        if (view == null)return;
        view.setController(draweeController);
    }

}