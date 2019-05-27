package ru.bogdanov.mythbusters;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Downloader {
    private Context context;

    public Downloader(Context context) {
        this.context = context;
    }

    public Single<List<Boolean>> loadFiles(UrlListManager urlListManager, int threadsCount){
        List<Observable<Boolean>> completableList=getCompletableList(urlListManager, threadsCount);
        return Observable.fromIterable(completableList)
                .flatMap(task -> task.observeOn(Schedulers.io()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<Observable<Boolean>> getCompletableList(UrlListManager urlListManager, int threadsCount) {
        List<Observable<Boolean>> list=new ArrayList<>();
        for (int i=0;i<threadsCount;i++)
            list.add(getObservableOfLoaders(urlListManager)
            .subscribeOn(Schedulers.io()));

        return list;
    }

    private Observable<Boolean> getObservableOfLoaders(UrlListManager urlList){
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                Log.d("urlStorage", "thread started");
                String url;
                while ((url=urlList.getUrl())!=null)
                new FileSaver().downloadAndSave(url, context);

                Log.d("urlStorage", "thread completed: "+emitter.hashCode());
                emitter.onNext(true);
                emitter.onComplete();
            }
        });
    }

}
