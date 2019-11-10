package com.fyfirman.moviecatalogue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import java.util.ArrayList;

public class ListTvShowAdapter extends RecyclerView.Adapter<ListTvShowAdapter.ListViewHolder> {

  private ArrayList<Tv_Show> listTvShow;
  private Context context;

  public ListTvShowAdapter(Context context, ArrayList<Tv_Show> list) {
    this.context = context;
    this.listTvShow = list;
  }

  public class ListViewHolder extends ViewHolder {

    ImageView imgPhoto;
    TextView tvTitle, tvSynopsis;

    ListViewHolder(View itemView) {
      super(itemView);
      imgPhoto = itemView.findViewById(R.id.tv_img_photo);
      tvTitle = itemView.findViewById(R.id.tv_show_title);
      tvSynopsis = itemView.findViewById(R.id.tv_show_synopsis);
    }
  }

  public void setData(ArrayList<Tv_Show> items) {
    listTvShow.clear();
    listTvShow.addAll(items);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
    View view = layoutInflater.inflate(R.layout.item_row_tv_show, viewGroup, false);
    return new ListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {
    Tv_Show tv_show = listTvShow.get(i);

    listViewHolder.tvTitle.setText(tv_show.getTitle());
    listViewHolder.tvSynopsis.setText(tv_show.getOverview());
    Glide.with(context)
        .load(tv_show.getPoster_path())
        .into(listViewHolder.imgPhoto);

    listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onItemClickCallback.onItemClicked(listTvShow.get(listViewHolder.getAdapterPosition()));
      }
    });
  }

  @Override
  public int getItemCount() {
    return listTvShow.size();
  }

  private OnItemClickCallback onItemClickCallback;

  public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
    this.onItemClickCallback = onItemClickCallback;
  }

  public interface OnItemClickCallback {

    void onItemClicked(Tv_Show data);
  }
}
