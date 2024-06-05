package com.sabanciuniv.sureviewapp;

import java.util.concurrent.ExecutorService;
import java.util.logging.Handler;

public class HomeScreenRepository {

    public void getReviews(ExecutorService srv, Handler uiHandler){
        srv.execute(()->{
            //TODO implement getReviews
        });
    }

    public void sendReview(ExecutorService srv, Handler uiHandler,Review review){
        srv.execute(()->{
            //TODO implement sendReview
        });
    }
}
