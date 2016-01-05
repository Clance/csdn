package com.android.clj.csdn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.clj.csdn.R;
import com.android.clj.csdn.app.MyApplication;
import com.android.clj.csdn.bean.Comments;
import com.android.clj.csdn.volley.VolleyImageHelper;

import java.util.List;

/**
 * 评论适配器
 * Created by clj on 2015/10/31.
 */
public class CommentsAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<Comments.DataEntity> mCommentsList;

    private final ImageLoader mImageLoader;
    private ImageLoader.ImageListener mImageListener;

    public CommentsAdapter(Context mContext, List<Comments.DataEntity> mCommentsList) {
        this.mContext = mContext;
        this.mCommentsList = mCommentsList;
        mImageLoader = new ImageLoader(MyApplication.getmRequestQueue(), new VolleyImageHelper());
    }

    @Override
    public int getGroupCount() {
        return mCommentsList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mCommentsList.get(groupPosition).getChildren() != null)
            return mCommentsList.get(groupPosition).getChildren().size();
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mCommentsList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mCommentsList.get(groupPosition).getChildren().get(childPosition) != null)
            return mCommentsList.get(groupPosition).getChildren().get(childPosition);
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_comments, parent, false);
            holder.head = (NetworkImageView) convertView.findViewById(R.id.item_comments_head);
            holder.name = (TextView) convertView.findViewById(R.id.item_comments_name);
            holder.time = (TextView) convertView.findViewById(R.id.item_comments_time);
            holder.content = (TextView) convertView.findViewById(R.id.item_comments_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Comments.DataEntity entity = mCommentsList.get(groupPosition);
        if (entity != null) {

            holder.name.setText(mCommentsList.get(groupPosition).getUsername());
            holder.time.setText(mCommentsList.get(groupPosition).getCreate_time());
            holder.content.setText(mCommentsList.get(groupPosition).getBody());
            holder.head.setImageUrl(mCommentsList.get(groupPosition).getAvatar(), mImageLoader);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_comments, parent, false);
            holder.head = (NetworkImageView) convertView.findViewById(R.id.item_comments_head);
            holder.name = (TextView) convertView.findViewById(R.id.item_comments_name);
            holder.time = (TextView) convertView.findViewById(R.id.item_comments_time);
            holder.content = (TextView) convertView.findViewById(R.id.item_comments_content);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.item_comments_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.layout.setPadding(100, 0, 0, 0);
        holder.name.setText(mCommentsList.get(groupPosition).getChildren().get(childPosition).getUsername());
        holder.time.setText(mCommentsList.get(groupPosition).getChildren().get(childPosition).getCreate_time());
        holder.content.setText(mCommentsList.get(groupPosition).getChildren().get(childPosition).getBody());
        holder.head.setImageUrl(mCommentsList.get(groupPosition).getChildren().get(childPosition).getAvatar(), mImageLoader);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder {
        NetworkImageView head;
        TextView name;
        TextView time;
        TextView content;
        LinearLayout layout;
    }

    public List<Comments.DataEntity> getmCommentsList() {
        return mCommentsList;
    }

    public void setmCommentsList(List<Comments.DataEntity> mCommentsList) {
        this.mCommentsList = mCommentsList;
    }
}
