package com.j.nth.appnghenhacoffline.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;

import com.j.nth.appnghenhacoffline.R;
import com.j.nth.appnghenhacoffline.classes.AudioPlayerBroadcastReceiver;
import com.j.nth.appnghenhacoffline.classes.clsMusic;

import java.text.SimpleDateFormat;


public class MusicActivity extends AppCompatActivity{
    LinearLayout linearLayout;
    public static MediaPlayer mediaPlayer;
    static TextView txtTenBH;
    static TextView txtTimeSong;
    static TextView txtTimeTotal;
    static SeekBar skB_Song;
    ImageButton btnBack;
    static ImageButton btnPlay;
    ImageButton btnNext;
    ImageButton btnReload;
    ImageView songImage;
    public static int position = 0;
    public static RemoteViews notificationView;
    static Context mcontext = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        connectView();
        mcontext = getApplicationContext();
        //background
        //sendNotifyicon();
        //notification controls
        sendNotification("NTH");
        linearLayout.setBackgroundResource(R.drawable.layout3);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        clsMusic music = ListMusicActivity.clsMusicsArr.get(position);
        if(music.getImage(music.getLocation(),position)!=null)
        songImage.setImageBitmap(music.getImage(music.getLocation(),position));
        else
            songImage.setImageResource(R.drawable.cd_black);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),Uri.parse(music.getLocation()));
        mediaPlayer.start();
        btnPlay.setImageResource(R.drawable.pause);

        SetTimeToTal();
        UpdateTimeSong();
        txtTenBH.setText(music.getTitle());

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_next();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               btn_back();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (mediaPlayer.isPlaying())
                {
                    // nếu đang chạy nhạc -> pause -> đổi hình play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);

                }
                else
                // đang ngừng -> phát -> đổi hình pause
                {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                }
                SetTimeToTal();
                UpdateTimeSong();
            }
        });

        skB_Song.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skB_Song.getProgress());
            }
        });
    }

    public static void btn_next()
    {
        position++;
        if(position > ListMusicActivity.clsMusicsArr.size() -1)
        {
            position = 0;
        }
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
        clsMusic music = ListMusicActivity.clsMusicsArr.get(position);
        mediaPlayer = MediaPlayer.create(mcontext, Uri.parse(music.getLocation()));

        txtTenBH.setText(music.getTitle());
        mediaPlayer.start();
        btnPlay.setImageResource(R.drawable.pause);
        SetTimeToTal();
        UpdateTimeSong();

    }

    public static void btn_back()
    {
        position--;
        if(position < 0)
        {
            position = ListMusicActivity.clsMusicsArr.size() -1;
        }
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
        clsMusic music = ListMusicActivity.clsMusicsArr.get(position);
        mediaPlayer = MediaPlayer.create(mcontext, Uri.parse(music.getLocation()));

        txtTenBH.setText(music.getTitle());
        notificationView.setTextViewText(R.id.txtSongNameNotify,music.getTitle());
        mediaPlayer.start();
        btnPlay.setImageResource(R.drawable.pause);
        SetTimeToTal();
        UpdateTimeSong();
    }

    private static void SetTimeToTal()
    {
        SimpleDateFormat DinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(DinhDangGio.format(mediaPlayer.getDuration()));
        // gán max của skB_Song = mediaPlayer.getDuration()
        skB_Song.setMax(mediaPlayer.getDuration());
    }

    public static void UpdateTimeSong()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat DinhDangTime = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(DinhDangTime.format(mediaPlayer.getCurrentPosition()));
                //update process skB_Song
                skB_Song.setProgress(mediaPlayer.getCurrentPosition());

                // kiểm tra thời gian bài hát nếu kết thúc -> tự động next bài
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer2) {
                        position++;
                        if(position > ListMusicActivity.clsMusicsArr.size() -1)
                        {
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying())
                        {
                            mediaPlayer.stop();
                        }
                        clsMusic music = ListMusicActivity.clsMusicsArr.get(position);
                        mediaPlayer = MediaPlayer.create(mcontext, Uri.parse(music.getLocation()));

                        txtTenBH.setText(music.getTitle());
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        SetTimeToTal();
                        UpdateTimeSong();
                    }
                });

                handler.postDelayed(this,500);
            }
        },100);
    }

    void sendNotifyicon()
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.play)
                .setContentTitle("aaa")
                .setContentText("dwa");

        Intent intent = new Intent(this,MusicActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(113,mBuilder.build());
    }

    void sendNotification(String name)
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) getSystemService(ns);


        @SuppressWarnings("deprecation")
        Notification notification = new Notification(R.drawable.play, null, System.currentTimeMillis());

        notificationView = new RemoteViews(getPackageName(), R.layout.controll_music_notify);
        notificationView.setTextViewText(R.id.txtSongNameNotify,name);

        //the intent that is started when the notification is clicked (works)
        Intent notificationIntent = new Intent(this, AudioPlayerBroadcastReceiver.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification.contentView = notificationView;
        notification.contentIntent = pendingNotificationIntent;
        notification.flags |= Notification.FLAG_NO_CLEAR;

        //this is the intent that is supposed to be called when the button is clicked
        Intent switchIntent = new Intent("com.example.app.ACTION_PLAY");
        switchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(this, 100, switchIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationView.setOnClickPendingIntent(R.id.btn_play_notify, pendingSwitchIntent);

        //next
        Intent switchIntent_next = new Intent("com.example.app.ACTION_NEXT");
        PendingIntent pendingSwitchIntent_next = PendingIntent.getBroadcast(this, 100, switchIntent_next, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationView.setOnClickPendingIntent(R.id.btn_next_notify, pendingSwitchIntent_next);

        //back
        Intent switchIntent_back = new Intent("com.example.app.ACTION_BACK");
        PendingIntent pendingSwitchIntent_back = PendingIntent.getBroadcast(this, 100, switchIntent_back, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationView.setOnClickPendingIntent(R.id.btn_pre_notify, pendingSwitchIntent_back);

        notificationManager.notify(1, notification);
    }


    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null)
            if(mediaPlayer.isPlaying()) mediaPlayer.stop();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    void connectView()
    {
        linearLayout = findViewById(R.id.screen);
        txtTenBH = findViewById(R.id.txt_TenBH);
        txtTimeSong = findViewById(R.id.txt_TimeSong);
        txtTimeTotal = findViewById(R.id.txt_TimeTotal);
        skB_Song = findViewById(R.id.seekbar);
        btnBack = findViewById(R.id.imgB_Trai);
        btnPlay = findViewById(R.id.img_Play);
        btnNext = findViewById(R.id.imgB_Phai);
        btnReload = findViewById(R.id.imgB_reload);
        songImage = findViewById(R.id.songImage);
    }


}
