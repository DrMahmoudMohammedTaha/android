package course.examples.datamanagement.preferencefragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class ViewAndUpdatePreferencesActivity extends Activity {

	private static final String USERNAME = "uname";
	private static final String USERAge = "uAge";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.user_prefs_fragment);

	}

	// Fragment that displays the username preference
	public static class UserPreferenceFragment extends PreferenceFragment {

		protected static final String TAG = "UserPrefsFragment";
		private OnSharedPreferenceChangeListener mListener , mAgeListener;
		private Preference mUserNamePreference , mAgePreference;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			// Load the preferences from an XML resource
			addPreferencesFromResource(R.xml.user_prefs);
			// Get the username Preference
			mUserNamePreference = (Preference) getPreferenceManager()
					.findPreference(USERNAME);

			// Get the age Preference
						mAgePreference = (Preference) getPreferenceManager()
								.findPreference(USERAge);
						
			// Attach a listener to update summary when username changes
			mListener = new OnSharedPreferenceChangeListener() {
				@Override
				public void onSharedPreferenceChanged(
						SharedPreferences sharedPreferences, String key) {
					mUserNamePreference.setSummary(sharedPreferences.getString(
							USERNAME, "None Set"));
				}
			};

			
			// Attach a listener to update summary when username changes
						mAgeListener = new OnSharedPreferenceChangeListener() {
							@Override
							public void onSharedPreferenceChanged(
									SharedPreferences sharedPreferences, String key) {
								mAgePreference.setSummary(sharedPreferences.getString(
										USERAge, "None Set"));
							}
						};

						
			// Get SharedPreferences object managed by the PreferenceManager for
			// this Fragment
			SharedPreferences prefs = getPreferenceManager()
					.getSharedPreferences();

			// Register a listener on the SharedPreferences object
			prefs.registerOnSharedPreferenceChangeListener(mListener);

			prefs.registerOnSharedPreferenceChangeListener(mAgeListener);
			// Invoke callback manually to display the current username
			mListener.onSharedPreferenceChanged(prefs, USERNAME);
			mAgeListener.onSharedPreferenceChanged(prefs, USERAge);
		}

	}
}
