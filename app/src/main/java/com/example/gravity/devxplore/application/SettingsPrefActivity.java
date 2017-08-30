package com.example.gravity.devxplore.application;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.utilities.ThemeUtil;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class SettingsPrefActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtil.onActivityCreateSetTheme(this);

        MainPreferenceFragment fragment = MainPreferenceFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
    }

    public static class MainPreferenceFragment extends PreferenceFragment {

        public static MainPreferenceFragment newInstance() {
            MainPreferenceFragment mainPreferenceFragment = new MainPreferenceFragment();
            return mainPreferenceFragment;
        }

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_main);

            // notification preference change listener
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_new_notifications_ringtone)));

            // feedback preference click listener
            Preference myPref = findPreference(getString(R.string.key_send_feedback));
            myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    sendFeedback(getActivity());
                    return true;
                }
            });
        }
        private static void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));
        }

        /**
         * A preference value change listener that updates the preference's summary
         * to reflect its new value.
         */
        private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String stringValue = newValue.toString();

                if (preference instanceof ListPreference) {
                    // For list preferences, look up the correct display value in
                    // the preference's 'entries' list.
                    ListPreference listPreference = (ListPreference) preference;
                    int index = listPreference.findIndexOfValue(stringValue);

                    // Set the summary to reflect the new value.
                    preference.setSummary(
                            index >= 0
                                    ? listPreference.getEntries()[index]
                                    : null);

                } else if (preference instanceof RingtonePreference) {
                    // For ringtone preferences, look up the correct display value
                    // using RingtoneManager.
                    if (TextUtils.isEmpty(stringValue)) {
                        // Empty values correspond to 'silent' (no ringtone).
                        preference.setSummary(R.string.pref_ringtone_silent);

                    } else {
                        Ringtone ringtone = RingtoneManager.getRingtone(
                                preference.getContext(), Uri.parse(stringValue));

                        if (ringtone == null) {
                            // Clear the summary if there was a lookup error.
                            preference.setSummary(R.string.summary_choose_ringtone);
                        } else {
                            // Set the summary to reflect the new ringtone display
                            // name.
                            String name = ringtone.getTitle(preference.getContext());
                            preference.setSummary(name);
                        }
                    }

                } else {
                    preference.setSummary(stringValue);
                }
                return true;
            }
        };

        /**
         * Email client intent to send support mail
         * Appends the necessary device information to email body
         * useful when providing support
         */
        public static void sendFeedback(Context context) {
            String body = "\n\n--Sent from \n Device OS: Android \n Device OS version: " +
                        Build.VERSION.RELEASE+"\n Device Model: " + Build.MODEL + "\n Device Manufacturer: " + Build.MANUFACTURER;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mikalil525@yahoo.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Devxplore feedback");
            intent.putExtra(Intent.EXTRA_TEXT, body);
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_email_client)));
        }
    }
}
