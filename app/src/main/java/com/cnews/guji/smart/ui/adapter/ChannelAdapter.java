package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.bean.NewsChannelTable;
import com.cnews.guji.smart.event.ChannelItemMoveEvent;
import com.cnews.guji.smart.helper.ItemDragHelperCallback;
import com.cnews.guji.smart.helper.ViewHolderHelper;
import com.cnews.guji.smart.listener.OnNoDoubleClickListener;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.subjects.Subject;

/**
 * 新闻频道
 */
public class ChannelAdapter  extends CommonRecycleViewAdapter<NewsChannelTable>implements
        ItemDragHelperCallback.OnItemMoveListener{

    private ItemDragHelperCallback mItemDragHelperCallback;
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setItemDragHelperCallback(ItemDragHelperCallback itemDragHelperCallback) {
        mItemDragHelperCallback = itemDragHelperCallback;
    }
    public ChannelAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, NewsChannelTable newsChannelTable) {
        helper.setText(R.id.news_channel_tv,newsChannelTable.getNewsChannelName());
        if (newsChannelTable.getNewsChannelFixed()) {
//            helper.setTextColor(R.id.news_channel_tv,ContextCompat.getColor(mContext,R.color.gray));
            helper.setTextColor(R.id.news_channel_tv,ContextCompat.getColor(mContext,R.color.orange));
        }else{
            helper.setTextColor(R.id.news_channel_tv,ContextCompat.getColor(mContext,R.color.gray_deep));
        }
        handleLongPress(helper,newsChannelTable);
        handleOnClick(helper,newsChannelTable);
    }


    private void handleLongPress(ViewHolderHelper helper,final NewsChannelTable newsChannelTable) {
        if (mItemDragHelperCallback != null) {
            helper.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mItemDragHelperCallback.setLongPressEnabled(newsChannelTable.getNewsChannelIndex()==0?false:true);
                    return false;
                }
            });
        }
    }

    private void handleOnClick(final ViewHolderHelper helper,final NewsChannelTable newsChannelTable) {
        if (mOnItemClickListener != null) {
            helper.itemView.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if (!newsChannelTable.getNewsChannelFixed()) {
                        mOnItemClickListener.onItemClick(v, helper.getLayoutPosition());
                    }
                }
            });
        }
    }

    @SuppressWarnings("rawtypes")
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<Object, List<Subject>>();
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (isChannelFixed(fromPosition, toPosition)) {
            return false;
        }
        Collections.swap(getAll(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        List<Subject> subjectList = subjectMapper.get(AppConstant.CHANNEL_SWAP);
        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(new ChannelItemMoveEvent(fromPosition, toPosition));
            }
        }
//        RxBus.getInstance().post(AppConstant.CHANNEL_SWAP,new ChannelItemMoveEvent(fromPosition, toPosition));
        return true;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }

    private boolean isChannelFixed(int fromPosition, int toPosition) {
        return (getAll().get(fromPosition).getNewsChannelFixed() ||
                getAll().get(toPosition).getNewsChannelFixed())&&(fromPosition==0||toPosition==0);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
