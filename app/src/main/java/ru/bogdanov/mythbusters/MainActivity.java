package ru.bogdanov.mythbusters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import ru.bogdanov.mythbusters.items.HistoryItem;

public class MainActivity extends AppCompatActivity {
    private TextView textViewThreadsCount;
    private SeekBar seekBar;
    private Button buttonDownload;
    private RecyclerView recyclerView;
    private HistoryRecyclerAdapter recyclerAdapter;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewThreadsCount=findViewById(R.id.textViewThreadsCount);
        seekBar=findViewById(R.id.seekBar);
        buttonDownload=findViewById(R.id.buttonDownload);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerAdapter=new HistoryRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        buttonClear=findViewById(R.id.buttonClear);

        buttonClear.setOnClickListener(v->{clearClicked();});

        buttonDownload.setOnClickListener(v->{downloadButtonClicked();});
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewThreadsCount.setText(String.valueOf(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void clearClicked() {
        recyclerAdapter.clearList();
    }

    private void downloadButtonClicked() {
        buttonDownload.setEnabled(false);
        Downloader downloader=new Downloader(this);

        FileSaver.deleteFolder(this);

        long startTime= SystemClock.elapsedRealtime();
        downloader.loadFiles(UrlStorage.getUrls(getThreadsCountFromSeekbar()))
                .subscribe(new SingleObserver<List<Boolean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Boolean> booleans) {
                        addToHistoryList(startTime);
                        buttonDownload.setEnabled(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        buttonDownload.setEnabled(true);
                        Snackbar.make(buttonDownload, e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void addToHistoryList(long startTime) {
        long secondsSpent=SystemClock.elapsedRealtime()-startTime;
        secondsSpent/=1000;
        HistoryItem historyItem=new HistoryItem(getThreadsCountFromSeekbar(), secondsSpent);
        recyclerAdapter.addToList(historyItem);
    }

    private int getThreadsCountFromSeekbar() {
        return seekBar.getProgress()+1;
    }



}
