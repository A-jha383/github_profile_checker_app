package com.example.android.github_search_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepoViewHolder>{

    Repository[] repoarray;
    Context mContext;

    public RepositoryAdapter(Repository[] repoarray, Context context) {
        this.repoarray = repoarray;
        mContext = context;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemview=inflater.inflate(R.layout.itemrepo,parent,false);
        RepoViewHolder viewHolder=new RepoViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        Repository repo=repoarray[position];
        holder.reponame.setText(repo.name);
        holder.description.setText(repo.desc);
        holder.language.setText(repo.language);
    }

    @Override
    public int getItemCount() {
        return repoarray.length;
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {

        TextView reponame,description,language;
        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            reponame=(TextView)itemView.findViewById(R.id.rname);
            description=(TextView)itemView.findViewById(R.id.decs);
            language=(TextView)itemView.findViewById(R.id.lang);
        }
    }
}