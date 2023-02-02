package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class AboutApp {
    public static ViewInteraction versionValue = onView(withText("1.0.0"));
    public static ViewInteraction privacyPolicyValue = onView(withText("https://vhospice.org/#/privacy-policy/"));
    public static ViewInteraction termsOfUseValue = onView(withText("https://vhospice.org/#/terms-of-use"));
    public static ViewInteraction infoLabel = onView(withText("© I-Teco, 2022"));
    public static Matcher<View> aboutPrivacyPolicyValue = withId(R.id.about_privacy_policy_value_text_view);
    public static Matcher<View> aboutTermsOfUseValue = withId(R.id.about_terms_of_use_value_text_view);

}
