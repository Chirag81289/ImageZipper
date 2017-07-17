package com.developers.imagezipper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by dell on 15/7/17.
 */

public class Compressor {

    private static int height, width, inSampleSize;
    private static String encodedfile;

    public static File compressImageFile(File imageFile, int reqHeight, int reqWidth,
                                         String filePath, int quality,
                                         Bitmap.CompressFormat compressFormat, int orientation) throws IOException {
        FileOutputStream fileOutputStream = null;
        File file = new File(filePath).getParentFile();
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(filePath);
            decodeBitmapAndCompress(imageFile, reqHeight, reqWidth,orientation)
                    .compress(compressFormat, quality, fileOutputStream);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        return new File(filePath);
    }

    public static Bitmap decodeBitmapAndCompress(File imageFile, int reqHeight, int reqWidth,int reqOrientation)
            throws IOException {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        //Calculating Sample Size
        options.inSampleSize = calculateSampleSize(options, reqHeight, reqWidth);

        options.inJustDecodeBounds = false;

        Bitmap scaledBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);

        ExifInterface exifInterface;
        exifInterface = new ExifInterface(imageFile.getAbsolutePath());
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
        Matrix matrix = new Matrix();
        if (orientation == 6) {
            matrix.postRotate(90);
        } else if (orientation == 3) {
            matrix.postRotate(180);
        } else if (orientation == 8) {
            matrix.postRotate(270);
        }
        if(reqOrientation>0){
            matrix.postRotate(reqOrientation);
        }
        scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth()
                , scaledBitmap.getHeight(), matrix, true);
        return scaledBitmap;
    }


    private static int calculateSampleSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {

        height = options.outHeight;
        width = options.outWidth;
        inSampleSize = 1;

        int halfHeight = height / 2;
        int halfWidth = width / 2;

        if (height > reqHeight || width > reqWidth) {
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static String getBase64forCompressedImage(File compressFile){

        FileInputStream fileInputStreamReader = null;
        byte[] bytes = new byte[(int)compressFile.length()];
        try {
            fileInputStreamReader = new FileInputStream(compressFile);
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.encodeToString(bytes,Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedfile;
    }



}
