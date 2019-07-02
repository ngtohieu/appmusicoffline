package com.j.nth.appnghenhacoffline.classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import com.j.nth.appnghenhacoffline.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class clsMusic {
    String title;
    String location;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public clsMusic(String title, String location) {
        this.title = title;
        this.location = location;
    }

    public Bitmap getImage(String mPath,int positon)
    {
        try {
            MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever();;
            metaRetriver.setDataSource(mPath);
            byte[] art;
            art = metaRetriver.getEmbeddedPicture();
            Bitmap songImage = BitmapFactory.decodeByteArray(art, positon, art.length);
            Log.d("AAA",art.length+"");
            return songImage;
        }catch (Exception e)
        {
            return null;
        }

    }
}
