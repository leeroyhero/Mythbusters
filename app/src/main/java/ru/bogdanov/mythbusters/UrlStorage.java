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
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/yH9MOvPF4dtbWqhNnFOS9vwui0peGV3mvzqY9sO4.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/tH8xRHNIXU8HDEhvjpK2PbPGYHI9QhcRB02viH9u.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/oVRt02aWJRPzETUfDyUpHVqunIhd9OdpIsFJZbIQ.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/rhV84gWbv2rdz6fvNROrpRBcFkoTr4ovpTf4PVY1.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/3cDq9UzgIYfnTYBZrmN24oP50sLXFAMjDbLap0UG.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/58c8EOI1MsvpScFxPKcvbLY7dovbwSkN91I8Cpna.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/MeS8PNfHQkaPQvPTTEelBxNicn4ekxZR3hUZ5w0J.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/Of5VQvYGjY6EThnkUS294NOoah8eUDvQ6mi1edAm.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/xVg5IVyfeQdz5n2r6WWXR7rpvKwKbtuyGCSDK3jB.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/KXE2AsOi17jlTEMEYFWJ2Wn6BNP9iaCxX707vLz9.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/HjSvQ9XG3rA7wOaOdIiuWy7DmIgrFCckW26TSHsB.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/N9KYn08ZglGbATAAawl6HSEMkj5WVqgqFbQ8nHUl.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/CeE1n4MRuXMLn7I0d4ASleG8d80grtDPuAcfs167.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/D1SHh1DNKJDtbtvQdEt1dP8hGKaPcMHbaCDHvD7v.m4v");
        urlList.add("https://dev.bs-go.ru/storage/videos/exercises/video/XEOtfkvl5RWmg1UxphUkOI10HV8HqX3Cpl1afVQM.m4v");

        return urlList;
    }


}
