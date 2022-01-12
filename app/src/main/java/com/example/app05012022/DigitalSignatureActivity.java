package com.example.app05012022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.app05012022.databinding.ActivityDigitalSignatureBinding;
import com.example.app05012022.databinding.ActivityMainBinding;
import com.kyanogen.signatureview.SignatureView;
import com.kyanogen.signatureview.model.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DigitalSignatureActivity extends AppCompatActivity {

//    private SignatureView signatureView;
    private ActivityDigitalSignatureBinding signatureBinding;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_signature);//see xml layout


        int colorPrimary = ContextCompat.getColor(this, R.color.penRoyalBlue);
        signatureBinding.signatureView.setPenColor(colorPrimary);
        // or like signatureView.setPenColor(Color.RED);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_clear:
//                signatureBinding.signatureView.clearCanvas();//Clear SignatureView
//                Toast.makeText(getApplicationContext(),
//                        "Clear canvas", Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.download:
                File directory = Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File file = new File(directory, System.currentTimeMillis() + ".png");
                FileOutputStream out = null;
                Bitmap bitmap = signatureBinding.signatureView.getSignatureBitmap();
                try {
                    out = new FileOutputStream(file);
                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    } else {
                        throw new FileNotFoundException();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.flush();
                            out.close();

                            if (bitmap != null) {
                                Toast.makeText(getApplicationContext(),
                                        "Image saved successfully at " + file.getPath(),
                                        Toast.LENGTH_LONG).show();
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                                    new MyMediaScanner(this, file);
                                } else {
                                    ArrayList<String> toBeScanned = new ArrayList<String>();
                                    toBeScanned.add(file.getAbsolutePath());
                                    String[] toBeScannedStr = new String[toBeScanned.size()];
                                    toBeScannedStr = toBeScanned.toArray(toBeScannedStr);
                                    MediaScannerConnection.scanFile(this, toBeScannedStr, null,
                                            null);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}