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
package com.smassive.comicviewr.data.bean.dto.comic.mapper;

import com.smassive.comicviewr.data.bean.dto.comic.ComicResponseDto;
import com.smassive.comicviewr.data.bean.dto.common.ImageResponseDto;
import com.smassive.comicviewr.data.bean.vo.comic.ComicVo;
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

public class ComicResponseDtoMapperTest {

    private static final int FAKE_ID = 1;

    private static final String FAKE_TITLE = "fakeTitle";

    private static final String FAKE_DESCRIPTION = "fakeDescription";

    private static final int FAKE_PAGE_COUNT = 1;

    private static final int FAKE_CHARACTER_ID = 1;

    @Test
    public void testToBo() {
        ComicResponseDto mockComicResponseDto = mock(ComicResponseDto.class);
        ImageResponseDto mockImageResponseDto = mock(ImageResponseDto.class);

        ImageResponseDto mockImageOne = mock(ImageResponseDto.class);
        ImageResponseDto mockImageTwo = mock(ImageResponseDto.class);
        List<ImageResponseDto> mockImages = new ArrayList<>();
        mockImages.add(mockImageOne);
        mockImages.add(mockImageTwo);

        when(mockComicResponseDto.getId()).thenReturn(FAKE_ID);
        when(mockComicResponseDto.getTitle()).thenReturn(FAKE_TITLE);
        when(mockComicResponseDto.getDescription()).thenReturn(FAKE_DESCRIPTION);
        when(mockComicResponseDto.getPageCount()).thenReturn(FAKE_PAGE_COUNT);
        when(mockComicResponseDto.getThumbnail()).thenReturn(mockImageResponseDto);
        when(mockComicResponseDto.getImages()).thenReturn(mockImages);

        ComicBo comicBo = ComicResponseDtoMapper.toBo(mockComicResponseDto);

        assertThat(comicBo, is(notNullValue()));
        assertThat(comicBo, is(instanceOf(ComicBo.class)));
        assertThat(comicBo.getId(), is(FAKE_ID));
        assertThat(comicBo.getTitle(), is(FAKE_TITLE));
        assertThat(comicBo.getDescription(), is(FAKE_DESCRIPTION));
        assertThat(comicBo.getPageCount(), is(FAKE_PAGE_COUNT));
    }

    @Test
    public void testToVo() {
        ComicResponseDto mockComicResponseDto = mock(ComicResponseDto.class);
        ImageResponseDto mockImageResponseDto = mock(ImageResponseDto.class);

        ImageResponseDto mockImageOne = mock(ImageResponseDto.class);
        ImageResponseDto mockImageTwo = mock(ImageResponseDto.class);
        List<ImageResponseDto> mockImages = new ArrayList<>();
        mockImages.add(mockImageOne);
        mockImages.add(mockImageTwo);

        when(mockComicResponseDto.getId()).thenReturn(FAKE_ID);
        when(mockComicResponseDto.getTitle()).thenReturn(FAKE_TITLE);
        when(mockComicResponseDto.getDescription()).thenReturn(FAKE_DESCRIPTION);
        when(mockComicResponseDto.getPageCount()).thenReturn(FAKE_PAGE_COUNT);
        when(mockComicResponseDto.getThumbnail()).thenReturn(mockImageResponseDto);
        when(mockComicResponseDto.getImages()).thenReturn(mockImages);

        ComicVo comicVo = ComicResponseDtoMapper.toVo(mockComicResponseDto, FAKE_CHARACTER_ID);

        assertThat(comicVo, is(notNullValue()));
        assertThat(comicVo, is(instanceOf(ComicVo.class)));
        assertThat(comicVo.getId(), is(FAKE_ID));
        assertThat(comicVo.getTitle(), is(FAKE_TITLE));
        assertThat(comicVo.getDescription(), is(FAKE_DESCRIPTION));
        assertThat(comicVo.getPageCount(), is(FAKE_PAGE_COUNT));
        assertThat(comicVo.getCharacterId(), is(FAKE_CHARACTER_ID));
    }
}
