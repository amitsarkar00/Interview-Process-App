package com.example.app05012022;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

import java.io.File;

public class MyMediaScanner implements
       MediaScannerConnection.MediaScannerConnectionClient {

   private MediaScannerConnection mSC;
   private File file;

   public MyMediaScanner(Context context, File file) {
       this.file = file;
       mSC = new MediaScannerConnection(context, this);
       mSC.connect();
   }

   @Override
   public void onMediaScannerConnected() {
       mSC.scanFile(file.getAbsolutePath(), null);
   }

   @Override
   public void onScanCompleted(String path, Uri uri) {
       mSC.disconnect();
   }
}
