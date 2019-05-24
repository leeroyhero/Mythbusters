package ru.bogdanov.mythbusters;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class Downloader {
    private Context context;

    public Downloader(Context context) {
        this.context = context;
    }

    public Single<List<Boolean>> loadFiles(final List<List<String>> packOfUrlLists){
        List<Observable<Boolean>> completableList=getCompletableList(packOfUrlLists);
        return Observable.fromIterable(completableList)
                .flatMap(task -> task.observeOn(Schedulers.io()))
                .toList();
    }

    private List<Observable<Boolean>> getCompletableList(List<List<String>> packOfUrlLists) {
        List<Observable<Boolean>> list=new ArrayList<>();
        for (List<String> urlList: packOfUrlLists)
            list.add(getObservableOfLoaders(urlList)
            .subscribeOn(Schedulers.io()));

        return list;
    }

    private Observable<Boolean> getObservableOfLoaders(List<String> urlList){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                Log.d("urlStorage", "thread started");
                for (String url: urlList)
                new FileSaver().downloadAndSave(url, context);

                emitter.onNext(true);
                emitter.onComplete();
            }
        });
    }


}
