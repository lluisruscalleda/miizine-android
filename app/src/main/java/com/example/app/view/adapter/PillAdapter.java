package com.example.app.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.app.R;
import com.example.app.model.Pill;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.PillViewHolder> {

  private Activity context;

  private OnItemClickListener mOnItemClickListener;

  private List<Pill> mItems;

  private final LayoutInflater layoutInflater;

  public interface OnItemClickListener {
    public void onItemClick(View view, int position);
    public void onFavClick(View view, Pill item, boolean fav);
  }

  @Inject
  public PillAdapter(Activity context) {
    this.context = context;
    this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.mItems = Collections.emptyList();
    this.mOnItemClickListener = null;
  }

  @Override
  public PillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.view_pill_row, parent, false);
    return new PillViewHolder(view, this.mOnItemClickListener);
  }

  @Override
  public void onBindViewHolder(PillViewHolder holder, int position) {
    final Pill pill = mItems.get(position);
    holder.bindPill(pill);
  }

  @Override
  public int getItemCount() {
    return (this.mItems != null) ? this.mItems.size() : 0;
  }

  public void setItemList(List<Pill> itemList) {
    this.mItems = itemList;
  }

  public void setListener(OnItemClickListener listener) {
    this.mOnItemClickListener = listener;
  }

  @Override
  public int getItemViewType(int position) {
    //return mItems.get(position).getType();
    return 1;
  }

  static class PillViewHolder extends RecyclerView.ViewHolder {

    private Pill mPill;
    private Context mContext;
    private OnItemClickListener mListener;
    private String id = null;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_image)
    ImageView tvImage;
    @BindView(R.id.tv_timmer)
    TextView tvTimmer;
    @BindView(R.id.iv_like)
    ImageView likeImageView;

    PillViewHolder(View view, OnItemClickListener listener) {
      super(view);
      mListener = listener;
      ButterKnife.bind(this, view);
    }

    public void bindPill(Pill pill) {
      mPill = pill;

      tvName.setText(mPill.getName());

      Glide.with(itemView.getContext()).load(mPill.getImage())
              .centerCrop()
              .error(R.drawable.pill_image_placeholder)
              .crossFade().into(tvImage);

     likeImageView.setTag(R.drawable.ic_favorite_border_white_24dp);

      likeImageView.setOnClickListener(new ImageView.OnClickListener() {
        @Override
        public void onClick (View v){
          boolean fav = false;
          int id = (int) v.getTag();
          if (mListener != null) {
            if (id == R.drawable.ic_favorite_border_white_24dp) {
              v.setTag(R.drawable.ic_favorite_white_24dp);
              likeImageView.setImageResource(R.drawable.ic_favorite_white_24dp);
              fav = true;
            } else {
              v.setTag(R.drawable.ic_favorite_border_white_24dp);
              likeImageView.setImageResource(R.drawable.ic_favorite_border_white_24dp);
              fav = false;
            }
            mListener.onFavClick(v, mPill, fav);
          }
        }
      });
    }

  }
}
