package com.example.dell.benahapp.attend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


public class QR_factory {


    private static final int BLACK = 0;
    private static final int WHITE = 1;
    private static final int WIDTH = 50;
    public static String data = "";
    public static AppCompatActivity app ;
    public static String QR_data = "none";
    public static void open_QR_reader(AppCompatActivity ma) {
        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
            app = ma;
            ma.startActivityForResult(intent, 0);
        } catch (Exception e) {

            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            ma.startActivity(marketIntent);

        }

    }

    public static void create_QR_code(String STR , AppCompatActivity ma) {
       // ImageView imageView = (ImageView) ma.findViewById(R.id.imageView);
        Toast.makeText(ma, "creator here",
                Toast.LENGTH_LONG).show();
        try {
            Bitmap bitmap = encodeAsBitmap(STR);
        //    imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, WIDTH, 0, 0, w, h);
        return bitmap;
    }


    public static void process_result(int requestCode , int resultCode , Intent data) {
        if (requestCode == 0) {

            if (resultCode == app.RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                Toast.makeText(app, contents, Toast.LENGTH_LONG).show();

                QR_data = contents.substring(contents.indexOf("-"),contents.length())
                        .replaceAll("-"," ").trim();
//                frontCameraController.checker();

                attendance_servant.check_bad_student(QR_data);
                if(!QR_factory.QR_data.equals("none"))
                    data_factory.setAttendence(QR_factory.QR_data);
                data_factory.saveLast(data_factory.getLastData());
            }
            if(resultCode == app.RESULT_CANCELED){
                //handle cancel
            }
            AttendenceActivity.cameraBt.setEnabled(true);
        }
    }
}
