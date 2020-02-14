package com.example.androiddaggerreceiver.presentation;

import javax.inject.Inject;

public class MainPresenter implements IMainPresenter.Presenter{
    @Inject
    IMainPresenter.View view;

    @Inject
    public MainPresenter() {}

    @Override
    public void detachView() {
        if (view != null) view = null;
    }

    @Override
    public void startView(IMainPresenter.View view) {}

    @Override
    public void init() {}

    @Override
    public void sendQuery() {}

    @Override
    public void startServiceBtn() {
        if (view != null) view.startNewService();
    }

    @Override
    public void stopServiceBtn() {
        if (view != null) view.stopNewService();
    }
}
