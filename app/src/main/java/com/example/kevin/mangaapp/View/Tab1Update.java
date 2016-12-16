package com.example.kevin.mangaapp.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kevin.mangaapp.Model.MangaObject;
import com.example.kevin.mangaapp.Model.MangaResponse;
import com.example.kevin.mangaapp.Presenter.MangaConnectionResponse;
import com.example.kevin.mangaapp.Presenter.MangaPresenter;
import com.example.kevin.mangaapp.Presenter.MangaPresenterHot;
import com.example.kevin.mangaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 12/2/2016.
 */

public class Tab1Update extends Fragment implements MangaConnectionResponse{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<MangaObject> mangaList;
    private RecyclerView.LayoutManager mLayoutManager;
    private MangaPresenter mangaPresenter;
    private MangaPresenterHot mangaPresenterHot;

    public Tab1Update(){
        //Blank public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1update, container, false);

        mangaPresenter = new MangaPresenter(this);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.tab1_rv);

        mangaList = new ArrayList<>();
        mAdapter = new MangaAdapter(getActivity(),mangaList);

//        linear layout view
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mangaPresenter.getMangaList();
//        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void doMangaSuccess(MangaResponse mangaResponse) {
        MangaResponse _mangaResponse = ((MangaResponse) mangaResponse);
        MangaAdapter ca = new MangaAdapter(getActivity(), _mangaResponse.getManga());
        mRecyclerView.setAdapter(ca);
    }

    @Override
    public void doMangaFail(String message) {

    }

    @Override
    public void doConnectionError(String message) {

    }
}
