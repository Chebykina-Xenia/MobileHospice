package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class AboutApp {
    public static ViewInteraction versionValue = onView(withText(R.string.version));
    //public static ViewInteraction privacyPolicyValue = onView(withText("https://vhospice.org/#/privacy-policy/"));
    public static ViewInteraction privacyPolicyValue = onView(withText(R.string.privacy_policy_url));
    //public static ViewInteraction termsOfUseValue = onView(withText("https://vhospice.org/#/terms-of-use"));
    public static ViewInteraction termsOfUseValue = onView(withText(R.string.terms_of_use_url));
    public static ViewInteraction infoLabel = onView(withText(R.string.company_info));
    public static Matcher<View> aboutPrivacyPolicyValue = withText(R.string.privacy_policy);
    public static Matcher<View> aboutTermsOfUseValue = withText(R.string.terms_of_use);
}
