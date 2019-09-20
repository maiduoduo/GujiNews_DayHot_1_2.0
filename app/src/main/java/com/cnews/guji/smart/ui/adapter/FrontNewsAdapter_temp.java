package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cnews.guji.smart.R;


/**
 * Created by dingcl.
 */
public class FrontNewsAdapter_temp extends RecyclerView.Adapter<FrontNewsAdapter_temp.MyViewHolder> {
    private Context context;
    public FrontNewsAdapter_temp(Context context){
        this.context=context;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.fragment_front_news_item, parent, false));
//        MyViewHolder default_img_guji = new MyViewHolder(LayoutInflater.from(context).
//                inflate(R.layout.item_fragment_front_news, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText("这是第" + position + "个文本");
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,holder.tv.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
