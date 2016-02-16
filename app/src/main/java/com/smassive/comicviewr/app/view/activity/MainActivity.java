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
package com.smassive.comicviewr.app.view.activity;

import com.smassive.comicviewr.app.R;
import com.smassive.comicviewr.app.injection.HasComponent;
import com.smassive.comicviewr.app.injection.component.ComicsComponent;
import com.smassive.comicviewr.app.injection.component.DaggerComicsComponent;
import com.smassive.comicviewr.app.injection.module.ComicsModule;
import com.smassive.comicviewr.app.model.ComicModel;
import com.smassive.comicviewr.app.presenter.ComicsPresenter;
import com.smassive.comicviewr.app.view.adapter.ComicsAdapter;
import com.smassive.comicviewr.app.view.fragment.ComicDetailFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Main screen of the application. Contains the list of comics retrieved for a specific super hero.
 * This Master-Detail activity is shown in two designs depending on which device is displayed, just the list of comics if the
 * device is a handset or two panel (list + detail) if the device is a tablet.
 */
public class MainActivity extends BaseActivity implements HasComponent<ComicsComponent>, SwipeRefreshLayout.OnRefreshListener,
        ComicsAdapter.OnComicClickedListener {

    private static final String DETAIL_FRAGMENT = "DETAIL_FRAGMENT";

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.item_list)
    RecyclerView recyclerView;

    @Inject
    ComicsPresenter comicsPresenter;

    private boolean twoPanel;

    private ComicsComponent comicsComponent;

    private int characterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpToolbar(false);

        if (findViewById(R.id.item_detail_container) != null) {
            twoPanel = true;
        }

        initializeInjector();
        getComponent().inject(this);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        comicsPresenter.setView(this);
        characterId = Integer.valueOf(getString(R.string.character_id));
        comicsPresenter.getComics(characterId, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.comicsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.comicsPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.comicsPresenter.destroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (twoPanel) {
            FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
            Fragment detailFragment = getFragmentManager().findFragmentByTag(DETAIL_FRAGMENT);
            if (detailFragment != null) {
                fragmentTransaction.remove(detailFragment);
            }
            fragmentTransaction.commit();
        }

        super.onSaveInstanceState(outState);
    }

    private void initializeInjector() {
        comicsComponent = DaggerComicsComponent.builder().applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).comicsModule(new ComicsModule()).build();
    }

    public void setItems(List<ComicModel> models) {
        ComicsAdapter adapter = new ComicsAdapter(this, models);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void stopRefresh() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void startRefresh() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    public void showMessage(String message) {
        Snackbar.make(recyclerView, R.string.error_request, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public ComicsComponent getComponent() {
        return comicsComponent;
    }

    @Override
    public void onRefresh() {
        comicsPresenter.getComics(characterId, true);
    }

    @Override
    public void onComicClicked(ComicModel comicModel) {
        if (comicModel != null) {
            if (twoPanel) {
                replaceFragment(R.id.item_detail_container, ComicDetailFragment.newInstance(comicModel.getId()), DETAIL_FRAGMENT);
            } else {
                navigator.goToDetail(this, comicModel.getId());
            }
        } else {
            showMessage(null);
        }
    }
}
