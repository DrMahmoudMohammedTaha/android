package course.examples.graphics.bubbleprogram;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class BubbleActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.frame);

		final ImageView bubbleView = new ImageView(getApplicationContext());
		relativeLayout.addView(bubbleView);
		bubbleView
				.setImageDrawable(getResources().getDrawable(R.drawable.b128));

		int width = (int) getResources().getDimension(R.dimen.image_width);
		int height = (int) getResources().getDimension(R.dimen.image_height);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				width, height);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);

		bubbleView.setLayoutParams(params);

		Timer clock = new Timer();
		clock.schedule(new TimerTask() {
			
			@Override
			public void run() {
				bubbleView.post(new Runnable() {
					
					@Override
					public void run() {
				int ranNum = new Random().nextInt(8)+1 ;
				switch (ranNum) {
				case 1:
					ranNum = R.drawable.img1;
					break;
				case 2:
					ranNum = R.drawable.img2;
					break;
					case 3:
						ranNum = R.drawable.img3;
						break;
						case 4:
							ranNum = R.drawable.img4;
							break;
							case 5:
								ranNum = R.drawable.img5;
								break;
							case 6:
								ranNum = R.drawable.img6;
								break;
							case 7:
								ranNum = R.drawable.img7;
								break;
				default:
					ranNum = R.drawable.img8;
					break;
				}
				bubbleView.setImageDrawable(getResources().getDrawable(ranNum));

				int width = (int) getResources().getDimension(R.dimen.image_width);
				int height = (int) getResources().getDimension(R.dimen.image_height);

				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
						width, height);
				params.addRule(RelativeLayout.CENTER_IN_PARENT);

				bubbleView.setLayoutParams(params);

				
					}
				});
				
			}
		}, 1000 , 1000) ;
	}
}