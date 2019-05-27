package ru.bogdanov.mythbusters;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UrlListManager {
    private static final List<String> urlList=new ArrayList<>();

    public UrlListManager(List<String> list) {
        urlList.clear();
        urlList.addAll(list);
    }

    public String getUrl(){
        synchronized (urlList) {
            if (urlList.size() < 1) return null;

            Log.d("urlStorage", "url count: " + urlList.size());

            String url = urlList.get(0);
            urlList.remove(0);

            return url;
        }
    }
}
