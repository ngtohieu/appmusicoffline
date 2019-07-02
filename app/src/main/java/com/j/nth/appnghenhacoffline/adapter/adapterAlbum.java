package com.j.nth.appnghenhacoffline.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.j.nth.appnghenhacoffline.R;
import com.j.nth.appnghenhacoffline.classes.clsAlbum;
import com.j.nth.appnghenhacoffline.classes.clsMusic;

import java.util.ArrayList;

public class adapterAlbum extends BaseAdapter {

    Context context;
    ArrayList listAlbum;
    int layout;

    public adapterAlbum(Context context,  int layout, ArrayList listAlbum) {
        this.context = context;
        this.listAlbum = listAlbum;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listAlbum.size();
    }

    @Override
    public Object getItem(int position) {
        return listAlbum.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class viewHolder
    {
        TextView txtNameAlbum;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new viewHolder();
            holder.txtNameAlbum = convertView.findViewById(R.id.txtNameAlbum);
            holder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (viewHolder) convertView.getTag();
        }
        clsAlbum album = (clsAlbum) listAlbum.get(position);
        holder.txtNameAlbum.setText(album.getNameAlbum());
        if(album.getImageAlbum()!= null)//Log.d("AAA",album.getImageAlbum());
        {
                holder.imageView.setImageURI(Uri.parse(album.getImageAlbum()));
        }
        return convertView;
    }
}
