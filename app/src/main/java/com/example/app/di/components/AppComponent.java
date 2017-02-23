package com.example.app.di.components;

import com.example.app.di.modules.ActivityModule;
import com.example.app.di.modules.AppModule;
import com.example.app.di.modules.DataModule;
import com.example.app.di.modules.DomainModule;
import com.example.app.di.modules.PresentationModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(
    modules = {
        AppModule.class, DataModule.class, DomainModule.class, PresentationModule.class
    })
public interface AppComponent {

  ActivityComponent plus(ActivityModule activityModule);
}
