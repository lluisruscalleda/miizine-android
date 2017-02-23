package com.example.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.app.R;
import com.example.app.exception.ErrorMessageFactory;
import com.example.app.model.Pill;
import com.example.app.presenter.ZinesPresenter;
import com.example.app.view.activity.BaseActivity;
import com.example.app.view.adapter.PillAdapter;
import com.example.app.view.viewpresenter.ZinesMvpView;
import com.example.domain.exception.ErrorBundle;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ZinesFragment extends BaseFragment<ZinesPresenter> implements ZinesMvpView, PillAdapter.OnItemClickListener {

  @Inject
  ZinesPresenter zinesPresenter;
  @Inject
  PillAdapter pillAdapter;

  @BindView(R.id.rl_item_list)
  RelativeLayout rlItemList;
  @BindView(R.id.rv_item)
  RecyclerView rvItem;
  @BindView(R.id.rl_progress)
  RelativeLayout rlProgress;
  @BindView(R.id.rl_retry)
  RelativeLayout rlRetry;

  private String moment;

  public static ZinesFragment newInstance(String moment) {
    Bundle bundle = new Bundle();
    bundle.putString("moment", moment);

    ZinesFragment fragment = new ZinesFragment();
    fragment.setArguments(bundle);

    return fragment;
  }

  private void readBundle(Bundle bundle) {
    if (bundle != null) {
      moment = bundle.getString("moment");
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((BaseActivity) getActivity()).getActivityComponent().inject(this);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
  ) {
    return inflater.inflate(R.layout.fragment_zines, container, false);
  }

  @Override
  public void onViewCreated(
      View view,
      @Nullable
      Bundle savedInstanceState
  ) {
    super.onViewCreated(view, savedInstanceState);
    Toolbar myToolbar = (Toolbar) view.findViewById(R.id.toolbar);
    ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);

    readBundle(getArguments());

    setupRecyclerView(rvItem, pillAdapter);
    if (getPresenter() != null) {
      getPresenter().attachView(this);
      getPresenter().setMoment(moment);
      getPresenter().initialize();
      mItemsLoaded = true;
    }
  }

  boolean mItemsLoaded = false;
/*
  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser && !mItemsLoaded ) {
      if (getPresenter() != null) {
        getPresenter().attachView(this);
        getPresenter().setMoment(moment);
        getPresenter().initialize();
        mItemsLoaded = true;
      }
    }
  }*/

  @Override
  public void onDestroyView() {
    if (rvItem != null) {
      rvItem.setAdapter(null);
    }
    if (getPresenter() != null) {
      getPresenter().destroy();
    }
    super.onDestroyView();
  }

  @Override
  ZinesPresenter getPresenter() {
    return zinesPresenter;
  }

  @Override
  public void showPills(List<Pill> pills) {
    PillAdapter adapter = (PillAdapter) rvItem.getAdapter();
    adapter.setItemList(pills);
    adapter.setListener(this);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void showLoading() {
    if (rlProgress != null) {
      rlProgress.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void hideLoading() {
    if (rlProgress != null) {
      rlProgress.setVisibility(View.GONE);
    }
  }

  @Override
  public void showRetry() {
    if (rlRetry != null) {
      rlRetry.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void hideRetry() {
    if (rlRetry != null) {
      rlRetry.setVisibility(View.GONE);
    }
  }

  @OnClick(R.id.bt_retry)
  public void retry(View view) {
    getPresenter().initialize();
  }

  @Override
  public void showError(ErrorBundle errorBundle) {
    if (isAdded() && rlItemList != null) {
      this.showSnackbarMessage(this.rlItemList,
                               ErrorMessageFactory.create(getContext(), errorBundle.getException())
      );
    }
  }

  private void setupRecyclerView(
      @NonNull
      RecyclerView recyclerView,
      @NonNull
          PillAdapter adapter
  ) {
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
  }

  @Override
  public void onItemClick(View view, int position) {

  }

  @Override
  public void onFavClick(View view, Pill pill, boolean fav) {
   getPresenter().favItem(pill, fav, this.getContext());
  }
}
