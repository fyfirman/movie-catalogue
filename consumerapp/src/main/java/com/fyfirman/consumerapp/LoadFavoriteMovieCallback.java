package com.fyfirman.consumerapp;

import android.database.Cursor;

interface LoadFavoriteMovieCallback {
    void postExecute(Cursor notes);
}
