package ru.bogdanov.mythbusters;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UrlStorage {
    public static List<List<String>> getUrls(int threadsCount){
        List<List<String>> list=new ArrayList<>();

        for (int i=0; i<threadsCount;i++)
            list.add(new ArrayList<String>());

        int pos=0;
        for (String url:getParentUrlList()){
            list.get(pos).add(url);

            pos++;
            if (pos>=threadsCount) pos=0;
        }

        Log.d("urlStorage", "parent list size: "+getParentUrlList().size());
        Log.d("urlStorage", "threads: "+threadsCount+" lists: "+list.size());
        String log="";
        for (List<String> listUrl:list)
            log+="list"+list.indexOf(listUrl)+" size: "+listUrl.size()+"     ";
        Log.d("urlStorage", log);

        return list;
    }

    private static List<String> getParentUrlList(){
        List<String> urlList=new ArrayList<>();
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/ZSt24JyqAjfc5dFyE05TRF4JmDWbJSg0PZ1WWbeM.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/hriXJNcVsMvSNPVKWlfzfwHze03yzJ6gE0ARpaTu.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/oVRt02aWJRPzETUfDyUpHVqunIhd9OdpIsFJZbIQ.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/IapA3w3Cr5a9saOxXd8ZFemFPk9hc4NtvZWGLQ2F.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/jr9IEVMgFQD33mHy3zhKA6rRkqOhjwpQfHtwNyrQ.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/lifHdCWdLDgfZ6FOGVoJYcKWqQw1WWA1wfawpMG9.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/GnCjLaMLoh0SymppiT9gXlSBavpO3X02oapklLlK.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/wRrx5ngZ4nTOwvDzJaqaHwHEUdHIjqdZfIzXunEv.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/ph1oFEnn1KEMjrXzAhtQZojXRATpH2CoeXJQq8Bi.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/UphKLZWWWfdIkYw0hBZH0ouptirwfb5Dv9vRGnFL.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/TxLlWWfyKt6ysGy3EC4QU7j8XukXR6iVhwQ72zn1.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/QysIsGZZROMRI9bXmON7GA3XGhw65pQlGmP2u542.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/tdrCX3gVcSaXoG0r1wabsiFcvPrcVAcUKc5hafQf.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/Vej1pUBbYcYx3W3QPLrALCDjrPGEtsjqVm1RQWtd.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/RBhq5mYas2CYaJtXhkBYPDCD8yn3SObW9HS9u8i6.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/Vej1pUBbYcYx3W3QPLrALCDjrPGEtsjqVm1RQWtd.m4v");
        urlList.add("https://api.bs-go.ru/storage/videos/exercises/video/EGeougKGtUT16XrZhxynYIHH9G7VRwFZiAF4e6Iv.m4v");

        return urlList;
    }


}
