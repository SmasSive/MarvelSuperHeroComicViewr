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
package com.smassive.comicviewr.data.bean.vo.mapper;

import com.smassive.comicviewr.data.bean.vo.ImageVo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImageVoMapperTest {

    public static final String FAKE_URL = "fakeUrl";

    @Test
    public void testToString() {
        ImageVo mockImageVo = mock(ImageVo.class);

        when(mockImageVo.getUrl()).thenReturn(FAKE_URL);

        String url = ImageVoMapper.toString(mockImageVo);

        assertThat(url, is(notNullValue()));
        assertThat(url, is(instanceOf(String.class)));
        assertThat(url, is(FAKE_URL));
    }

    @Test
    public void testToStringFromList() {
        ImageVo mockImageVoOne = mock(ImageVo.class);
        ImageVo mockImageVoTwo = mock(ImageVo.class);

        when(mockImageVoOne.getUrl()).thenReturn(FAKE_URL);
        when(mockImageVoTwo.getUrl()).thenReturn(FAKE_URL);

        List<ImageVo> imageVos = new ArrayList<>();
        imageVos.add(mockImageVoOne);
        imageVos.add(mockImageVoTwo);

        List<String> urls = ImageVoMapper.toString(imageVos);

        assertThat(urls, is(notNullValue()));
        assertThat(urls.toArray()[0], is(instanceOf(String.class)));
        assertThat(urls.toArray()[1], is(instanceOf(String.class)));
        assertThat(urls.size(), is(2));
    }
}
