package com.example.kevin.mangaapp.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevin.mangaapp.Model.MangaObjectHot;
import com.example.kevin.mangaapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kevin on 12/6/2016.
 */

public class MangaPopularAdapter extends RecyclerView.Adapter<MangaPopularAdapter.PopularViewHolder> {
    private Context mContext;
    private List<MangaObjectHot> mangaList;

    public class PopularViewHolder extends RecyclerView.ViewHolder {
        public TextView mtitle, mchapter;
        public ImageView mcover;

        public PopularViewHolder(View view) {
            super(view);
            mtitle = (TextView) view.findViewById(R.id.txtmangatitle);
            mchapter = (TextView) view.findViewById(R.id.txtmangachapter);
            mcover = (ImageView) view.findViewById(R.id.imageManga);

        }
    }
    public MangaPopularAdapter(Context mContext, List<MangaObjectHot> mangaList) {
        this.mContext = mContext;
        this.mangaList = mangaList;
    }

    @Override
    public MangaPopularAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popularmanga_layout, parent, false);

        return new MangaPopularAdapter.PopularViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MangaPopularAdapter.PopularViewHolder holder, int position) {
        MangaObjectHot mangas = mangaList.get(position);
        holder.mtitle.setText(mangas.getTitle());
        holder.mchapter.setText("Chapter " + mangas.getView());
        Picasso.with(mContext).load(mangas.getUrl()).into(holder.mcover);
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }
}
