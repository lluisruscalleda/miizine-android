package com.example.app.utils.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Comparator;

public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final Context mContext;
  private static final int SECTION_TYPE = 0;

  private boolean mValid = true;
  private int mSectionResourceId;
  private int mTextResourceId;
  private RecyclerView.Adapter baseAdapter;
  private SparseArray<Section> mSections = new SparseArray<>();
  private Character actualSection = 'A';

  public SectionedRecyclerViewAdapter(
      Context context, int sectionResourceId, int textResourceId, RecyclerView.Adapter baseAdapter
  ) {

    mSectionResourceId = sectionResourceId;
    mTextResourceId = textResourceId;
    this.baseAdapter = baseAdapter;
    mContext = context;

    this.baseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
      @Override
      public void onChanged() {
        mValid = SectionedRecyclerViewAdapter.this.baseAdapter.getItemCount() > 0;
        notifyDataSetChanged();
      }

      @Override
      public void onItemRangeChanged(int positionStart, int itemCount) {
        mValid = SectionedRecyclerViewAdapter.this.baseAdapter.getItemCount() > 0;
        notifyItemRangeChanged(positionStart, itemCount);
      }

      @Override
      public void onItemRangeInserted(int positionStart, int itemCount) {
        mValid = SectionedRecyclerViewAdapter.this.baseAdapter.getItemCount() > 0;
        notifyItemRangeInserted(positionStart, itemCount);
      }

      @Override
      public void onItemRangeRemoved(int positionStart, int itemCount) {
        mValid = SectionedRecyclerViewAdapter.this.baseAdapter.getItemCount() > 0;
        notifyItemRangeRemoved(positionStart, itemCount);
      }
    });
  }

  public RecyclerView.Adapter getBaseAdapter() {
    return baseAdapter;
  }

  public Character getActualSection() {
    return actualSection;
  }

  public static class SectionViewHolder extends RecyclerView.ViewHolder {

    private TextView title;

    public SectionViewHolder(View view, int mTextResourceid) {
      super(view);
      title = (TextView) view.findViewById(mTextResourceid);
    }

    public TextView getTitle() {
      return title;
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int typeView) {
    if (typeView == SECTION_TYPE) {
      final View view = LayoutInflater.from(mContext).inflate(mSectionResourceId, parent, false);
      return new SectionViewHolder(view, mTextResourceId);
    } else {
      return baseAdapter.onCreateViewHolder(parent, typeView - 1);
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder sectionViewHolder, int position) {
    if (isSectionHeaderPosition(position)) {
      ((SectionViewHolder) sectionViewHolder).getTitle().setText(mSections.get(position).title);
    } else {
      baseAdapter.onBindViewHolder(sectionViewHolder, sectionedPositionToPosition(position));
    }
  }

  @Override
  public int getItemViewType(int position) {
    return isSectionHeaderPosition(position) ? SECTION_TYPE : baseAdapter.getItemViewType(sectionedPositionToPosition(
        position)) + 1;
  }

  public static class Section {
    int firstPosition;
    int sectionedPosition;
    CharSequence title;

    public Section(int firstPosition, CharSequence title) {
      this.firstPosition = firstPosition;
      this.title = title;
    }

    public CharSequence getTitle() {
      return title;
    }


    public int getSectionedPosition() {
      return sectionedPosition;
    }

    public void setSectionedPosition(int sectionedPosition) {
      this.sectionedPosition = sectionedPosition;
    }

    public int getFirstPosition() {
      return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
      this.firstPosition = firstPosition;
    }

  }

  public void setSections(Section[] sections) {
    mSections.clear();

    Arrays.sort(sections, new Comparator<Section>() {
      @Override
      public int compare(Section o, Section o1) {
        return (o.firstPosition == o1.firstPosition) ? 0 : ((o.firstPosition < o1.firstPosition) ? -1 : 1);
      }
    });

    int offset = 0; // offset positions for the headers we're adding
    for (Section section : sections) {
      section.sectionedPosition = section.firstPosition + offset;
      mSections.append(section.sectionedPosition, section);
      ++offset;
    }

    notifyDataSetChanged();
  }

  public int sectionedPositionToPosition(int sectionedPosition) {
    if (isSectionHeaderPosition(sectionedPosition)) {
      return RecyclerView.NO_POSITION;
    }

    int offset = 0;
    for (int i = 0; i < mSections.size(); i++) {
      if (mSections.valueAt(i).sectionedPosition > sectionedPosition) {
        break;
      }
      --offset;
    }
    return sectionedPosition + offset;
  }

  public boolean isSectionHeaderPosition(int position) {
    return mSections.get(position) != null;
  }

  public void updateSectionSelected(int position) {
    Section tempSection;
    int key;
    int sum;
    for (int i = 0; i < mSections.size(); i++) {
      key = mSections.keyAt(i);
      tempSection = mSections.get(key);
      sum = tempSection.sectionedPosition;
      if (position >= key && position <= key + sum) {
        actualSection = tempSection.getTitle().charAt(0);
      }
    }
  }

  @Override
  public long getItemId(int position) {
    return isSectionHeaderPosition(position) ? Integer.MAX_VALUE - mSections.indexOfKey(position)
        : baseAdapter.getItemId(sectionedPositionToPosition(position));
  }

  @Override
  public int getItemCount() {
    return mValid ? baseAdapter.getItemCount() + mSections.size() : 0;
  }
}