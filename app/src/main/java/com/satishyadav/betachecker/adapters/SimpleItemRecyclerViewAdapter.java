package com.satishyadav.betachecker.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.satishyadav.betachecker.R;
import com.satishyadav.betachecker.apps.AppInfo;

import java.util.List;

/**
 * Created by Avenger on 02-03-2017.
 */
public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<AppInfo> mValues;
    private final Context mContext;

    public SimpleItemRecyclerViewAdapter(Context context, List<AppInfo> items) {
        mValues = items;
        mContext = context;
    }

    @Override
    public SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_list_content, parent, false);
        return new SimpleItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
        AppInfo item = mValues.get(position);
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(item.getName());
        holder.mIcon.setImageDrawable(item.getIcon());
        String package_name = item.getPackage_name();
        holder.mPackageName.setText(package_name);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Context context = v.getContext();
                Uri uri = Uri.parse(context.getResources().getString(R.string.beta_url) + holder.mPackageName.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
                }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView mIcon;
        public final TextView mPackageName;
//        public final TextView mBetaLink;
        public AppInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mIcon = (ImageView) view.findViewById(R.id.apps_icon);
            mPackageName = (TextView) view.findViewById(R.id.app_package_name);
//            mBetaLink = (TextView) view.findViewById(R.id.check_beta);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}