package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.ui.model.ProfileServiceGridBean;
import com.cnews.guji.smart.util.ImageLoaderUtils;

import java.util.List;

/**
 * author：JSYL-DCL on 2019/3/8
 * 关注-我的服务九宫格的数据适配器
 */
public class ProfileServiceGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<ProfileServiceGridBean> resBean;

    public ProfileServiceGridViewAdapter(Context context, List<ProfileServiceGridBean> resBean) {
        super();
        this.context = context;
        this.resBean = resBean;
    }

    @Override
    public int getCount() {
        return resBean.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_prodile_myservice_contentitem,parent,false);
            holder = new ViewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.mServiceImg);
            holder.textView = (TextView)convertView.findViewById(R.id.mServiceTv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        ImageLoaderUtils.display(context,holder.imageView,resBean.get(position).icoUrl == null? "" : resBean.get(position).icoUrl);
        holder.textView.setText(resBean.get(position).titleName == null? "" : resBean.get(position).titleName+"");
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
