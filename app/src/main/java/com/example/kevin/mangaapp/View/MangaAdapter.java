package com.example.kevin.mangaapp.View;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevin.mangaapp.Model.MangaObject;
import com.example.kevin.mangaapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kevin on 12/5/2016.
 */

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MyViewHolder> {

    private Context mContext;
    private List<MangaObject> mangaList;
    public TextView mtitle, mchapter;
    public ImageView mcover;
    public CardView cardView;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View view) {
            super(view);
            mtitle = (TextView) view.findViewById(R.id.txtmangatitle);
            mchapter = (TextView) view.findViewById(R.id.txtmangachapter);
            mcover = (ImageView) view.findViewById(R.id.imageManga);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }


    public MangaAdapter(Context mContext, List<MangaObject> mangaList) {
        this.mContext = mContext;
        this.mangaList = mangaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        MangaObject mangas = mangaList.get(position);
        mtitle.setText(mangas.getTitle());
        mchapter.setText("Chapter " + mangas.getLastupdate());
        Picasso.with(mContext).load(mangas.getUrl()).into( mcover);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

}
