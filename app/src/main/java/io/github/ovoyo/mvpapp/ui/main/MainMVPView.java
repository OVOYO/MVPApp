package io.github.ovoyo.mvpapp.ui.main;


import java.util.List;

import io.github.ovoyo.mvpapp.data.db.model.Question;
import io.github.ovoyo.mvpapp.ui.base.MVPView;

public interface MainMVPView extends MVPView {

    void openLoginActivity();

    void showAboutFragment();

    void refreshQuestionnaire(List<Question> questionList);

    void reloadQuestionnaire(List<Question> questionList);

    void updateUserName(String currentUserName);

    void updateUserEmail(String currentUserEmail);

    void updateUserProfilePic(String currentUserProfilePicUrl);

    void updateAppVersion();

    void showRateUsDialog();

    void openMyFeedActivity();

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();
}
