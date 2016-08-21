package com.music.musicplayerdb;

import android.app.Application;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<SongDataClass> list;
    ListView lv;
    songNameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);
        list=new ArrayList<>();
        list=getSongs(Environment.getExternalStorageDirectory());
        adapter=new songNameAdapter(this,list);
        lv.setAdapter(adapter);
    }
    public static ArrayList<SongDataClass> getSongs(File root) {
        ArrayList<SongDataClass> songs=new ArrayList<>();
        SongDataClass songDataClassObj;
        File[] files=root.getAbsoluteFile().listFiles();
         MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        for( File singleFile:files){
            if(singleFile.isDirectory()&& !singleFile.isHidden()){
                songs.addAll(getSongs(singleFile));
            }
            else{
                if(singleFile.getName().endsWith(".mp3")){
                        if(singleFile.getAbsoluteFile()!=null) {
                            mmr.setDataSource(singleFile.getAbsolutePath());

                            if (mmr.METADATA_KEY_DURATION <= 50000) {
                                songDataClassObj = new SongDataClass(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION), singleFile.getPath(), mmr.getEmbeddedPicture());
                                songs.add(songDataClassObj);
                                Log.i("INFO",singleFile.getAbsolutePath());
                            }
                        }
                }
            }

        }
        return songs;
    }

}
