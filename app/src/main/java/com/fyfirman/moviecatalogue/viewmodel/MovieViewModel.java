package com.fyfirman.moviecatalogue.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.fyfirman.moviecatalogue.data.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class MovieViewModel extends ViewModel {

  private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

  public void setData(String language) {
    final ArrayList<Movie> listItems = new ArrayList<>();

    AsyncHttpClient client = new AsyncHttpClient();

    String base_url = "https://api.themoviedb.org/3/discover/movie?";
    String api_key = "b8d05a72822a6e82cf552ec84fc42c85";

    client.get(base_url + "api_key=" + api_key + "&language=" + language, new AsyncHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
          String result = new String(responseBody);

          Log.d("Response", result);

          JSONObject responseObject = new JSONObject(result);
          JSONArray list = responseObject.getJSONArray("results");

          for (int i = 0; i < list.length(); i++) {
            JSONObject jsonObject = list.getJSONObject(i);

            Movie movie = new Movie();
            movie.setId(jsonObject.getInt("id"));
            movie.setTitle(jsonObject.getString("title"));
            movie.setOverview(jsonObject.getString("overview"));
            movie.setPoster_path(jsonObject.getString("poster_path"));

            listItems.add(movie);
          }
          listMovies.postValue(listItems);
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

  public LiveData<ArrayList<Movie>> getData() {
    return listMovies;
  }
}
