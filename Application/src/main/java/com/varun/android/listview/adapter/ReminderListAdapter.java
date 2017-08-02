package com.varun.android.listview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.varun.android.listview.R;
import com.varun.android.listview.app.AppController;
import com.varun.android.listview.model.Movie;
import com.varun.android.listview.model.Reminder;

import java.util.List;


/**
 * Created by VarunSasidharan_Nair on 1/08/2017.
 */

public class ReminderListAdapter /*extends Adapter <ReminderListAdapter.ReminderViewHolder>*/ {

    /*private ImageLoader imageLoader;
    private List<Reminder> reminderList;


    public class ReminderViewHolder extends RecyclerView.ViewHolder {
        public TextView title, recurringType, status;

        public ReminderViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            recurringType = (TextView) view.findViewById(R.id.genre);
            status = (TextView) view.findViewById(R.id.releaseYear);
        }
    }

    public ReminderListAdapter(List<Reminder> reminder_list) {
        reminderList = reminder_list;
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new ReminderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {
        Reminder reminder = reminderList.get(position);
        holder.title.setText(reminder.getTitle());
        holder.recurringType.setText(reminder.getRecurringType());
        holder.status.setText(reminder.getStatus());
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }*/
}
