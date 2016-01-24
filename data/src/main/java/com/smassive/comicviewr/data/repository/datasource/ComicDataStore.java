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
package com.smassive.comicviewr.data.repository.datasource;

import com.smassive.comicviewr.domain.bean.ComicBo;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface ComicDataStore {

    /**
     * Get an {@link Observable} which will emit a list of {@link ComicBo}.
     *
     * @param characterId int character identifier which we want to retrieve comics from.
     *
     * @return an {@link Observable} which will emit a list of {@link ComicBo}.
     */
    Observable<List<ComicBo>> getComics(int characterId);

    /**
     * Get an {@link Observable} which will emit a {@link ComicBo}.
     *
     * @param comicId int comic identifier which we want to retrieve information.
     *
     * @return an {@link Observable} which will emit a {@link ComicBo}.
     */
    Observable<ComicBo> getComic(int comicId);
}
