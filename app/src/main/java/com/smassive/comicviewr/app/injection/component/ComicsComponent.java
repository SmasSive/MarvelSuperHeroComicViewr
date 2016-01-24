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

import com.smassive.comicviewr.app.injection.PerActivity;
import com.smassive.comicviewr.app.injection.module.ActivityModule;
import com.smassive.comicviewr.app.injection.module.ComicsModule;
import com.smassive.comicviewr.app.view.activity.MainActivity;
import com.smassive.comicviewr.app.view.fragment.ComicDetailFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ComicsModule.class})
public interface ComicsComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(ComicDetailFragment comicDetailFragment);
}
