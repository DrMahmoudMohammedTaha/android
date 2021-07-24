package course.examples.audiovideo.camera;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AudioVideoCameraActivity extends Activity implements
OnGesturePerformedListener{
	//new for gesture
	private static final String HFSA = "hfaa";
	private GestureLibrary mLibrary;
	
	
	private static final int PREVIEW_PAUSE = 2000;
	private static final String TAG = "AudioVideoCameraActivity";

	private Camera mCamera;
	private LinearLayout mFrame;
	private SurfaceHolder mSurfaceHolder;
	private boolean mIsPreviewing;

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mFrame = (LinearLayout) findViewById(R.id.frame);

		// Disable touches on mFrame
		mFrame.setEnabled(false);
		
//new for gesture		
		mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!mLibrary.load()) {
			finish();
		}
		// Make this the target of gesture detection callbacks
				GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.gestures_overlay);
				gestureView.addOnGesturePerformedListener(this);

		
		// Setup SurfaceView for previewing camera image
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.cameraView);

		// Get SurfaceHolder for accessing the SurfaceView's Surface
		mSurfaceHolder = surfaceView.getHolder();

		// Set callback Object for the SurfaceHolder
		mSurfaceHolder.addCallback(mSurfaceHolderCallback);

	}
	
	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

		// Get gesture predictions
		ArrayList<Prediction> predictions = mLibrary.recognize(gesture);

		// Get highest-ranked prediction
		if (predictions.size() > 0) {
			Prediction prediction = predictions.get(0);

			// Ignore weak predictions

			if (prediction.score > 2.0) {
				if (prediction.name.equals(HFSA)) {

					// Take picture
					// Pass in shutterCallback and PictureCallback Objects
					mCamera.takePicture(mShutterCallback, null,
							mPictureCallback);

				}  else {
					Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT)
							.show();

				}
			} else {
				Toast.makeText(this, "No prediction", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
	@Override
	protected void onResume() {
		super.onResume();

		if (null == mCamera) {
			try {

				// Returns first back-facing camera or null if no camera is
				// available.
				// May take a long time to complete
				// Consider moving this to an AsyncTask
				mCamera = Camera.open();

			} catch (RuntimeException e) {
				Log.e(TAG, "Failed to acquire camera");
			}

			// Ensure presence of camera or finish()
			if (null == mCamera)
				finish();
		}
	}

	@Override
	protected void onPause() {

		// Disable touches on mFrame
		mFrame.setEnabled(false);

		// Shutdown preview
		stopPreview();

		// Release camera resources
		releaseCameraResources();

		super.onPause();

	}

	// Start the preview
	private void startPreview() {
		if (null != mCamera) {
			try {
				mCamera.startPreview();
				mIsPreviewing = true;
			} catch (Exception e) {
				Log.e(TAG, "Failed to start preview");
			}
		}
	}

	// Shutdown preview
	private void stopPreview() {
		if (null != mCamera && mIsPreviewing) {
			try {
				mCamera.stopPreview();
				mIsPreviewing = false;
			} catch (Exception e) {
				Log.e(TAG, "Failed to stop preview");
			}
		}
	}

	// Release camera so other applications can use it.
	private void releaseCameraResources() {
		if (null != mCamera) {
			mCamera.release();
			mCamera = null;
		}
	}

	// SurfaceHolder callback Object
	SurfaceHolder.Callback mSurfaceHolderCallback = new SurfaceHolder.Callback() {
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// Do nothing
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

			if (mSurfaceHolder.getSurface() == null) {
				return;
			}

			// Disable touches on mFrame
			mFrame.setEnabled(false);

			// Shutdown current preview
			stopPreview();

			setCameraParameters(width, height);

			// Initialize preview display
			try {
				mCamera.setPreviewDisplay(holder);
			} catch (IOException e) {
				Log.e(TAG, "Failed to set preview display in ");
			}

			// Start preview
			try {
				startPreview();
				mFrame.setEnabled(true);
			} catch (RuntimeException e) {
				Log.e(TAG, "Failed to start preview in surfaceChanged()");
			}
		}

		// Change camera parameters
		private void setCameraParameters(int width, int height) {

			// Get camera parameters object
			Camera.Parameters p = mCamera.getParameters();

			// Find closest supported preview size
			Camera.Size bestSize = findBestSize(p, width, height);

			// FIX - Should lock in landscape mode?

			int tmpWidth = bestSize.width;
			int tmpHeight = bestSize.height;

			if (bestSize.width < bestSize.height) {
				tmpWidth = bestSize.height;
				tmpHeight = bestSize.width;
			}

			p.setPreviewSize(tmpWidth, tmpHeight);
			mCamera.setParameters(p);
		}

		// Determine the largest supported preview size
		private Camera.Size findBestSize(Camera.Parameters parameters,
				int width, int height) {

			List<Camera.Size> supportedSizes = parameters
					.getSupportedPreviewSizes();

			Camera.Size bestSize = supportedSizes.remove(0);

			for (Camera.Size size : supportedSizes) {
				if ((size.width * size.height) > (bestSize.width * bestSize.height)) {
					bestSize = size;
				}
			}

			return bestSize;
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// Do Nothing
		}
	};

	// Plays system shutter Sound
	Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {
		@Override
		public void onShutter() {
			// Do nothing
		}
	};

	// Freeze the Preview for a few seconds and then restart the preview
	Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			// Preview stopped by system
			mIsPreviewing = false;

			try {
				// Give the user some time to view the image
				Thread.sleep(PREVIEW_PAUSE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Would normally save the image here

			// Restart preview
			startPreview();
		}

	};

}