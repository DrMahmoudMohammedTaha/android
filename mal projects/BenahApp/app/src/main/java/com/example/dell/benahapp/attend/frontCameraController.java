package com.example.dell.benahapp.attend;


import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceView;

import java.io.IOException;

public class frontCameraController {

    private static Context context;
    private static boolean hasCamera;
    private static Camera camera;
    private static int cameraId;
    public  static byte previous [] ;
    private static byte current [] ;
    public static boolean similar = false;



    public static void setContext(Context context) {
        frontCameraController.context = context;
    }


    public static void compareStates()
    {
        int win = 0;
        int lose = 0 ;
        int tmp = Math.min( current.length , previous.length);
        for (int i = 2; i < tmp-2; i++) {
            if( Math.abs(previous[i] - current[i]) < 10)
                win++;
            else
                lose++;
        }
        Log.v("xtest pixels", win + " " + (float)win/(float)current.length);
        similar = ( ((float)win/(float)current.length) > 0.05);
    }

    public static void startFrontCameraController(Context c){
        context = c.getApplicationContext();

        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            cameraId = getFrontCameraId();

            if(cameraId != -1){
                hasCamera = true;
                getCameraInstance();
            }else{
                hasCamera = false;
            }
        }else{
            hasCamera = false;
        }

    }

    public static boolean hasCamera(){
        return hasCamera;
    }

    public static  void getCameraInstance(){
        camera = null;

        if(hasCamera){
            try{
                camera = Camera.open(cameraId);
                prepareCamera();
            }
            catch(Exception e){
                hasCamera = false;
            }
        }
    }

    public static void takePicture(){


        startFrontCameraController(context);

        if(hasCamera){
            camera.takePicture(null,null,mPicture);
        }
    }

    public static void releaseCamera(){
        if(camera != null){
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    private static int getFrontCameraId(){
        int camId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        android.hardware.Camera.CameraInfo ci = new android.hardware.Camera.CameraInfo();

        for(int i = 0;i < numberOfCameras;i++){
            Camera.getCameraInfo(i,ci);
            if(ci.facing == android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT){
                camId = i;
            }
        }

        return camId;
    }

    private static void prepareCamera(){
        SurfaceView view = new SurfaceView(context);

        try{
            camera.setPreviewDisplay(view.getHolder());
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        SurfaceTexture st = new SurfaceTexture(Service.MODE_PRIVATE);
        try {
            camera.setPreviewTexture(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();

        Camera.Parameters params = camera.getParameters();
        params.setJpegQuality(100);

        camera.setParameters(params);

    }

    private static android.hardware.Camera.PictureCallback mPicture = new android.hardware.Camera.PictureCallback(){

        @Override
        public void onPictureTaken(byte[] bytes, android.hardware.Camera camera) {

            current = bytes;
            attendance_servant.safe = true;
            Log.v("xtest camera == null ",(camera == null)+"");
            releaseCamera();
            QR_factory.open_QR_reader(attendance_servant.app);
        }


    };

    public static void checker()
    {

        if(previous != null)
        {
            compareStates();
        }
            previous = current.clone();

        current = null;

    }

}
