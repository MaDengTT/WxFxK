package com.xxm.mmd.common_circle.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.xxm.mmd.common_circle.R;

import java.util.ArrayList;
import java.util.List;
import ch.ielse.view.imagewatcher.ImageWatcher;

/**
 * @author KCrason
 * @date 2018/4/27
 */
public class FriendCircleAdapter extends RecyclerView.Adapter<FriendCircleAdapter.BaseFriendCircleViewHolder>
        implements com.xxm.mmd.common_circle.interfaces.OnItemClickPopupMenuListener {

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private List<com.xxm.mmd.common_circle.beans.FriendCircleBean> mFriendCircleBeans;

    private RequestOptions mRequestOptions;

    private int mAvatarSize;

    private DrawableTransitionOptions mDrawableTransitionOptions;

    private com.xxm.mmd.common_circle.widgets.CommentOrPraisePopupWindow mCommentOrPraisePopupWindow;

    private com.xxm.mmd.common_circle.interfaces.OnPraiseOrCommentClickListener mOnPraiseOrCommentClickListener;

    private LinearLayoutManager mLayoutManager;

    private RecyclerView mRecyclerView;

    private ImageWatcher mImageWatcher;

    public FriendCircleAdapter(Context context, RecyclerView recyclerView, ImageWatcher imageWatcher) {
        this.mContext = context;
        this.mImageWatcher = imageWatcher;
        mRecyclerView = recyclerView;
        this.mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.mAvatarSize = com.xxm.mmd.common_circle.Utils.dp2px(44f,context);
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRequestOptions = new RequestOptions().centerCrop();
        this.mDrawableTransitionOptions = DrawableTransitionOptions.withCrossFade();
        if (context instanceof com.xxm.mmd.common_circle.interfaces.OnPraiseOrCommentClickListener) {
            this.mOnPraiseOrCommentClickListener = (com.xxm.mmd.common_circle.interfaces.OnPraiseOrCommentClickListener) context;
        }
    }

    public void setFriendCircleBeans(List<com.xxm.mmd.common_circle.beans.FriendCircleBean> friendCircleBeans) {
        this.mFriendCircleBeans = friendCircleBeans;
        notifyDataSetChanged();
    }

    public void addFriendCircleBeans(List<com.xxm.mmd.common_circle.beans.FriendCircleBean> friendCircleBeans) {
        if (friendCircleBeans != null) {
            if (mFriendCircleBeans == null) {
                mFriendCircleBeans = new ArrayList<>();
            }
            this.mFriendCircleBeans.addAll(friendCircleBeans);
            notifyItemRangeInserted(mFriendCircleBeans.size(), friendCircleBeans.size());
        }
    }

    @Override
    public BaseFriendCircleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == com.xxm.mmd.common_circle.Constants.FriendCircleType.FRIEND_CIRCLE_TYPE_ONLY_WORD) {
            return new OnlyWordViewHolder(mLayoutInflater.inflate(R.layout.item_recycler_firend_circle_only_word, parent, false));
        } else if (viewType == com.xxm.mmd.common_circle.Constants.FriendCircleType.FRIEND_CIRCLE_TYPE_WORD_AND_URL) {
            return new WordAndUrlViewHolder(mLayoutInflater.inflate(R.layout.item_recycler_firend_circle_word_and_url, parent, false));
        } else if (viewType == com.xxm.mmd.common_circle.Constants.FriendCircleType.FRIEND_CIRCLE_TYPE_WORD_AND_IMAGES) {
            return new WordAndImagesViewHolder(mLayoutInflater.inflate(R.layout.item_recycler_firend_circle_word_and_images, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseFriendCircleViewHolder holder, int position) {
        if (holder != null && mFriendCircleBeans != null && position < mFriendCircleBeans.size()) {
            final com.xxm.mmd.common_circle.beans.FriendCircleBean friendCircleBean = mFriendCircleBeans.get(position);
            makeUserBaseData(holder, friendCircleBean, position);
            if (holder instanceof OnlyWordViewHolder) {
                OnlyWordViewHolder onlyWordViewHolder = (OnlyWordViewHolder) holder;
            } else if (holder instanceof WordAndUrlViewHolder) {
                WordAndUrlViewHolder wordAndUrlViewHolder = (WordAndUrlViewHolder) holder;
                wordAndUrlViewHolder.layoutUrl.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "You Click Layout Url", Toast.LENGTH_SHORT).show();
                            }
                        }
                        );
            } else if (holder instanceof WordAndImagesViewHolder) {
                final WordAndImagesViewHolder wordAndImagesViewHolder = (WordAndImagesViewHolder) holder;
                wordAndImagesViewHolder.nineGridView.setOnImageClickListener(
                        new com.xxm.mmd.common_circle.widgets.NineGridView.OnImageClickListener() {
                            @Override
                            public void onImageClick(int position, View view) {
                                mImageWatcher.show((ImageView) view, wordAndImagesViewHolder.nineGridView.getImageViews(),
                                        friendCircleBean.getImageUrls());
                            }
                        });

                wordAndImagesViewHolder.nineGridView.setAdapter(new com.xxm.mmd.common_circle.adapters.NineImageAdapter(mContext, mRequestOptions,
                        mDrawableTransitionOptions, friendCircleBean.getImageUrls()));
            }
        }
    }


    private void makeUserBaseData(BaseFriendCircleViewHolder holder, final com.xxm.mmd.common_circle.beans.FriendCircleBean friendCircleBean, final int position) {
        holder.txtContent.setText(friendCircleBean.getContentSpan());
        setContentShowState(holder, friendCircleBean);
        holder.txtContent.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        com.xxm.mmd.common_circle.enums.TranslationState translationState = friendCircleBean.getTranslationState();
                        if (translationState == com.xxm.mmd.common_circle.enums.TranslationState.END) {
                            com.xxm.mmd.common_circle.Utils.showPopupMenu(mContext, FriendCircleAdapter.this, position, view, com.xxm.mmd.common_circle.enums.TranslationState.END);
                        } else {
                            com.xxm.mmd.common_circle.Utils.showPopupMenu(mContext, FriendCircleAdapter.this, position, view, com.xxm.mmd.common_circle.enums.TranslationState.START);
                        }
                        return true;
                    }
                });

        updateTargetItemContent(position, holder, friendCircleBean.getTranslationState(),
                friendCircleBean.getContentSpan(), false);

        com.xxm.mmd.common_circle.beans.UserBean userBean = friendCircleBean.getUserBean();
        if (userBean != null) {
            holder.txtUserName.setText(userBean.getUserName());
            Glide.with(mContext).load(userBean.getUserAvatarUrl())
                    .apply(mRequestOptions.override(mAvatarSize, mAvatarSize))
                    .transition(mDrawableTransitionOptions)
                    .into(holder.imgAvatar);
        }

        com.xxm.mmd.common_circle.beans.OtherInfoBean otherInfoBean = friendCircleBean.getOtherInfoBean();

        if (otherInfoBean != null) {
            holder.txtSource.setText(otherInfoBean.getSource());
            holder.txtPublishTime.setText(otherInfoBean.getTime());
        }

        if (friendCircleBean.isShowPraise() || friendCircleBean.isShowComment()) {
            holder.layoutPraiseAndComment.setVisibility(View.VISIBLE);
            if (friendCircleBean.isShowComment() && friendCircleBean.isShowPraise()) {
                holder.viewLine.setVisibility(View.VISIBLE);
            } else {
                holder.viewLine.setVisibility(View.GONE);
            }
            if (friendCircleBean.isShowPraise()) {
                holder.txtPraiseContent.setVisibility(View.VISIBLE);
                holder.txtPraiseContent.setText(friendCircleBean.getPraiseSpan());
            } else {
                holder.txtPraiseContent.setVisibility(View.GONE);
            }
            if (friendCircleBean.isShowComment()) {
                holder.verticalCommentWidget.setVisibility(View.VISIBLE);
                holder.verticalCommentWidget.addComments(friendCircleBean.getCommentBeans(), false);
            } else {
                holder.verticalCommentWidget.setVisibility(View.GONE);
            }
        } else {
            holder.layoutPraiseAndComment.setVisibility(View.GONE);
        }

        holder.imgPraiseOrComment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mContext instanceof Activity) {
                            if (mCommentOrPraisePopupWindow == null) {
                                mCommentOrPraisePopupWindow = new com.xxm.mmd.common_circle.widgets.CommentOrPraisePopupWindow(mContext);
                            }
                            mCommentOrPraisePopupWindow
                                    .setOnPraiseOrCommentClickListener(mOnPraiseOrCommentClickListener)
                                    .setCurrentPosition(position);
                            if (mCommentOrPraisePopupWindow.isShowing()) {
                                mCommentOrPraisePopupWindow.dismiss();
                            } else {
                                mCommentOrPraisePopupWindow.showPopupWindow(view);
                            }
                        }
                    }
                });

        holder.txtLocation.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "You Click Location", Toast.LENGTH_SHORT).show();
                    }
                } );
    }

    private void setContentShowState(final BaseFriendCircleViewHolder holder, final com.xxm.mmd.common_circle.beans.FriendCircleBean friendCircleBean) {
        if (friendCircleBean.isShowCheckAll()) {
            holder.txtState.setVisibility(View.VISIBLE);
            setTextState(holder, friendCircleBean.isExpanded());
            holder.txtState.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (friendCircleBean.isExpanded()) {
                                friendCircleBean.setExpanded(false);
                            } else {
                                friendCircleBean.setExpanded(true);
                            }
                            setTextState(holder, friendCircleBean.isExpanded());
                        }
                    });
        } else {
            holder.txtState.setVisibility(View.GONE);
            holder.txtContent.setMaxLines(Integer.MAX_VALUE);
        }
    }

    private void setTextState(BaseFriendCircleViewHolder holder, boolean isExpand) {
        if (isExpand) {
            holder.txtContent.setMaxLines(Integer.MAX_VALUE);
            holder.txtState.setText("收起");
        } else {
            holder.txtContent.setMaxLines(4);
            holder.txtState.setText("全文");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mFriendCircleBeans.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return mFriendCircleBeans == null ? 0 : mFriendCircleBeans.size();
    }


    @Override
    public void onItemClickCopy(int position) {
        Toast.makeText(mContext, "已复制", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickTranslation(final int position) {
        if (mFriendCircleBeans != null && position < mFriendCircleBeans.size()) {
            mFriendCircleBeans.get(position).setTranslationState(com.xxm.mmd.common_circle.enums.TranslationState.CENTER);
            notifyTargetItemView(position, com.xxm.mmd.common_circle.enums.TranslationState.CENTER, null);
            com.xxm.mmd.common_circle.TimerUtils.timerTranslation(
                    new com.xxm.mmd.common_circle.interfaces.OnTimerResultListener() {
                        @Override
                        public void onTimerResult() {
                            if (mFriendCircleBeans != null && position < mFriendCircleBeans.size()) {
                                mFriendCircleBeans.get(position).setTranslationState(com.xxm.mmd.common_circle.enums.TranslationState.END);
                                notifyTargetItemView(position, com.xxm.mmd.common_circle.enums.TranslationState.END, mFriendCircleBeans.get(position).getContentSpan());
                            }
                        }
                    });
        }
    }

    @Override
    public void onItemClickHideTranslation(int position) {
        if (mFriendCircleBeans != null && position < mFriendCircleBeans.size()) {
            mFriendCircleBeans.get(position).setTranslationState(com.xxm.mmd.common_circle.enums.TranslationState.START);
            notifyTargetItemView(position, com.xxm.mmd.common_circle.enums.TranslationState.START, null);
        }
    }


    private void updateTargetItemContent(final int position, BaseFriendCircleViewHolder baseFriendCircleViewHolder,
                                         com.xxm.mmd.common_circle.enums.TranslationState translationState, SpannableStringBuilder translationResult, boolean isStartAnimation) {
        if (translationState == com.xxm.mmd.common_circle.enums.TranslationState.START) {
            baseFriendCircleViewHolder.layoutTranslation.setVisibility(View.GONE);
        } else if (translationState == com.xxm.mmd.common_circle.enums.TranslationState.CENTER) {
            baseFriendCircleViewHolder.layoutTranslation.setVisibility(View.VISIBLE);
            baseFriendCircleViewHolder.divideLine.setVisibility(View.GONE);
            baseFriendCircleViewHolder.translationTag.setVisibility(View.VISIBLE);
            baseFriendCircleViewHolder.translationDesc.setText(R.string.translating);
            baseFriendCircleViewHolder.txtTranslationContent.setVisibility(View.GONE);
            com.xxm.mmd.common_circle.Utils.startAlphaAnimation(baseFriendCircleViewHolder.translationDesc, isStartAnimation);
        } else {
            baseFriendCircleViewHolder.layoutTranslation.setVisibility(View.VISIBLE);
            baseFriendCircleViewHolder.divideLine.setVisibility(View.VISIBLE);
            baseFriendCircleViewHolder.translationTag.setVisibility(View.GONE);
            baseFriendCircleViewHolder.translationDesc.setText(R.string.translated);
            baseFriendCircleViewHolder.txtTranslationContent.setVisibility(View.VISIBLE);
            baseFriendCircleViewHolder.txtTranslationContent.setText(translationResult);
            com.xxm.mmd.common_circle.Utils.startAlphaAnimation(baseFriendCircleViewHolder.txtTranslationContent, isStartAnimation);
            baseFriendCircleViewHolder.txtTranslationContent.setOnLongClickListener(
                    new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            com.xxm.mmd.common_circle.Utils.showPopupMenu(mContext, FriendCircleAdapter.this, position, view, com.xxm.mmd.common_circle.enums.TranslationState.END);
                            return true;
                        }
                    });
        }
    }


    private void notifyTargetItemView(int position, com.xxm.mmd.common_circle.enums.TranslationState translationState, SpannableStringBuilder translationResult) {
        View childView = mLayoutManager.findViewByPosition(position);
        if (childView != null) {
            RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(childView);
            if (viewHolder instanceof BaseFriendCircleViewHolder) {
                BaseFriendCircleViewHolder baseFriendCircleViewHolder = (BaseFriendCircleViewHolder) viewHolder;
                updateTargetItemContent(position, baseFriendCircleViewHolder,
                        translationState, translationResult, true);
            }
        }
    }


    @Override
    public void onItemClickCollection(int position) {
        Toast.makeText(mContext, "已收藏", Toast.LENGTH_SHORT).show();
    }


    static class WordAndImagesViewHolder extends BaseFriendCircleViewHolder {

        com.xxm.mmd.common_circle.widgets.NineGridView nineGridView;

        public WordAndImagesViewHolder(View itemView) {
            super(itemView);
            nineGridView = itemView.findViewById(R.id.nine_grid_view);
        }
    }


    static class WordAndUrlViewHolder extends BaseFriendCircleViewHolder {

        LinearLayout layoutUrl;

        public WordAndUrlViewHolder(View itemView) {
            super(itemView);
            layoutUrl = itemView.findViewById(R.id.layout_url);
        }
    }

    static class OnlyWordViewHolder extends BaseFriendCircleViewHolder {

        public OnlyWordViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class BaseFriendCircleViewHolder extends RecyclerView.ViewHolder {

        public com.xxm.mmd.common_circle.widgets.VerticalCommentWidget verticalCommentWidget;
        public TextView txtUserName;
        public View viewLine;
        public TextView txtPraiseContent;
        public ImageView imgAvatar;
        public TextView txtSource;
        public TextView txtPublishTime;
        public ImageView imgPraiseOrComment;
        public TextView txtLocation;
        public TextView txtContent;
        public TextView txtState;
        public LinearLayout layoutTranslation;
        public TextView txtTranslationContent;
        public View divideLine;
        public ImageView translationTag;
        public TextView translationDesc;
        public LinearLayout layoutPraiseAndComment;

        public BaseFriendCircleViewHolder(View itemView) {
            super(itemView);
            verticalCommentWidget = itemView.findViewById(R.id.vertical_comment_widget);
            txtUserName = itemView.findViewById(R.id.txt_user_name);
            txtPraiseContent = itemView.findViewById(R.id.praise_content);
            viewLine = itemView.findViewById(R.id.view_line);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            txtSource = itemView.findViewById(R.id.txt_source);
            txtPublishTime = itemView.findViewById(R.id.txt_publish_time);
            imgPraiseOrComment = itemView.findViewById(R.id.img_click_praise_or_comment);
            txtLocation = itemView.findViewById(R.id.txt_location);
            txtContent = itemView.findViewById(R.id.txt_content);
            txtState = itemView.findViewById(R.id.txt_state);
            txtTranslationContent = itemView.findViewById(R.id.txt_translation_content);
            layoutTranslation = itemView.findViewById(R.id.layout_translation);
            layoutPraiseAndComment = itemView.findViewById(R.id.layout_praise_and_comment);
            divideLine = itemView.findViewById(R.id.view_divide_line);
            translationTag = itemView.findViewById(R.id.img_translating);
            translationDesc = itemView.findViewById(R.id.txt_translation_desc);
            txtPraiseContent.setMovementMethod(new com.xxm.mmd.common_circle.span.TextMovementMothod());
        }
    }
}
