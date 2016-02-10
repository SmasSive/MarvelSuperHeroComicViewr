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
package com.smassive.comicviewr.app.injection.module;

import com.smassive.comicviewr.app.injection.PerActivity;
import com.smassive.comicviewr.data.repository.datasource.CloudComicDataStore;
import com.smassive.comicviewr.data.repository.datasource.DbComicDataStore;
import com.smassive.comicviewr.domain.interactor.GetComicUseCase;
import com.smassive.comicviewr.domain.interactor.GetComicsUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides comics related collaborators.
 */
@Module
public class ComicsModule {

    @Provides
    @PerActivity
    @Named("getComics")
    GetComicsUseCase provideGetComics(GetComicsUseCase getComicsUseCase) {
        return getComicsUseCase;
    }

    @Provides
    @PerActivity
    @Named("getComic")
    GetComicUseCase provideGetComic(GetComicUseCase getComicUseCase) {
        return getComicUseCase;
    }

    @Provides
    @PerActivity
    DbComicDataStore provideDbComicDataStore(DbComicDataStore dbComicDataStore) {
        return dbComicDataStore;
    }

    @Provides
    @PerActivity
    CloudComicDataStore provideCloudComicDataStore(CloudComicDataStore cloudComicDataStore) {
        return cloudComicDataStore;
    }
}
