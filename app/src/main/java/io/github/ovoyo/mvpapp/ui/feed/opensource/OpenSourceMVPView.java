package io.github.ovoyo.mvpapp.ui.feed.opensource;

import java.util.List;

import io.github.ovoyo.mvpapp.data.network.model.OpenSourceResponse;
import io.github.ovoyo.mvpapp.ui.base.MVPView;


public interface OpenSourceMVPView extends MVPView {

    void updateRepo(List<OpenSourceResponse.Repo> repoList);
}
