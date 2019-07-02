package com.j.nth.appnghenhacoffline.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.j.nth.appnghenhacoffline.R;
import com.j.nth.appnghenhacoffline.adapter.AdapterMusic;
import com.j.nth.appnghenhacoffline.adapter.adapterAlbum;
import com.j.nth.appnghenhacoffline.classes.clsAlbum;
import com.j.nth.appnghenhacoffline.classes.clsMedia;
import com.j.nth.appnghenhacoffline.classes.clsMusic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridviewMusic;
    ArrayList<clsAlbum> listAlbum;
    adapterAlbum adapterAlbum;
    int READ_EXTERNAL_STORAGE=1;
    clsMedia clsmedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        listAlbum = new ArrayList();
        clsmedia = new clsMedia();
        listAlbum = clsmedia.getAlbum(MainActivity.this);
        adapterAlbum = new adapterAlbum(this,R.layout.layout_list_album,listAlbum);
        gridviewMusic.setAdapter(adapterAlbum);

        gridviewMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clsAlbum album =listAlbum.get(position);
                Intent intent = new Intent(MainActivity.this,ListMusicActivity.class);
                intent.putExtra("nameAlbum", String.valueOf(album.getNameAlbum()));
                startActivity(intent);
            }
        });
        //Log.d("AAA",File.separator+"");
//        listMusics = new ArrayList<>();
//        adapter = new AdapterMusic(this,R.layout.layout_music,listMusics);
//        listViewMusic.setAdapter(adapter);

//        listViewMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                clsMusic music =listMusics.get(position);
////                Toast.makeText(MainActivity.this,music.getLocation(),Toast.LENGTH_SHORT).show();
////                    if(player.isPlaying())
////                    {
////                       player.stop();
////                    }
////                    player = MediaPlayer.create(getApplicationContext(), Uri.parse(music.getLocation()));
////                    player.start();
//                Intent intent = new Intent(MainActivity.this,MusicActivity.class);
//                intent.putExtra("position",music.getLocation());
//                startActivity(intent);
//            }
//        });

        permisstion();
    }

    void permisstion()
    {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==READ_EXTERNAL_STORAGE && permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {

        }
        else
        {
            Toast.makeText(MainActivity.this,"Bạn chưa cấp quyền",Toast.LENGTH_SHORT).show();
        }
        adapterAlbum.notifyDataSetChanged();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void connectView() {
        gridviewMusic = findViewById(R.id.listMusic);
    }
}
