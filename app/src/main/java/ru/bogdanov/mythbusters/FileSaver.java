package ru.bogdanov.mythbusters;

import android.content.Context;
import android.util.Log;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FileSaver {
    private final static String FOLDER_NAME="videos";

    public void downloadAndSave(String url, Context context) {
        File file=prepareFile(url, context);
        downloadFile(file, url);
    }

    private File prepareFile(String url, Context context) {
        String[] split = url.split("/");
        String fileName = split[split.length - 1];

        File dir = new File(context.getFilesDir(), FOLDER_NAME);
        if (!dir.exists())
            dir.mkdirs();

        return new File(dir.getPath() + "/" + fileName);
    }

    private void downloadFile(File file, String url) {
        try {
            URL u = new URL(url);

            InputStream is = u.openStream();
            DataInputStream dataInputStream = new DataInputStream(is);
            byte[] buffer = new byte[1024];
            int length;

            FileOutputStream fos = new FileOutputStream(file);
            while ((length = dataInputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            is.close();
            fos.flush();
            fos.close();

            Log.d("urlStorage",  "fileLoaded thread:" + Thread.currentThread().getId());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("urlStorage",  e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("urlStorage",  e.getMessage());
        }
    }

    public static void deleteFolder(Context context){
        File dir = new File(context.getFilesDir(), FOLDER_NAME );
        if (dir.isDirectory())
            for (File file:dir.listFiles())
                file.delete();
        dir.delete();
    }
}
