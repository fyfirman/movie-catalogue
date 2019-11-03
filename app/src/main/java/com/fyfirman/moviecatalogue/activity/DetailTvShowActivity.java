package com.fyfirman.moviecatalogue.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.data.Tv_Show;

public class DetailTvShowActivity extends AppCompatActivity {
  public static final String EXTRA_TV_SHOW = "default_extra";
  private ImageView imgPhoto;
  private TextView txtTitle;
  private TextView txtSynopsis;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_tv_show);

    Tv_Show tv_show = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
    bindView(tv_show);
  }

  private void bindView(Tv_Show tv_show){
    imgPhoto = findViewById(R.id.img_photo);
    txtTitle = findViewById(R.id.tv_show_title);
    txtSynopsis = findViewById(R.id.tv_show_synopsis);

    imgPhoto.setImageResource(tv_show.getPhoto());
    txtTitle.setText(tv_show.getTitle());
    txtSynopsis.setText(tv_show.getSynopsis());
  }
}
