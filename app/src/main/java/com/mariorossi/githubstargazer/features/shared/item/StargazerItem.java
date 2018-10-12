package com.mariorossi.githubstargazer.features.shared.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mariorossi.githubstargazer.R;
import com.mariorossi.githubstargazer.core.model.User;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StargazerItem extends AbstractItem<StargazerItem, StargazerItem.ViewHolder> {

    public static List<StargazerItem> getStargazersItems(List<User> stargazers, Context context) {
        List<StargazerItem> items = new ArrayList<>();
        if (stargazers != null && stargazers.size() > 0) {
            for (int i = 0; i < stargazers.size(); i++) {
                items.add(getStargazerItems(stargazers.get(i), context));
            }
        }
        return items;
    }

    public static StargazerItem getStargazerItems(User user, Context context) {
        return new StargazerItem()
                .setStargazer(user)
                .setContext(context);
    }

    private Context context;
    private User user;

    public StargazerItem setStargazer(User user) {
        this.user = user;
        return this;
    }

    public StargazerItem setContext(Context context) {
        this.context = context;
        return this;
    }

    public User getStargazer() {
        return user;
    }

    public Context getContext() {
        return context;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.fastadapter_stargazer_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_stargazer;
    }


    //The logic to bind your data to the view
    @Override
    public void bindView(ViewHolder viewHolder, List payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);

        viewHolder.name.setText(getText(user.getLogin()));
        Picasso.with(context)
                .load(user.getAvatar_url())
                .resizeDimen(R.dimen.item_stargazer_image_size, R.dimen.item_stargazer_image_size)
                .into(viewHolder.image);
    }

    private String getText(String input) {
        return input != null ? input : "";
    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.name.setText("");
        holder.image.setImageDrawable(null);
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    //The viewHolder used for this item. This viewHolder is always reused by the RecyclerView so scrolling is blazing fast
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.stargazer_name)
        TextView name;
        @BindView(R.id.stargazer_image)
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}
