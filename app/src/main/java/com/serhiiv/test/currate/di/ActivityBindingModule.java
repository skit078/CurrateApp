package com.serhiiv.test.currate.di;

import com.serhiiv.test.currate.di.scope.PerActivity;
import com.serhiiv.test.currate.ui.main.MainActivity;
import com.serhiiv.test.currate.ui.main.di.MainModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity contibuteMainActivity();
}
