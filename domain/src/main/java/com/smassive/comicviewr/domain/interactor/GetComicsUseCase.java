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
package com.smassive.comicviewr.domain.interactor;

import com.smassive.comicviewr.domain.executor.PostExecutionThread;
import com.smassive.comicviewr.domain.repository.ComicsRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Get comics by character identifier use case.
 */
public class GetComicsUseCase extends UseCase {

    private final ComicsRepository comicsRepository;

    private int characterId;

    private boolean refresh;

    @Inject
    public GetComicsUseCase(PostExecutionThread postExecutionThread, ComicsRepository comicsRepository) {
        super(postExecutionThread);
        this.comicsRepository = comicsRepository;
    }

    /**
     * Executes the current use case.
     *
     * @param characterId       character identifier whose comics will be retrieved.
     * @param refresh           boolean true if the request should be done to the cloud.
     * @param UseCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     */
    public void execute(int characterId, boolean refresh, Subscriber UseCaseSubscriber) {
        this.characterId = characterId;
        this.refresh = refresh;
        super.execute(UseCaseSubscriber);
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    @Override
    protected Observable buildUseCaseObservable() {
        return comicsRepository.getComics(characterId, refresh);
    }
}
