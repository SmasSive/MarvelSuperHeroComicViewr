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
package com.smassive.comicviewr.data.repository;

import com.smassive.comicviewr.data.repository.datasource.CloudComicDataStore;
import com.smassive.comicviewr.data.repository.datasource.DbComicDataStore;
import com.smassive.comicviewr.domain.bean.ComicBo;
import com.smassive.comicviewr.domain.repository.ComicsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Implementation of {@link ComicsRepository} interface for retrieving comics data.
 */
@Singleton
public class ComicsRepositoryImpl implements ComicsRepository {

    private final DbComicDataStore localDataStore;

    private final CloudComicDataStore remoteDataStore;

    @Inject
    public ComicsRepositoryImpl(DbComicDataStore localDataStore, CloudComicDataStore remoteDataStore) {
        this.localDataStore = localDataStore;
        this.remoteDataStore = remoteDataStore;
    }

    /**
     * Get an {@link Observable} which will emit a list of {@link ComicBo}.
     *
     * @param characterId int character identifier which we want to retrieve comics from.
     * @param refresh boolean true if the request should be done to the cloud.
     *
     * @return an {@link Observable} which will emit a list of {@link ComicBo}.
     */
    @Override
    public Observable<List<ComicBo>> getComics(int characterId, boolean refresh) {
        if (refresh) {
            return remoteDataStore.getComics(characterId);
        }
        return Observable.concat(localDataStore.getComics(characterId), remoteDataStore.getComics(characterId)).first();
    }

    /**
     * Get an {@link Observable} which will emit a {@link ComicBo}.
     *
     * @param comicId int comic identifier which we want to retrieve information.
     *
     * @return an {@link Observable} which will emit a {@link ComicBo}.
     */
    @Override
    public Observable<ComicBo> getComic(int comicId) {
        return localDataStore.getComic(comicId);
    }
}
