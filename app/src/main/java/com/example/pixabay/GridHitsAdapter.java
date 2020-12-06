package com.example.pixabay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GridHitsAdapter extends ArrayAdapter<ItemHits> {
    List<ItemHits> hitsList;
    Context context;

    public GridHitsAdapter (Context context) {
        super(context, 0);
        hitsList = new ArrayList<>();
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ItemHits item = getItem(position);

        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.item_hits, null);
            ViewHolder holder = new ViewHolder();
            holder.imgHits = (ImageView) view.findViewById(R.id.imgHits);
            holder.txtDownload = (TextView) view.findViewById(R.id.txtDownload);
            holder.txtLike = (TextView) view.findViewById(R.id.txtLike);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        Glide.with(context)
                .load(item.previewURL)
                .into(holder.imgHits);
        holder.txtDownload.setText(Integer.toString(item.downloads));
        holder.txtLike.setText(Integer.toString(item.likes));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UILApplication.selectedItemHits = item;
                Intent i = new Intent(context, WallpaperActivity.class);
                context.startActivity(i);
            }
        });

        return view;
    }

    class ViewHolder {
        ImageView imgHits;
        TextView txtDownload;
        TextView txtLike;
    }
}
