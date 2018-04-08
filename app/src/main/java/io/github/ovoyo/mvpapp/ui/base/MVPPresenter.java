package io.github.ovoyo.mvpapp.ui.base;


import com.androidnetworking.error.ANError;

public interface MVPPresenter<V extends MVPView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(ANError error);

    void setUserAsLoggedOut();

}
