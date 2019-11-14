package com.fyfirman.moviecatalogue.contentProvider;

import java.util.ArrayList;

public interface LoadFavoriteMovieCallback {
    void preExecute();

    void postExecute(ArrayList<FavoriteMovie> favoriteMovies);
}
