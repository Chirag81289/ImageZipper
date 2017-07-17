package com.developers.imagezipperdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.imagezipper.ImageZipper;
import com.developers.imagezipper.OrientationConstants;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.result_image_view)
    ImageView result;
    @BindView(R.id.original_image_view)
    ImageView originalImage;
    @BindView(R.id.original_size)
    TextView originalSize;
    @BindView(R.id.compressed_size)
    TextView compressedSize;

    private static final String TAG=MainActivity.class.getSimpleName();
    private String originalFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions=new String[]{ "jpg", "jpeg", "png", "gif" };
        properties.extensions = null;
        FilePickerDialog dialog = new FilePickerDialog(MainActivity.this,properties);
        dialog.setTitle("Select a File");
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                Log.d(TAG,files[0]+"");
                originalFilePath=files[0];
                File actualFile=new File(originalFilePath);
                try {
                    File imageZipperFile=new ImageZipper(MainActivity.this).setQuality(10).setMaxWidth(200).setMaxHeight(200).compressToFile(actualFile);
                    originalImage.setImageBitmap(BitmapFactory.decodeFile(actualFile.getAbsolutePath()));
                    int file_size = Integer.parseInt(String.valueOf(actualFile.length()/1024));
                    int result_file_size = Integer.parseInt(String.valueOf(imageZipperFile.length()/1024));
                    String base64=ImageZipper.getBase64forImage(imageZipperFile);
                    result.setImageBitmap(BitmapFactory.decodeFile(imageZipperFile.getAbsolutePath()));
                    Bitmap b=new ImageZipper(MainActivity.this).compressToBitmap(actualFile);
                    originalSize.setText(file_size+" Kb");
                    compressedSize.setText(result_file_size+" Kb");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.show();

    }

}
