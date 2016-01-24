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
package com.smassive.comicviewr.data.bean.dto.common.mapper;

import com.smassive.comicviewr.data.bean.dto.common.ImageResponseDto;
import com.smassive.comicviewr.data.bean.vo.ImageVo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImageResponseDtoMapperTest {

    public static final String FAKE_PATH = "fakePath";

    public static final String FAKE_EXTENSION = "fakeExtension";

    @Test
    public void testToString() {
        ImageResponseDto mockImageResponseDto = mock(ImageResponseDto.class);

        when(mockImageResponseDto.getPath()).thenReturn(FAKE_PATH);
        when(mockImageResponseDto.getExtension()).thenReturn(FAKE_EXTENSION);

        String url = ImageResponseDtoMapper.toString(mockImageResponseDto);

        assertThat(url, is(notNullValue()));
        assertThat(url, is(instanceOf(String.class)));
        assertThat(url, is(FAKE_PATH + ImageResponseDtoMapper.EXTENSION_SEPARATOR + FAKE_EXTENSION));
    }

    @Test
    public void testToStringFromList() {
        ImageResponseDto mockImageResponseDtoOne = mock(ImageResponseDto.class);
        ImageResponseDto mockImageResponseDtoTwo = mock(ImageResponseDto.class);

        when(mockImageResponseDtoOne.getPath()).thenReturn(FAKE_PATH);
        when(mockImageResponseDtoOne.getExtension()).thenReturn(FAKE_EXTENSION);
        when(mockImageResponseDtoTwo.getPath()).thenReturn(FAKE_PATH);
        when(mockImageResponseDtoTwo.getExtension()).thenReturn(FAKE_EXTENSION);

        List<ImageResponseDto> imageResponseDtos = new ArrayList<>();
        imageResponseDtos.add(mockImageResponseDtoOne);
        imageResponseDtos.add(mockImageResponseDtoTwo);

        List<String> urls = ImageResponseDtoMapper.toString(imageResponseDtos);

        assertThat(urls, is(notNullValue()));
        assertThat(urls.toArray()[0], is(instanceOf(String.class)));
        assertThat(urls.toArray()[1], is(instanceOf(String.class)));
        assertThat(urls.size(), is(2));
    }

    @Test
    public void testToVo() {
        ImageResponseDto mockImageResponseDto = mock(ImageResponseDto.class);

        when(mockImageResponseDto.getPath()).thenReturn(FAKE_PATH);
        when(mockImageResponseDto.getExtension()).thenReturn(FAKE_EXTENSION);

        ImageVo imageVo = ImageResponseDtoMapper.toVo(mockImageResponseDto);

        assertThat(imageVo, is(notNullValue()));
        assertThat(imageVo, is(instanceOf(ImageVo.class)));
        assertThat(imageVo.getUrl(), is(FAKE_PATH + ImageResponseDtoMapper.EXTENSION_SEPARATOR + FAKE_EXTENSION));
    }

    @Test
    public void testToVoFromList() {
        ImageResponseDto mockImageResponseDtoOne = mock(ImageResponseDto.class);
        ImageResponseDto mockImageResponseDtoTwo = mock(ImageResponseDto.class);

        when(mockImageResponseDtoOne.getPath()).thenReturn(FAKE_PATH);
        when(mockImageResponseDtoOne.getExtension()).thenReturn(FAKE_EXTENSION);
        when(mockImageResponseDtoTwo.getPath()).thenReturn(FAKE_PATH);
        when(mockImageResponseDtoTwo.getExtension()).thenReturn(FAKE_EXTENSION);

        List<ImageResponseDto> imageResponseDtos = new ArrayList<>();
        imageResponseDtos.add(mockImageResponseDtoOne);
        imageResponseDtos.add(mockImageResponseDtoTwo);

        RealmList<ImageVo> imageVos = ImageResponseDtoMapper.toVo(imageResponseDtos);

        assertThat(imageVos, is(notNullValue()));
        assertThat(imageVos.toArray()[0], is(instanceOf(ImageVo.class)));
        assertThat(imageVos.toArray()[1], is(instanceOf(ImageVo.class)));
        assertThat(imageVos.size(), is(2));
    }
}
