package com.example.Minim2DSA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Minim2DSA.API.Repository;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Repository> reposList;
    private LayoutInflater mInflater;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in our viewHolder
        public TextView repoName;
        public TextView repoCode;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            repoName = (TextView) v.findViewById(R.id.repoName);
            repoCode = (TextView) v.findViewById(R.id.repoCode);

        }
    }
    public ListAdapter(List<Repository> reposList, Context context) {
        this.reposList = reposList;
        this.mInflater = LayoutInflater.from((Context) context);
        this.context = (Context) context;
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.activity_repos_list, null);
        return new ListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {

        holder.repoName.setText(reposList.get(position).getName());
        holder.repoCode.setText(reposList.get(position).getLanguage());

    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }



}
