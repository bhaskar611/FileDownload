package com.example.downloadfilefromurl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText url;
    private Button download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = (EditText)findViewById(R.id.editTextTextPersonName);
        download = (Button)findViewById(R.id.button);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getURl = url.getText().toString();

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(getURl));
                String title = URLUtil.guessFileName(getURl,null,null);
                request.setTitle(title);
                request.setDescription("File is Downloading ...");
                String cookie = CookieManager.getInstance().getCookie(getURl);
                request.addRequestHeader("cookie",cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);

                DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
                Toast.makeText(MainActivity.this,"Downloading Started",Toast.LENGTH_SHORT).show();
            }
        });
    }
}