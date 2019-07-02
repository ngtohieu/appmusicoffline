package com.j.nth.appnghenhacoffline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.j.nth.appnghenhacoffline.R;
import com.j.nth.appnghenhacoffline.adapter.AdapterMusic;
import com.j.nth.appnghenhacoffline.classes.clsMedia;
import com.j.nth.appnghenhacoffline.classes.clsMusic;

import java.util.ArrayList;

public class ListMusicActivity extends AppCompatActivity implements AdapterMusic.onCallBack {
    RecyclerView listviewmusic;
    AdapterMusic arrayAdapter;
    public static ArrayList<clsMusic> clsMusicsArr;
    clsMedia clsmedia;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);
        connectView();
        clsmedia = new clsMedia();
        clsMusicsArr = new ArrayList<>();
        intent = getIntent();
        String nameAlbum = intent.getStringExtra("nameAlbum");
        clsMusicsArr = clsmedia.getMusic(this,nameAlbum);
        arrayAdapter = new AdapterMusic(this,R.layout.layout_music,clsMusicsArr,this);
        listviewmusic.setAdapter(arrayAdapter);
//        listviewmusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent1 = new Intent(ListMusicActivity.this,MusicActivity.class);
//                intent.putExtra("position",position);
//                startActivity(intent1);
//            }
//        });

    }
    void connectView()
    {
        listviewmusic = findViewById(R.id.listMusic);
        listviewmusic.setHasFixedSize(true);
        listviewmusic.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void itemOnClick(int positon) {
        Intent intent1 = new Intent(ListMusicActivity.this,MusicActivity.class);
        intent.putExtra("position",positon);
        startActivity(intent1);
    }
}
