package com.example.sandip.musicapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder>{

    ArrayList<SongInfo> _songs = new ArrayList<SongInfo>();
    Context context;
    OnItemClickListener onItemClicKListener;

    SongAdapter(Context context , ArrayList<SongInfo> _songs){
        this.context = context;
        this._songs = _songs ;
    }

    public interface OnItemClickListener{
        void onItemClick(Button b, View v , SongInfo obj , int postion);
    }

    public void setOnitemClickListener(OnItemClickListener onitemClickListener){
        this.onItemClicKListener = onitemClickListener;
    }

    @NonNull
    @Override
    public SongAdapter.SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(context).inflate(R.layout.row_song , parent, false);
        return  new SongHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongAdapter.SongHolder holder, final int position) {

        final SongInfo c = _songs.get(position);
        holder.TVsongName.setText(c.songName);
        holder.TVartistName.setText(c.artistName);
        holder.BtnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClicKListener!=null){
                    onItemClicKListener.onItemClick(holder.BtnAction , view , c , position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return _songs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView TVsongName , TVartistName;
        Button BtnAction;
        public SongHolder(View itemView) {
            super(itemView);
            TVsongName = (TextView) itemView.findViewById(R.id.SongNameTV);
            TVartistName = (TextView) itemView.findViewById(R.id.ArtistNameTV);
            BtnAction = (Button) itemView.findViewById(R.id.ActionBTN) ;
        }
    }
}
