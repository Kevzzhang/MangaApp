package com.example.kevin.mangaapp.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kevin.mangaapp.Model.MangaObjectHot;
import com.example.kevin.mangaapp.Model.MangaResponseHot;
import com.example.kevin.mangaapp.Presenter.MangaConnectionResponseHot;
import com.example.kevin.mangaapp.Presenter.MangaPresenterHot;
import com.example.kevin.mangaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 12/2/2016.
 */

public class Tab2Ongoing extends Fragment implements MangaConnectionResponseHot{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<MangaObjectHot> mangaList;
    private RecyclerView.LayoutManager mLayoutManager;
    private MangaPresenterHot mangaPresenterHot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2ongoing, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.tab2_rv);
        mangaPresenterHot = new MangaPresenterHot(this);
        mangaList = new ArrayList<>();
        mAdapter = new MangaPopularAdapter(getActivity(),mangaList);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mRecyclerView.setAdapter(mAdapter);
        mangaPresenterHot.getMangaHot();
        return rootView;
    }


    @Override
    public void doMangaSuccess(MangaResponseHot mangaResponseHot) {
        MangaResponseHot _mangaResponse = ((MangaResponseHot) mangaResponseHot);
        MangaPopularAdapter ca = new MangaPopularAdapter(getActivity(), _mangaResponse.getManga());
        mRecyclerView.setAdapter(ca);
    }

    @Override
    public void doMangaFail(String message) {

    }

    @Override
    public void doConnectionError(String message) {

    }
}
