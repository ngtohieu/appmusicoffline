package com.j.nth.appnghenhacoffline.classes;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.ArrayList;

public class clsMedia {

    public ArrayList getAlbum(Context context)
    {
        ArrayList<clsAlbum> arrayListAlbum;
        arrayListAlbum = new ArrayList();
        String[] projection = new String[] { MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST, MediaStore.Audio.Albums.ALBUM_ART, MediaStore.Audio.Albums.NUMBER_OF_SONGS };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = MediaStore.Audio.Media.ALBUM + " ASC";
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, sortOrder);
        if (cursor != null && cursor.moveToFirst())
        {
            int name = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
            int image = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
            do{
                clsAlbum album = new clsAlbum(cursor.getString(image),cursor.getString(name));
                arrayListAlbum.add(album);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arrayListAlbum;
    }

    public ArrayList<clsMusic> getMusic(Context context,String nameAlbum)
    {
        ArrayList<clsMusic> clsMusicArrayList = new ArrayList<>();
        String[] column = { MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.MIME_TYPE, };
        String orderBy = android.provider.MediaStore.Audio.Media.TITLE;

        String where = android.provider.MediaStore.Audio.Media.ALBUM + "=?";
        String whereVal[] = {nameAlbum};
        Cursor cursor =context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                column,where,whereVal,null);
        int songTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int songArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int location = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        if(cursor != null)
        {
            MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever();

            while (cursor.moveToNext())
            {
                clsMusic music = new clsMusic(cursor.getString(songTitle),
                        cursor.getString(location));

                clsMusicArrayList.add(music);
            }
        }
        return  clsMusicArrayList;
    }
}
