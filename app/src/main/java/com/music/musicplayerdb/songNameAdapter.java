package com.music.musicplayerdb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by k on 8/20/2016.
 */
public class songNameAdapter extends ArrayAdapter<SongDataClass>{
    ArrayList<SongDataClass> mData;
    Context context;
    public songNameAdapter(Context context,ArrayList<SongDataClass> Obj){
        super(context,0,Obj);
        this.context=context;
        this.mData=Obj;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v1=convertView;
        if(v1==null){
            v1= LayoutInflater.from(this.context).inflate(R.layout.adapter_layout_song_name,parent,false);
        }
        SongDataClass obj=mData.get(position);
        TextView songName=(TextView) v1.findViewById(R.id.SongName);
        TextView songArtist=(TextView)v1.findViewById(R.id.SongArtist);
        ImageView albumArt=(ImageView)v1.findViewById(R.id.albumArt);
        if(obj.getCoverArt()!=null){
            Bitmap bm = BitmapFactory.decodeByteArray(obj.coverArt, 0, obj.coverArt.length);
            albumArt.setImageBitmap(bm);
        }
        songName.setText(obj.getSongName());
        songArtist.setText(obj.getSongArtist());
        return v1;
    }
}
