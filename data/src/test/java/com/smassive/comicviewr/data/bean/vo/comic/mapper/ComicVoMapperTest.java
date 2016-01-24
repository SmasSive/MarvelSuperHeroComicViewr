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
package com.smassive.comicviewr.data.bean.vo.comic.mapper;

import com.smassive.comicviewr.data.bean.vo.ImageVo;
import com.smassive.comicviewr.data.bean.vo.comic.ComicVo;
import com.smassive.comicviewr.domain.bean.ComicBo;

import org.junit.Test;

import io.realm.RealmList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComicVoMapperTest {

    private static final int FAKE_ID = 1;

    private static final String FAKE_TITLE = "fakeTitle";

    private static final String FAKE_DESCRIPTION = "fakeDescription";

    private static final int FAKE_PAGE_COUNT = 1;

    @Test
    public void testToBo() {
        ComicVo mockComicVo = mock(ComicVo.class);

        ImageVo mockThumbnail = mock(ImageVo.class);

        ImageVo mockImageOne = mock(ImageVo.class);
        ImageVo mockImageTwo = mock(ImageVo.class);
        RealmList<ImageVo> mockImages = new RealmList<>();
        mockImages.add(mockImageOne);
        mockImages.add(mockImageTwo);

        when(mockComicVo.getId()).thenReturn(FAKE_ID);
        when(mockComicVo.getTitle()).thenReturn(FAKE_TITLE);
        when(mockComicVo.getDescription()).thenReturn(FAKE_DESCRIPTION);
        when(mockComicVo.getPageCount()).thenReturn(FAKE_PAGE_COUNT);
        when(mockComicVo.getThumbnail()).thenReturn(mockThumbnail);
        when(mockComicVo.getImages()).thenReturn(mockImages);

        ComicBo comicBo = ComicVoMapper.toBo(mockComicVo);

        assertThat(comicBo, is(notNullValue()));
        assertThat(comicBo, is(instanceOf(ComicBo.class)));
        assertThat(comicBo.getId(), is(FAKE_ID));
        assertThat(comicBo.getTitle(), is(FAKE_TITLE));
        assertThat(comicBo.getDescription(), is(FAKE_DESCRIPTION));
        assertThat(comicBo.getPageCount(), is(FAKE_PAGE_COUNT));
    }
}
