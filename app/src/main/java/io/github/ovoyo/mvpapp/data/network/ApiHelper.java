package io.github.ovoyo.mvpapp.data.network;

import io.github.ovoyo.mvpapp.data.network.model.ApiHeader;
import io.github.ovoyo.mvpapp.data.network.model.BlogResponse;
import io.github.ovoyo.mvpapp.data.network.model.LoginRequest;
import io.github.ovoyo.mvpapp.data.network.model.LoginResponse;
import io.github.ovoyo.mvpapp.data.network.model.LogoutResponse;
import io.github.ovoyo.mvpapp.data.network.model.OpenSourceResponse;
import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();

}
