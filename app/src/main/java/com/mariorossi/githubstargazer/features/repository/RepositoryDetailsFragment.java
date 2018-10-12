package com.mariorossi.githubstargazer.features.repository;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mariorossi.githubstargazer.R;
import com.mariorossi.githubstargazer.core.model.Repository;
import com.mariorossi.githubstargazer.core.model.User;
import com.mariorossi.githubstargazer.core.network.RestApi;
import com.mariorossi.githubstargazer.features.shared.decorator.ItemDecorationAlbumColumns;
import com.mariorossi.githubstargazer.features.shared.item.StargazerItem;
import com.mariorossi.githubstargazer.features.shared.utils.BaseFragment;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryDetailsFragment extends BaseFragment {

    private static final String REPOSITORY = "repository";

    public static Fragment getInstance(Repository repository) {
        Fragment fragment = new RepositoryDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(REPOSITORY, repository);
        fragment.setArguments(bundle);
        return fragment;
    }

    public interface OnRepositoryDetailsListener {

        void onUserClick(User user);
    }

    @BindView(R.id.repo_name)
    TextView name;
    @BindView(R.id.repo_desc)
    TextView desc;
    @BindView(R.id.layout_no_data)
    View noData;
    @BindView(R.id.layout_loading)
    View loading;
    @BindView(R.id.repo_list)
    RecyclerView recyclerView;

    private OnRepositoryDetailsListener listener;

    private FastItemAdapter<StargazerItem> itemAdapter = new FastItemAdapter<>();

    private Callback<List<User>> stargazersResult = new Callback<List<User>>() {
        @Override
        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
            if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                showStargazers(response.body());
            } else {
                showNoData();
            }
        }

        @Override
        public void onFailure(Call<List<User>> call, Throwable t) {
            if (!call.isCanceled()) {
                // TODO: 29/12/2016 show error message
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRepositoryDetailsListener)
            setOnRepositoryDetailsListener((OnRepositoryDetailsListener) context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnRepositoryDetailsListener)
            setOnRepositoryDetailsListener((OnRepositoryDetailsListener) activity);
    }

    public void setOnRepositoryDetailsListener(OnRepositoryDetailsListener onFindRepositoryListener) {
        this.listener = onFindRepositoryListener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null)
            listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_details, container, false);
        ButterKnife.bind(this, view);

        itemAdapter.withOnClickListener(new OnClickListener<StargazerItem>() {
            @Override
            public boolean onClick(View v, IAdapter<StargazerItem> adapter, StargazerItem item, int position) {
                listener.onUserClick(item.getStargazer());
                return true;
            }
        });

        int columns = getResources().getInteger(R.integer.repo_details_columns);
        int space = getResources().getDimensionPixelSize(R.dimen.repo_details_spacing);

        //get our recyclerView and do basic setup
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columns));
        recyclerView.addItemDecoration(new ItemDecorationAlbumColumns(columns, space, true, 0));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.getItemAnimator().setAddDuration(500);
        recyclerView.getItemAnimator().setRemoveDuration(500);
        recyclerView.setAdapter(itemAdapter);

        Bundle bundle = getArguments();
        Repository repository = bundle != null ? (Repository) bundle.getSerializable(REPOSITORY) : null;
        if (repository != null) {
            name.setText(repository.getName());
            desc.setText(repository.getDescription());

            downloadStargazers(repository.getOwner().getLogin(), repository.getName());
        } else {
            showNoData();
        }

        return view;
    }

    private void downloadStargazers(String userName, String repositoryName) {
        cancelCall();
        setCall(RestApi.requestRepositoryStargazers(userName, repositoryName, stargazersResult));
        showLoading();
    }

    private void showStargazers(List<User> body) {
        if (itemAdapter.getAdapterItemCount() > 0)
            itemAdapter.clear();
        itemAdapter.add(StargazerItem.getStargazersItems(body, getActivity()));
        showData();
    }

    private void showLoading() {
        loading.setVisibility(View.VISIBLE);
        noData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showNoData() {
        loading.setVisibility(View.GONE);
        noData.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showData() {
        loading.setVisibility(View.GONE);
        noData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
