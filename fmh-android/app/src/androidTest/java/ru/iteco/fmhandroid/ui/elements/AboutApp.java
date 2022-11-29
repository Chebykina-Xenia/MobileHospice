package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

public class AboutApp {
    public static ViewInteraction versionValue = onView(withText("1.0.0"));
    public static ViewInteraction privacyPolicyValue = onView(withText("https://vhospice.org/#/privacy-policy/"));
    public static ViewInteraction termsOfUseValue = onView(withText("https://vhospice.org/#/terms-of-use"));
    public static ViewInteraction infoLabel = onView(withText("© I-Teco, 2022"));
}
