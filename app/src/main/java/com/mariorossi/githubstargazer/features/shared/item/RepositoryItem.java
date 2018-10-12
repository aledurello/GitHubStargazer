package com.mariorossi.githubstargazer.features.shared.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mariorossi.githubstargazer.R;
import com.mariorossi.githubstargazer.core.model.Repository;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryItem extends AbstractItem<RepositoryItem, RepositoryItem.ViewHolder> {

    public static List<RepositoryItem> getRepositoryItems(List<Repository> repositories, Context context) {
        List<RepositoryItem> items = new ArrayList<>();
        if (repositories != null && repositories.size() > 0) {
            for (int i = 0; i < repositories.size(); i++) {
                items.add(getRepositoryItems(repositories.get(i), context));
            }
        }
        return items;
    }

    public static RepositoryItem getRepositoryItems(Repository repository, Context context) {
        return new RepositoryItem()
                .setRepositoryItem(repository)
                .setContext(context);
    }

    private Context context;
    private Repository repository;

    public RepositoryItem setRepositoryItem(Repository repository) {
        this.repository = repository;
        return this;
    }

    public RepositoryItem setContext(Context context) {
        this.context = context;
        return this;
    }

    public Repository getRepository() {
        return repository;
    }

    public Context getContext() {
        return context;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.fastadapter_repository_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_repository;
    }


    //The logic to bind your data to the view
    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);


        viewHolder.name.setText(getText(repository.getName()));
        viewHolder.desc.setText(getText(repository.getDescription()));
        viewHolder.lang.setText(repository.getLanguage());
        viewHolder.favorite.setText(String.valueOf(repository.getStargazers_count()));
        viewHolder.fork.setText(String.valueOf(repository.getForks_count()));
    }

    private String getText(String input) {
        return input != null ? input : "";
    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.name.setText("");
        holder.desc.setText("");
        holder.lang.setText("");
        holder.favorite.setText("");
        holder.fork.setText("");
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    //The viewHolder used for this item. This viewHolder is always reused by the RecyclerView so scrolling is blazing fast
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.repo_name)
        TextView name;
        @BindView(R.id.repo_desc)
        TextView desc;
        @BindView(R.id.repo_lang)
        TextView lang;
        @BindView(R.id.repo_favorites)
        TextView favorite;
        @BindView(R.id.repo_fork)
        TextView fork;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}
