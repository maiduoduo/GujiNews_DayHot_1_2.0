package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.ProfileCareBean;
import com.cnews.guji.smart.ui.model.ProfileServiceGridBean;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.view.KitGridView;
import com.github.library.BaseMultiItemQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * author：JSYL-DCL on 2019/3/7
 * 关注的Adapter
 */
public class ProfileAdapter extends BaseMultiItemQuickAdapter<ProfileCareBean.Profile_data_list, BaseViewHolder> {

    private Context context;
    public ProfileAdapter(Context context,List<ProfileCareBean.Profile_data_list> data) {
        super(data);
        this.context = context;
        addItemType(ProfileCareBean.Profile_data_list.PROFILE_MY_TOPBAR, R.layout.item_profile_my_top_bar);
        addItemType(ProfileCareBean.Profile_data_list.PROFILE_MY_ORDER_MENU, R.layout.item_profile_my_order);
        addItemType(ProfileCareBean.Profile_data_list.PROFILE_MY_WALLET, R.layout.item_profile_my_wallet);
        addItemType(ProfileCareBean.Profile_data_list.PROFILE_MY_SERVICE, R.layout.item_profile_my_service);
        addItemType(ProfileCareBean.Profile_data_list.PROFILE_CONTACT_US, R.layout.item_profile_my_wallet);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProfileCareBean.Profile_data_list profile_data_list) {
        switch (profile_data_list.getItemType()) {
            case ProfileCareBean.Profile_data_list.PROFILE_MY_TOPBAR:
                break;
            case ProfileCareBean.Profile_data_list.PROFILE_MY_ORDER_MENU:
                bindOrderMenuData(baseViewHolder, profile_data_list);
                break;
            case ProfileCareBean.Profile_data_list.PROFILE_MY_WALLET:
                bindWalletData(baseViewHolder, profile_data_list);
                break;
            case ProfileCareBean.Profile_data_list.PROFILE_MY_SERVICE:
                bindServiceData(baseViewHolder, profile_data_list);
                break;
            case ProfileCareBean.Profile_data_list.PROFILE_CONTACT_US:
                bindContactUsData(baseViewHolder, profile_data_list);
                break;
            default:
                break;
        }
    }


    /**
     * 我的订单
     *
     * @param holder
     * @param data
     */
    private void bindOrderMenuData(BaseViewHolder holder, ProfileCareBean.Profile_data_list data) {
        List<LinearLayout> linearLayouts = new ArrayList<>();
        if (linearLayouts != null)linearLayouts.clear();
        AppCompatImageView civTitle =  holder.getView(R.id.civTitle);
        linearLayouts.add((LinearLayout)holder.getView(R.id.llOrder1));
        linearLayouts.add((LinearLayout)holder.getView(R.id.llOrder2));
        linearLayouts.add((LinearLayout)holder.getView(R.id.llOrder3));
        linearLayouts.add((LinearLayout)holder.getView(R.id.llOrder4));
        linearLayouts.add((LinearLayout)holder.getView(R.id.llOrder5));
        if (data != null && linearLayouts != null){
            ImageLoaderUtils.display(context,civTitle,data.title_url == null ? "" : data.title_url);
            for (int i = 0; i < linearLayouts.size(); i++) {
                AppCompatImageView ico = (AppCompatImageView) ((ViewGroup) linearLayouts.get(i)).getChildAt(0);
                TextView name = (TextView) ((ViewGroup) linearLayouts.get(i)).getChildAt(1);
                name.setText(data.item_list.get(i).below_text == null ? "" : data.item_list.get(i).below_text);
                ImageLoaderUtils.display(context,ico,data.item_list.get(i).above_image.img_url == null ? "" : data.item_list.get(i).above_image.img_url);
                int finalI = i;
                linearLayouts.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUitl.showShort(data.item_list.get(finalI).below_text+"");
                    }
                });
            }
        }
    }

    /**
     * 我的钱包
     * @param holder
     * @param data
     */
    private void bindWalletData(BaseViewHolder holder, ProfileCareBean.Profile_data_list data) {
        AppCompatImageView civTitle =  holder.getView(R.id.civTitle);
        List<LinearLayout> linearLayouts = new ArrayList<>();
        if (linearLayouts != null)linearLayouts.clear();
        linearLayouts.add((LinearLayout)holder.getView(R.id.llWallet1));
        linearLayouts.add((LinearLayout)holder.getView(R.id.llWallet2));
        linearLayouts.add((LinearLayout)holder.getView(R.id.llWallet3));
        linearLayouts.add((LinearLayout)holder.getView(R.id.llWallet4));
        if (data != null && linearLayouts != null){
            ImageLoaderUtils.display(context,civTitle,data.title_url == null ? "" : data.title_url);
            for (int i = 0; i < linearLayouts.size(); i++) {
                TextView num = (TextView) ((ViewGroup) linearLayouts.get(i)).getChildAt(0);
                TextView name = (TextView) ((ViewGroup) linearLayouts.get(i)).getChildAt(1);
                num.setText(data.item_list.get(i).above_text == null ? "" : data.item_list.get(i).above_text);
                name.setText(data.item_list.get(i).below_text == null ? "" : data.item_list.get(i).below_text);
                int finalI = i;
                linearLayouts.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUitl.showShort(data.item_list.get(finalI).below_text+"");
                    }
                });
            }
        }
    }

    /**
     * 我的服务
     *
     * @param holder
     * @param data
     */
    private void bindServiceData(BaseViewHolder holder, ProfileCareBean.Profile_data_list data) {
        List<ProfileServiceGridBean> profileServiceGridBeans = new ArrayList<>();
        if (profileServiceGridBeans != null) profileServiceGridBeans.clear();
        AppCompatImageView civTitle = holder.getView(R.id.civTitle);
        KitGridView mySrviceGrid = holder.getView(R.id.mySrviceGrid);
        if (data != null){
            ImageLoaderUtils.display(context,civTitle,data.title_url == null ? "" : data.title_url);
            if (data.item_list != null){
                for (ProfileCareBean.Profile_data_list.Item_list a : data.item_list) {
                    profileServiceGridBeans.add(new ProfileServiceGridBean(a.above_image.img_url,a.below_text));
                }
            }
            mySrviceGrid.setAdapter(new ProfileServiceGridViewAdapter(context,profileServiceGridBeans));
            mySrviceGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 500:
                            break;
                        default:
                            ToastUitl.showShort(profileServiceGridBeans.get(position).titleName+"");
                            break;
                    }
                }
            });
        }
    }

    /**
     * 联系我们
     *
     * @param holder
     * @param data
     */
    private void bindContactUsData(BaseViewHolder holder, ProfileCareBean.Profile_data_list data) {
    }


}
