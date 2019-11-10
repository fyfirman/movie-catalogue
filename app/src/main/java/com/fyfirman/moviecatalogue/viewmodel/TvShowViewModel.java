package com.fyfirman.moviecatalogue.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class TvShowViewModel extends ViewModel {

  private MutableLiveData<ArrayList<Tv_Show>> listTvShow = new MutableLiveData<>();

  public void setData() {
    final ArrayList<Tv_Show> listItems = new ArrayList<>();

    AsyncHttpClient client = new AsyncHttpClient();

    String url = "https://api.themoviedb.org/3/discover/tv?api_key=b8d05a72822a6e82cf552ec84fc42c85&language=en-US";

    client.get(url, new AsyncHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
          String result = new String(responseBody);

          Log.d("Response", result);

          JSONObject responseObject = new JSONObject(result);
          JSONArray list = responseObject.getJSONArray("results");

          for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);

            Tv_Show tvShow = new Tv_Show();
            tvShow.setTitle(jsonObject.getString("name"));
            tvShow.setOverview(jsonObject.getString("overview"));
            tvShow.setPoster_path(jsonObject.getString("poster_path"));

            listItems.add(tvShow);
          }
          listTvShow.postValue(listItems);
        } catch (Exception e) {
          Log.d("Exception", e.getMessage());
        }
      }

      @Override
      public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
          Throwable error) {
        Log.d("onFailure", error.getMessage());
      }
    });
  }

  public LiveData<ArrayList<Tv_Show>> getData() {
    return listTvShow;
  }
}
