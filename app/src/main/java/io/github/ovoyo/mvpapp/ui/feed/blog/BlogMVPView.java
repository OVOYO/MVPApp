package io.github.ovoyo.mvpapp.ui.feed.blog;


import java.util.List;

import io.github.ovoyo.mvpapp.data.network.model.BlogResponse;
import io.github.ovoyo.mvpapp.ui.base.MVPView;

public interface BlogMVPView extends MVPView {

    void updateBlog(List<BlogResponse.Blog> blogList);

}
