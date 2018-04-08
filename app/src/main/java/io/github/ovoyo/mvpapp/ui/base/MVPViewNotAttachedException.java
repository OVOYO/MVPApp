package io.github.ovoyo.mvpapp.ui.base;


public class MVPViewNotAttachedException extends RuntimeException {

    public MVPViewNotAttachedException() {
        super("Please call Presenter.onAttach(MvpView) before" +
                " requesting data to the Presenter");
    }
}
