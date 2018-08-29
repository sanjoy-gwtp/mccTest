package com.mcctest.utills;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AppConvertions {

    public static ArrayList<String> allPath  = new ArrayList<>();

    public static byte[] convertFileToByteArray(File f) {
        byte[] byteArray = null;
        try {

            File fff = f;


//            File fff = new File("/path/to/file");
            FileInputStream fileInputStream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytes = 0;


            while ((bytes = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
            }

            fileInputStream.close();
            out.flush();

            byteArray = out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

//    public static byte[] convertImageToByte(Context context, Uri uri){
//        byte[] data = null;
//        try {
//            ContentResolver cr = context.getContentResolver();
//            InputStream inputStream = cr.openInputStream(uri);
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            data = baos.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }

    public static byte[] convertImageToByte(Context context, String uri){
        ContentResolver cr = context.getContentResolver();
        InputStream inputStream=null;
        try {
            inputStream = new FileInputStream(new File(uri));
        }catch (IOException e){
            e.printStackTrace();
        }
        return readBytes(inputStream);
    }

    public static byte[] readBytes(InputStream inputStream){
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return byteBuffer.toByteArray();
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    /** Create a File for saving an image or video */
    public static File getOutputVideoFile(boolean isImage){

        // Check that the SDCard is mounted
        File mediaStorageDir = new File(Environment.DIRECTORY_DOWNLOADS + "/SignatureVideo");

        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        // Create the storage directory(MyCameraVideo) if it does not exist


//            if (! mediaStorageDir.mkdirs()){
//
//                Log.d("MyCameraVideo", "Failed to create directory SignatureVideo.");
//                return null;
//            }



        // Create a media file name

        // For unique file name appending current timeStamp with file name
        Date date= new Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(date.getTime());

        if (isImage){
            File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
            return mediaFile;
        }else {
            File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
            return mediaFile;
        }

//             File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                "VID_"+ timeStamp + ".mp4");
//        return mediaFile;

    }

    public static Boolean deleteVideoTempFiles() {

        if (allPath!=null && allPath.size()>0) {

            for (int i = 0; i < allPath.size(); i++) {
                new File(allPath.get(i)).delete();
            }
            return true;
        }
        return false;
    }

    public static String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    public static String getDate(String stringDate) {

        Date currentDate = new Date(Long.parseLong(stringDate));
        DateFormat df = new SimpleDateFormat("dd/MM/yy");

        //formatted value of current Date
        String date = "" + df.format(currentDate);

        return date;
    }

    public static String getFormattedBalance(String amount) {

        if (!amount.isEmpty()) {

            String balance = "";
//        long number = Long.parseLong(amount);
//        Log.e("number", "" + number);
//        String balance = NumberFormat.getNumberInstance(Locale.US).format(number);

//        String number = "10005055.574";
            boolean hasFractionalPart = false;
            DecimalFormat df;
            DecimalFormat dfnd;
            df = new DecimalFormat("##,##,##,##,##,##,###.##");
            df.setDecimalSeparatorAlwaysShown(true);
            dfnd = new DecimalFormat("##,##,##,##,##,##,###");

            if (amount.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator()))) {
                hasFractionalPart = true;
            } else {
                hasFractionalPart = false;
            }


            double number = Double.parseDouble(amount);

            if (hasFractionalPart) {
                balance = (df.format(number));
            } else {
                balance = (dfnd.format(number));
            }


//    DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
//        String balance = df.format(number);
            return balance;
        } else {
            return amount;
        }
    }

    public static String getStringWithoutSeparator(String str) {
        return str.replaceAll(",", "");
    }


}