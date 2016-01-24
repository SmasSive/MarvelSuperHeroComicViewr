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

import com.smassive.comicviewr.data.bean.vo.comic.ComicVo;
import com.smassive.comicviewr.data.bean.vo.mapper.ImageVoMapper;
import com.smassive.comicviewr.domain.bean.ComicBo;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public final class ComicVoMapper {

    private ComicVoMapper() {
    }

    public static ComicBo toBo(ComicVo vo) {
        ComicBo bo = null;

        if (vo != null) {
            bo = new ComicBo();

            bo.setId(vo.getId());
            bo.setTitle(vo.getTitle());
            bo.setPageCount(vo.getPageCount());
            bo.setDescription(vo.getDescription());
            bo.setThumbnailUrl(ImageVoMapper.toString(vo.getThumbnail()));
            bo.setImageUrls(ImageVoMapper.toString(vo.getImages()));
        }

        return bo;
    }

    public static List<ComicBo> toBo(RealmResults<ComicVo> vos) {
        List<ComicBo> bos = null;

        if (vos != null && !vos.isEmpty()) {
            bos = new ArrayList<>();

            for (ComicVo vo : vos) {
                bos.add(toBo(vo));
            }
        }

        return bos;
    }
}
