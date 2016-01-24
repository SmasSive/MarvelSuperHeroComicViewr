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
package com.smassive.comicviewr.app.presenter;

import com.smassive.comicviewr.app.injection.PerActivity;
import com.smassive.comicviewr.app.model.ComicModel;
import com.smassive.comicviewr.app.model.mapper.ComicModelMapper;
import com.smassive.comicviewr.app.view.fragment.ComicDetailFragment;
import com.smassive.comicviewr.domain.bean.ComicBo;
import com.smassive.comicviewr.domain.interactor.GetComicUseCase;
import com.smassive.comicviewr.domain.interactor.UseCase;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * {@link Presenter} that controls communication between views and models of the presentation layer.
 */
@PerActivity
public class ComicDetailPresenter implements Presenter {

    private static final int RANDOM_MIN = 0;

    private static final String TAG = ComicDetailPresenter.class.getName();

    private ComicDetailFragment view;

    private final UseCase getComicUseCase;

    private ComicModel model;

    @Inject
    public ComicDetailPresenter(@Named("getComic") UseCase getComicUseCase) {
        this.getComicUseCase = getComicUseCase;
    }

    public void setView(@NonNull ComicDetailFragment comicDetailFragment) {
        this.view = comicDetailFragment;
    }

    public void getComic(int comicId) {
        if (getComicUseCase instanceof GetComicUseCase) {
            ((GetComicUseCase) getComicUseCase).execute(comicId, new GetComicSubscriber());
        }
    }

    public String getRandomImageUrl(ComicModel model) {
        if (model != null) {
            List<String> imageUrls = model.getImageUrls();
            if (imageUrls != null && !imageUrls.isEmpty()) {
                return imageUrls.get(getRandomPosition(imageUrls.size() - 1));
            }
        }
        return "";
    }

    private int getRandomPosition(int max) {
        Random random = new Random();
        return random.nextInt(max - RANDOM_MIN + 1) + RANDOM_MIN;
    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    @Override
    public void resume() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    @Override
    public void pause() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    @Override
    public void destroy() {
        getComicUseCase.unsubscribe();
    }

    private void setModel() {
        view.setComicInfo(model);
    }

    private class GetComicSubscriber extends Subscriber<ComicBo> {
        @Override
        public void onCompleted() {
            // hide loading view for example
            setModel();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());

        }

        @Override
        public void onNext(ComicBo comicBo) {
            model = ComicModelMapper.toModel(comicBo);
        }
    }
}
