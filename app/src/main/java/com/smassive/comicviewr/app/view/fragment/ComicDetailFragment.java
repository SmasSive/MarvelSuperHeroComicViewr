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
package com.smassive.comicviewr.app.view.fragment;

import com.smassive.comicviewr.app.R;
import com.smassive.comicviewr.app.injection.component.ComicsComponent;
import com.smassive.comicviewr.app.model.ComicModel;
import com.smassive.comicviewr.app.presenter.ComicDetailPresenter;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Comic detail fragment.
 */
public class ComicDetailFragment extends BaseFragment {

    public static final String ARG_COMIC_ID = "ARG_COMIC_ID";

    @Bind(R.id.comic_title)
    TextView comicTitle;

    @Bind(R.id.comic_description)
    TextView comicDescription;

    @Inject
    ComicDetailPresenter comicDetailPresenter;

    private int comicId;

    public static ComicDetailFragment newInstance(int comicId) {
        ComicDetailFragment comicDetailFragment = new ComicDetailFragment();

        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putInt(ARG_COMIC_ID, comicId);
        comicDetailFragment.setArguments(argumentsBundle);

        return comicDetailFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic_detail, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(ComicsComponent.class).inject(this);

        comicDetailPresenter.setView(this);
        comicId = getArguments().getInt(ARG_COMIC_ID);
        comicDetailPresenter.getComic(comicId);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.comicDetailPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.comicDetailPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.comicDetailPresenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setComicInfo(ComicModel comicModel) {
        if (comicModel != null) {
            comicTitle.setText(comicModel.getTitle());
            comicDescription.setText(Html.fromHtml(comicModel.getDescription()));

            Activity activity = this.getActivity();

            if (activity != null) {
                ImageView comicImage = (ImageView) activity.findViewById(R.id.comic_image);
                Picasso.with(activity).load(comicDetailPresenter.getRandomImageUrl(comicModel)).error(R.drawable.default_comic)
                        .fit().centerCrop().into(comicImage);

                CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(comicModel.getTitle());
                    appBarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                }
            }
        } else {
            comicTitle.setText(getString(R.string.error_comic));
            comicDescription.setText("");
        }
    }
}
