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

import com.smassive.comicviewr.data.R;
import com.smassive.comicviewr.data.bean.dto.comic.ComicsResponseDto;
import com.smassive.comicviewr.data.bean.dto.comic.mapper.ComicsResponseDtoMapper;
import com.smassive.comicviewr.data.bean.vo.comic.ComicVo;
import com.smassive.comicviewr.data.net.ApiConstants;
import com.smassive.comicviewr.data.net.ComicApiService;
import com.smassive.comicviewr.data.net.interceptor.AuthInterceptor;
import com.smassive.comicviewr.domain.bean.ComicBo;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Action1;

/**
 * Implementation of {@link ComicDataStore} which represents the remote data stored in the cloud.
 */
public class CloudComicDataStore implements ComicDataStore {

    private Context context;

    private ComicApiService comicApiService;

    public CloudComicDataStore(Context context) {
        this.context = context;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        AuthInterceptor authInterceptor = new AuthInterceptor(context.getString(R.string.public_key),
                context.getString(R.string.private_key));

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(authInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        comicApiService = retrofit.create(ComicApiService.class);
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
        return comicApiService.getComicsByCharacterId(characterId).doOnNext(new ComicsAction1(characterId))
                .map(ComicsResponseDtoMapper::toBo);
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
        throw new UnsupportedOperationException("Operation not allowed");
    }

    private class ComicsAction1 implements Action1<ComicsResponseDto> {

        private int characterId;

        public ComicsAction1(int characterId) {
            this.characterId = characterId;
        }

        @Override
        public void call(ComicsResponseDto comicsResponseDto) {
            Realm realm = Realm.getInstance(context);
            List<ComicVo> comicVos = ComicsResponseDtoMapper.toVo(comicsResponseDto, characterId);

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(comicVos);
            realm.commitTransaction();

            realm.close();
        }
    }
}
