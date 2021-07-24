package course.examples.notification.statusbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationSubActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_activity);
		final TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
	
				Toast.makeText(getBaseContext(), "success",Toast.LENGTH_LONG).show();
	
			}});

	}
}
