package io.github.ovoyo.mvpapp.ui.feed.blog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.ovoyo.mvpapp.R;

public class BlogFragment extends Fragment {

    public BlogFragment() {
        // Required empty public constructor
    }

    public static BlogFragment newInstance() {
        return new BlogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

}
