package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.elements.AboutApp;

public class AboutAppPage {
    //Проверяем что на странице О приложении есть вся информация
    public static void checkAboutAppInfIsFull() {
        AboutApp.versionValue.check(matches(isDisplayed()));
        AboutApp.privacyPolicyValue.check(matches(isDisplayed()));
        AboutApp.termsOfUseValue.check(matches(isDisplayed()));
        AboutApp.infoLabel.check(matches(isDisplayed()));
    }
}
