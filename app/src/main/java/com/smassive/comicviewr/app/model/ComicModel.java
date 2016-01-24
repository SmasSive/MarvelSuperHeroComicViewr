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
package com.smassive.comicviewr.app.model;

import java.util.List;

import lombok.Data;

/**
 * Model class representing a comic in the UI layer.
 * This kind of class should contain the fields that are present in the screen.
 */
@Data
public class ComicModel {

    private int id;

    private String title;

    private String description;

    private int pageCount;

    private String thumbnailUrl;

    private List<String> imageUrls;
}
