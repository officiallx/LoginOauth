package com.example.postloginoauth.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.postloginoauth.R;
import com.example.postloginoauth.models.Content;
import com.example.postloginoauth.models.Notice;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {


    TextView txtDate, txtTitle, txtContent;
    List<Notice> contents;

    public NoticeAdapter(List<Notice> contents) {
        this.contents = contents;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice_row, viewGroup, false);

        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder noticeViewHolder, final int i) {

        noticeViewHolder.bindUser(contents.get(i));
        Log.d("Contents", "onBindViewHolder: "+contents.toString());
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }


    public class NoticeViewHolder extends RecyclerView.ViewHolder{

        public NoticeViewHolder(@NonNull View itemView) {

            super(itemView);

            txtDate = itemView.findViewById(R.id.txtDate);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);

        }

        public void bindUser(Content content){
            String date = content.getLocalDate();
            txtDate.setText(date);
            String title = content.getTitle();
            txtTitle.setText(title);
            String desc = (content.getContent());
            txtContent.setText(desc);
        }
    }
}
