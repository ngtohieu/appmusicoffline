package com.j.nth.appnghenhacoffline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.j.nth.appnghenhacoffline.R;
import com.j.nth.appnghenhacoffline.classes.clsMusic;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewHolder> {

    Context context;
    ArrayList listMusic;
    int layout;
    onCallBack mListener;

    public AdapterMusic(Context context,int layout,ArrayList listMusic,onCallBack mlistener) {
        this.listMusic = listMusic;
        this.layout = layout;
        this.mListener = mlistener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        clsMusic music = (clsMusic) listMusic.get(i);
        viewHolder.txtTitleMusic.setAnimation(AnimationUtils.loadAnimation(context,R.anim.ani_list_music));
        viewHolder.txtTitleMusic.setText(music.getTitle());
    }

    @Override
    public int getItemCount() {
        return listMusic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleMusic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleMusic = itemView.findViewById(R.id.txtTitleMusic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.itemOnClick(getPosition());
                }
            });
        }
    }

    public interface onCallBack
    {
        void itemOnClick(int position);
    }

//    public AdapterMusic(Context context, int layout ,ArrayList listMusic) {
//        this.context = context;
//        this.listMusic = listMusic;
//        this.layout = layout;
//    }
//
//    @Override
//    public int getCount() {
//        return listMusic.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return listMusic.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    class viewHolder
//    {
//        TextView txtTitleMusic;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        viewHolder holder;
//        if(convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(layout, null);
//            holder = new viewHolder();
//            holder.txtTitleMusic = convertView.findViewById(R.id.txtTitleMusic);
//            convertView.setTag(holder);
//        }
//        else
//        {
//            holder = (viewHolder) convertView.getTag();
//        }
//        clsMusic music = (clsMusic) listMusic.get(position);
//        holder.txtTitleMusic.setText(music.getTitle());
//        //gan animation
//        Animation animation = AnimationUtils.loadAnimation(context,R.anim.ani_list_music);
//        convertView.setAnimation(animation);
//        return convertView;
//    }
}
