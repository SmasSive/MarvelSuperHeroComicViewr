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
package com.smassive.comicviewr.data.bean.dto.comic;

import com.smassive.comicviewr.data.bean.dto.common.ImageResponseDto;

import java.util.List;

import lombok.Data;

/**
 * Data Transfer Object that represents the JSON returned by the service.
 * This class contains information regarding a comic.
 */
@Data
public class ComicResponseDto {

    private int id;

    private String title;

    private String description;

    private int pageCount;

    private ImageResponseDto thumbnail;

    private List<ImageResponseDto> images;
}
