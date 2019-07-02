package com.j.nth.appnghenhacoffline.classes;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.j.nth.appnghenhacoffline.activity.MusicActivity;


public class AudioPlayerBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equalsIgnoreCase("com.example.app.ACTION_PLAY")){
            // do your stuff to play action;
            //((MusicActivity) context.getApplicationContext()).position++;
            //((MusicActivity) mcontext.getApplicationContext()).UpdateTimeSong();
            if(MusicActivity.mediaPlayer.isPlaying()) {
                MusicActivity.mediaPlayer.pause();
            }
            else
                MusicActivity.mediaPlayer.start();
        }
        if(action.equalsIgnoreCase("com.example.app.ACTION_NEXT")){
            // do your stuff to play action;
            MusicActivity.btn_next();
        }
        if(action.equalsIgnoreCase("com.example.app.ACTION_BACK")){
            // do your stuff to play action;
            MusicActivity.btn_back();
        }

    }
}
