package io.github.ovoyo.mvpapp.ui.feed.opensource;


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
import io.github.ovoyo.mvpapp.data.network.model.OpenSourceResponse;
import io.github.ovoyo.mvpapp.di.component.ActivityComponent;
import io.github.ovoyo.mvpapp.ui.base.BaseFragment;

public class OpenSourceFragment extends BaseFragment implements OpenSourceMVPView, OpenSourceAdapter.Callback {

    @Inject
    OpenSourceMVPPresenter<OpenSourceMVPView> mPresenter;

    @BindView(R.id.repo_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    OpenSourceAdapter mOpenSourceAdapter;

    public OpenSourceFragment() {
        // Required empty public constructor
    }

    public static OpenSourceFragment newInstance() {
        return new OpenSourceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_source, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    public void updateRepo(List<OpenSourceResponse.Repo> repoList) {
        mOpenSourceAdapter.addItems(repoList);
    }

    @Override
    public void setup(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mOpenSourceAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void onRepoEmptyViewRetryClick() {

    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
