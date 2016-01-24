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

import com.smassive.comicviewr.data.bean.dto.ListResponseDto;
import com.smassive.comicviewr.data.bean.dto.comic.ComicResponseDto;
import com.smassive.comicviewr.data.bean.dto.comic.ComicsResponseDto;
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

public class ComicsResponseDtoMapperTest {

    private static final int FAKE_CHARACTER_ID = 1;

    @Test
    public void testToBo() {
        ComicsResponseDto mockComicsResponseDto = mock(ComicsResponseDto.class);
        ListResponseDto<ComicResponseDto> mockListResponseDto = mock(ListResponseDto.class);

        ComicResponseDto mockComicResponseDtoOne = mock(ComicResponseDto.class);
        ComicResponseDto mockComicResponseDtoTwo = mock(ComicResponseDto.class);

        List<ComicResponseDto> comicResponseDtos = new ArrayList<>();
        comicResponseDtos.add(mockComicResponseDtoOne);
        comicResponseDtos.add(mockComicResponseDtoTwo);

        when(mockComicsResponseDto.getData()).thenReturn(mockListResponseDto);
        when(mockListResponseDto.getResults()).thenReturn(comicResponseDtos);

        List<ComicBo> comicBos = ComicsResponseDtoMapper.toBo(mockComicsResponseDto);

        assertThat(comicBos, is(notNullValue()));
        assertThat(comicBos.toArray()[0], is(instanceOf(ComicBo.class)));
        assertThat(comicBos.toArray()[1], is(instanceOf(ComicBo.class)));
        assertThat(comicBos.size(), is(2));
    }

    @Test
    public void testToVo() {
        ComicsResponseDto mockComicsResponseDto = mock(ComicsResponseDto.class);
        ListResponseDto<ComicResponseDto> mockListResponseDto = mock(ListResponseDto.class);

        ComicResponseDto mockComicResponseDtoOne = mock(ComicResponseDto.class);
        ComicResponseDto mockComicResponseDtoTwo = mock(ComicResponseDto.class);

        List<ComicResponseDto> comicResponseDtos = new ArrayList<>();
        comicResponseDtos.add(mockComicResponseDtoOne);
        comicResponseDtos.add(mockComicResponseDtoTwo);

        when(mockComicsResponseDto.getData()).thenReturn(mockListResponseDto);
        when(mockListResponseDto.getResults()).thenReturn(comicResponseDtos);

        List<ComicVo> comicVos = ComicsResponseDtoMapper.toVo(mockComicsResponseDto, FAKE_CHARACTER_ID);

        assertThat(comicVos, is(notNullValue()));
        assertThat(comicVos.toArray()[0], is(instanceOf(ComicVo.class)));
        assertThat(comicVos.toArray()[1], is(instanceOf(ComicVo.class)));
        assertThat(comicVos.size(), is(2));
    }
}
