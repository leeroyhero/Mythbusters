package ru.bogdanov.mythbusters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final int THREADS=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Downloader downloader=new Downloader(this);

        FileSaver.deleteFolder(this);

        long startTime= SystemClock.elapsedRealtime();
        downloader.loadFiles(UrlStorage.getUrls(THREADS))
                .subscribe(new SingleObserver<List<Boolean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Boolean> booleans) {
                        logEndTime(startTime);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void logEndTime(long startTime) {
        long time=SystemClock.elapsedRealtime()-startTime;
        time/=1000;
        Log.d("urlStorage", "seconds: "+time);
    }


}
