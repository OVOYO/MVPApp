package io.github.ovoyo.mvpapp.ui.feed.blog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.data.network.model.BlogResponse;
import io.github.ovoyo.mvpapp.di.component.ActivityComponent;
import io.github.ovoyo.mvpapp.ui.base.BaseFragment;

public class BlogFragment extends BaseFragment implements BlogMVPView,BlogAdapter.Callback{

    @Inject
    BlogMVPPresenter<BlogMVPView> mPresenter;

    @Inject
    BlogAdapter mBlogAdapter;

    @BindView(R.id.blog_recycler_view)
    RecyclerView mRecyclerView;

    public BlogFragment() {
        // Required empty public constructor
    }

    public static BlogFragment newInstance() {
        return new BlogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null){
            component.inject(this);

            setUnBinder(ButterKnife.bind(this,view));

            mPresenter.onAttach(this);

            mBlogAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    public void setup(View view) {

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mBlogAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void updateBlog(List<BlogResponse.Blog> blogList) {
        mBlogAdapter.addItems(blogList);
    }

    @Override
    public void onBlogEmptyViewRetryClick() {

    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
