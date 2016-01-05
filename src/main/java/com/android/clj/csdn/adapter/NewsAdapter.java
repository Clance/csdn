package com.android.clj.csdn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.clj.csdn.R;
import com.android.clj.csdn.app.MyApplication;
import com.android.clj.csdn.bean.News;
import com.android.clj.csdn.volley.VolleyImageHelper;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

/**
 * Created by clj on 2015/10/28.
 */
public class NewsAdapter extends RecyclerView.Adapter {
    private final int TYPE_NORMAL = 0;
    private final int TYPE_FOOT = 1;
    private final ImageLoader mImageLoader;
    private ImageLoader.ImageListener mImageListener;
    private Context mContext;
    private List<News> mDatas;
    private OnItemClickLitener mOnItemClickLitener;
    private boolean isLoading;
    private String mError = null;

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public NewsAdapter(Context context, List<News> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mImageLoader = new ImageLoader(MyApplication.getmRequestQueue(), new VolleyImageHelper());
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
            return new ItemViewHolder(view);
        }
        if (viewType == TYPE_FOOT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_foot, parent, false);
            return new FootHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tv_title.setText(mDatas.get(position).getTitle());
            ((ItemViewHolder) holder).tv_content.setText(mDatas.get(position).getContent());
            ((ItemViewHolder) holder).tv_num_recom.setText(mDatas.get(position).getNum_recom());
            if (mDatas.get(position).getImgLink() != null) {
                ((ItemViewHolder) holder).nv_head.setVisibility(View.VISIBLE);
                ((ItemViewHolder) holder).nv_head.setDefaultImageResId(R.drawable.bg);
                ((ItemViewHolder) holder).nv_head.setImageUrl(mDatas.get(position).getImgLink(), mImageLoader);
            }
            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }

        }
        if (holder instanceof FootHolder) {
            ((FootHolder) holder).foot.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (mError != null) {
                ((FootHolder) holder).progressBar.setVisibility(View.GONE);
                ((FootHolder) holder).message.setText(mError);
            }else{
                ((FootHolder) holder).progressBar.setVisibility(View.VISIBLE);
                ((FootHolder) holder).message.setText("加载中....");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;
        TextView tv_num_recom;
        NetworkImageView nv_head;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.item_news_title);
            tv_content = (TextView) itemView.findViewById(R.id.item_news_content);
            tv_num_recom = (TextView) itemView.findViewById(R.id.item_news_num_recom);
            nv_head = (NetworkImageView) itemView.findViewById(R.id.item_news_head);
        }
    }

    class FootHolder extends RecyclerView.ViewHolder {
        LinearLayout foot;
        ProgressWheel progressBar;
        TextView message;

        public FootHolder(View itemView) {
            super(itemView);
            foot = (LinearLayout) itemView.findViewById(R.id.item_news_foot);
            progressBar = (ProgressWheel) itemView.findViewById(R.id.item_news_progressbar);
            message = (TextView) itemView.findViewById(R.id.item_news_message);
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    public List<News> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<News> mDatas) {
        this.mDatas = mDatas;
    }

    public String getmError() {
        return mError;
    }

    public void setmError(String mError) {
        this.mError = mError;
    }
}
