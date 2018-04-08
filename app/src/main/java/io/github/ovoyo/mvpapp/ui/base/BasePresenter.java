package io.github.ovoyo.mvpapp.ui.base;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import android.util.Log;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.data.network.model.ApiError;
import io.github.ovoyo.mvpapp.utils.AppConstants;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MVPView> implements MVPPresenter<V> {

    private static final String TAG = "BasePresenter";

    private final CompositeDisposable mCompositeDisposable;
    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;

    private V mMVPView;

    @Inject
    public BasePresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        mCompositeDisposable = compositeDisposable;
        mDataManager = dataManager;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void onAttach(V mvpView) {
        mMVPView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMVPView = null;
    }

    @Override
    public void handleApiError(ANError error) {

        if (error == null || error.getErrorBody() == null) {
            getMVPView().onError(R.string.api_default_error);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
            getMVPView().onError(R.string.connection_error);
            return;
        }

        if (error.getErrorCode() == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            getMVPView().onError(R.string.api_retry_error);
            return;
        }

        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();

        try {
            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

            if (apiError == null || apiError.getMessage() == null) {
                getMVPView().onError(R.string.api_default_error);
                return;
            }

            switch (error.getErrorCode()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    setUserAsLoggedOut();
                    getMVPView().openActivityOnTokenExpire();
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                default:
                    getMVPView().onError(apiError.getMessage());
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            Log.e(TAG, "handleApiError", e);
            getMVPView().onError(R.string.api_default_error);
        }
    }

    @Override
    public void setUserAsLoggedOut() {
        getDataManager().setAccessToken(null);
    }

    public boolean isViewAttached() {
        return mMVPView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MVPViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public V getMVPView() {
        return mMVPView;
    }
}
