package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import android.content.Intent;
import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;

import ru.iteco.fmhandroid.ui.date.ViewActions;
import ru.iteco.fmhandroid.ui.elements.AboutApp;

public class AboutAppPage {
    //Проверяем что на странице О приложении есть вся информация
    public static void checkAboutAppInfIsFull() {
        AboutApp.versionValue.check(matches(isDisplayed()));
        AboutApp.privacyPolicyValue.check(matches(isDisplayed()));
        AboutApp.termsOfUseValue.check(matches(isDisplayed()));
        AboutApp.infoLabel.check(matches(isDisplayed()));
    }

    public static void clickPrivacyPolicy() {
        String uriPrivacyPolicy = "https://vhospice.org/#/privacy-policy/";
        Intents.init();
        onView(AboutApp.aboutPrivacyPolicyValue).perform(click());
        SystemClock.sleep(3000);
        intended(allOf(hasData(uriPrivacyPolicy), hasAction(Intent.ACTION_VIEW)));
        Intents.release();
    }

    public static ViewInteraction getHeaderPrivacyPolicyPage() {
        return onView(AboutApp.aboutPrivacyPolicyValue);
    }

    //Проверка что загрузилась страница с Политикой конфиденциальности.
    public static void checkPrivacyPolicy() {
        String headerPrivacyPolicyPage = "Privacy policy";
        getHeaderPrivacyPolicyPage().check(matches(withText(headerPrivacyPolicyPage)));
    }

    public static void clickUserAgreement() {
        String uriTermsOfUse = "https://vhospice.org/#/terms-of-use";
        Intents.init();
        onView(AboutApp.aboutTermsOfUseValue).perform(click());
        SystemClock.sleep(3000);
        intended(allOf(hasData(uriTermsOfUse), hasAction(Intent.ACTION_VIEW)));
        Intents.release();
    }

    public static ViewInteraction getHeaderTermsOfUsePage() {
        return onView(AboutApp.aboutTermsOfUseValue);
    }

    //Проверка что загрузилась страница с Пользовательским соглашением.
    public static void checkTermsOfUse() {
        String headerTermsOfUsePege = "Terms of use";
        getHeaderTermsOfUsePage().check(matches(withText(headerTermsOfUsePege)));
    }
}
