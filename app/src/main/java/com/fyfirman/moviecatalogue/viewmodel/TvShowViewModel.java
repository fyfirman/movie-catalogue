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
  String api_key = "b8d05a72822a6e82cf552ec84fc42c85";

  public void setData(String language) {
    final ArrayList<Tv_Show> listItems = new ArrayList<>();

    AsyncHttpClient client = new AsyncHttpClient();

    String url = "https://api.themoviedb.org/3/discover/tv?";

    client
        .get(url + "api_key=" + api_key + "&language=" + language, new AsyncHttpResponseHandler() {
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
                tvShow.setId(jsonObject.getInt("id"));
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

  public void searchMovie(String query) {
    AsyncHttpClient client = new AsyncHttpClient();
    final ArrayList<Tv_Show> listItems = new ArrayList<>();
    String url =
        "https://api.themoviedb.org/3/search/tv?api_key=" + api_key + "&language=en-US&query="
            + query;

    client.get(url, new AsyncHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
          String result = new String(responseBody);
          JSONObject responseObject = new JSONObject(result);
          JSONArray list = responseObject.getJSONArray("results");

          for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);
            Tv_Show tv_show = new Tv_Show();
            tv_show.setId(jsonObject.getInt("id"));
            tv_show.setTitle(jsonObject.getString("name"));
            tv_show.setOverview(jsonObject.getString("overview"));
            tv_show.setPoster_path(jsonObject.getString("poster_path"));
            listItems.add(tv_show);
          }

          listTvShow.postValue(listItems);
        } catch (Exception e) {
          Log.d("searchMovieErr", e.getMessage());
        }
      }

      @Override
      public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
          Throwable error) {
//        Toast.makeText(context, context.getString(R.string.connection_error), Toast.LENGTH_LONG)
//            .show();
        Log.d("searchTvShowNoConn", error.getMessage());
      }
    });
  }

  public LiveData<ArrayList<Tv_Show>> getData() {
    return listTvShow;
  }
}
