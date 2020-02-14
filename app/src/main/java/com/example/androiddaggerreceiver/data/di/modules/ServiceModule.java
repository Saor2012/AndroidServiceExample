package com.example.androiddaggerreceiver.data.di.modules;

import com.example.androiddaggerreceiver.data.ServicePos;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceModule {
    @ContributesAndroidInjector()
    abstract ServicePos bindServicePos();
}
