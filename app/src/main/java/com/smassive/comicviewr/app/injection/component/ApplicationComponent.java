/**
 * Copyright (C) 2016 Sergi Castillo Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smassive.comicviewr.app.injection.component;

import com.smassive.comicviewr.app.injection.module.ApplicationModule;
import com.smassive.comicviewr.app.navigation.Navigator;
import com.smassive.comicviewr.app.view.activity.BaseActivity;
import com.smassive.comicviewr.domain.executor.PostExecutionThread;
import com.smassive.comicviewr.domain.executor.ThreadExecutor;
import com.smassive.comicviewr.domain.repository.ComicsRepository;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    ComicsRepository comicsRepository();

    Navigator navigator();
}
