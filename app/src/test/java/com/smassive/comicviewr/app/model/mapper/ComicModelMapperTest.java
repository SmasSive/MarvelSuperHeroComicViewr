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
package com.smassive.comicviewr.app.model.mapper;

import com.smassive.comicviewr.app.model.ComicModel;
import com.smassive.comicviewr.domain.bean.ComicBo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComicModelMapperTest {

    private static final int FAKE_ID = 1;

    private static final String FAKE_TITLE = "fakeTitle";

    private static final String FAKE_DESCRIPTION = "fakeDescription";

    private static final int FAKE_PAGE_COUNT = 1;

    private static final String FAKE_THUMBNAIL_URL = "fakeThumbnailUrl";

    private static final String FAKE_IMAGE_URL_1 = "fakeImageUrl1";

    private static final String FAKE_IMAGE_URL_2 = "fakeImageUrl2";

    @Test
    public void testToModel() {
        ComicBo mockComicBo = mock(ComicBo.class);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add(FAKE_IMAGE_URL_1);
        imageUrls.add(FAKE_IMAGE_URL_2);

        when(mockComicBo.getId()).thenReturn(FAKE_ID);
        when(mockComicBo.getTitle()).thenReturn(FAKE_TITLE);
        when(mockComicBo.getDescription()).thenReturn(FAKE_DESCRIPTION);
        when(mockComicBo.getPageCount()).thenReturn(FAKE_PAGE_COUNT);
        when(mockComicBo.getThumbnailUrl()).thenReturn(FAKE_THUMBNAIL_URL);
        when(mockComicBo.getImageUrls()).thenReturn(imageUrls);

        ComicModel comicModel = ComicModelMapper.toModel(mockComicBo);

        assertThat(comicModel, is(notNullValue()));
        assertThat(comicModel, is(instanceOf(ComicModel.class)));
        assertThat(comicModel.getId(), is(FAKE_ID));
        assertThat(comicModel.getTitle(), is(FAKE_TITLE));
        assertThat(comicModel.getDescription(), is(FAKE_DESCRIPTION));
        assertThat(comicModel.getPageCount(), is(FAKE_PAGE_COUNT));
    }

    @Test
    public void testToModelFromList() {
        ComicBo mockComicBoOne = mock(ComicBo.class);
        ComicBo mockComicBoTwo = mock(ComicBo.class);

        List<ComicBo> comicBos = new ArrayList<>();
        comicBos.add(mockComicBoOne);
        comicBos.add(mockComicBoTwo);

        List<ComicModel> comicModels = ComicModelMapper.toModel(comicBos);

        assertThat(comicModels, is(notNullValue()));
        assertThat(comicModels.toArray()[0], is(instanceOf(ComicModel.class)));
        assertThat(comicModels.toArray()[1], is(instanceOf(ComicModel.class)));
        assertThat(comicModels.size(), is(2));
    }
}
