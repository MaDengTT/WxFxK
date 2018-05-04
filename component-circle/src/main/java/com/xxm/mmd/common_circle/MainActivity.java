package com.xxm.mmd.common_circle;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.xxm.mmd.common_circle.adapters.FriendCircleAdapter;
import com.xxm.mmd.common_circle.beans.FriendCircleBean;
import com.xxm.mmd.common_circle.interfaces.OnPraiseOrCommentClickListener;
import com.xxm.mmd.common_circle.interfaces.OnStartSwipeRefreshListener;
import com.xxm.mmd.router.RouterConstants;

import java.util.List;
import ch.ielse.view.imagewatcher.ImageWatcher;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
@Route(path = RouterConstants.PATH_Circle_MAIN)
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        OnPraiseOrCommentClickListener, ImageWatcher.OnPictureLongPressListener, ImageWatcher.Loader {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Disposable mDisposable;
    private FriendCircleAdapter mFriendCircleAdapter;
    private ImageWatcher mImageWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_activity_main);
        mSwipeRefreshLayout = findViewById(R.id.swpie_refresh_layout);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MainActivity.this).resumeRequests();
                } else {
                    Glide.with(MainActivity.this).pauseRequests();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mImageWatcher = findViewById(R.id.image_watcher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new FriendsCircleAdapterDivideLine(MainActivity.this));
        mFriendCircleAdapter = new FriendCircleAdapter(this, recyclerView, mImageWatcher);
        recyclerView.setAdapter(mFriendCircleAdapter);
        mImageWatcher.setTranslucentStatus(Utils.calcStatusBarHeight(this));
        mImageWatcher.setErrorImageRes(R.mipmap.error_picture);
        mImageWatcher.setOnPictureLongPressListener(this);
        mImageWatcher.setLoader(this);
        Utils.showSwipeRefreshLayout(mSwipeRefreshLayout, new OnStartSwipeRefreshListener() {
            @Override
            public void onStartRefresh() {
                asyncMakeData();
            }
        } /*this::asyncMakeData*/);
    }


    private void asyncMakeData() {
        mDisposable = Single.create(
                new SingleOnSubscribe<List<FriendCircleBean>>() {
                    @Override
                    public void subscribe(SingleEmitter<List<FriendCircleBean>> e) throws Exception {
                                e.onSuccess(DataCenter.makeFriendCircleBeans(MainActivity.this));
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<FriendCircleBean>>() {
                            @Override
                            public void accept(List<FriendCircleBean> friendCircleBeans) throws Exception {
                                Utils.hideSwipeRefreshLayout(mSwipeRefreshLayout);
                                if (friendCircleBeans != null) {
                                    mFriendCircleAdapter.setFriendCircleBeans(friendCircleBeans);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("cirlce", "accept: ",throwable );
                            }
                        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onRefresh() {
        asyncMakeData();
    }

    @Override
    public void onPraiseClick(int position) {
        Toast.makeText(this, "You Click Praise!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentClick(int position) {
        Toast.makeText(this, "You Click Comment!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (!mImageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }


    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }
}
