package com.varun.android.listview.holders;

/**
 * Created by VarunSasidharan_Nair on 2/08/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.varun.android.listview.R;


public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView mItemTextView;

    public RecyclerItemViewHolder(final View parent, TextView itemTextView) {
        super(parent);
        mItemTextView = itemTextView;
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        return new RecyclerItemViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }

}