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

import com.smassive.comicviewr.data.bean.vo.comic.ComicVo;
import com.smassive.comicviewr.data.bean.vo.comic.mapper.ComicVoMapper;
import com.smassive.comicviewr.domain.bean.ComicBo;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Implementation of {@link ComicDataStore} which represents the local data stored in the database.
 */
public class DbComicDataStore implements ComicDataStore {

    private Context context;

    private Realm realm;

    public DbComicDataStore(Context context) {
        this.context = context;
        this.realm = Realm.getInstance(context);
    }

    /**
     * Get an {@link Observable} which will emit a list of {@link ComicBo}.
     *
     * @param characterId int character identifier which we want to retrieve comics from.
     *
     * @return an {@link Observable} which will emit a list of {@link ComicBo}.
     */
    @Override
    public Observable<List<ComicBo>> getComics(int characterId) {
        RealmResults<ComicVo> comicVos = realm.where(ComicVo.class).equalTo(ComicVo.FIELD_CHARACTER_ID, characterId).findAll();
        List<ComicBo> comicBos = ComicVoMapper.toBo(comicVos);

        realm.close();

        if (comicBos != null && !comicBos.isEmpty()) {
            return Observable.just(comicBos);
        }

        return Observable.empty();
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
        ComicVo comicVo = realm.where(ComicVo.class).equalTo(ComicVo.PRIMARY_KEY, comicId).findFirst();
        ComicBo comicBo = ComicVoMapper.toBo(comicVo);

        realm.close();

        if (comicBo != null) {
            return Observable.just(comicBo);
        }

        return Observable.empty();
    }
}
