package com.example.app.di.components;

import com.example.app.di.modules.ActivityModule;
import com.example.app.di.modules.BundleModule;
import com.example.app.di.scopes.ScopeActivity;
import com.example.app.view.fragment.ZinesFragment;
import dagger.Subcomponent;

@Subcomponent(
    modules = {
        ActivityModule.class, BundleModule.class
    })
@ScopeActivity
public interface ActivityComponent {

  void inject(ZinesFragment repositoriesFragment);
}
