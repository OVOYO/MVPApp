package io.github.ovoyo.mvpapp.ui.feed.opensource;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.ovoyo.mvpapp.R;

public class OpenSourceFragment extends Fragment {

    public OpenSourceFragment() {
        // Required empty public constructor
    }

    public static OpenSourceFragment newInstance() {
        return new OpenSourceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_source, container, false);
    }

}
