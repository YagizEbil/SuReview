package com.sabanciuniv.sureviewapp;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SuReviewApp extends Application {
    ExecutorService srv = Executors.newCachedThreadPool();
}
