package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static org.hamcrest.core.AllOf.allOf;

import android.content.Intent;

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
        onView(isRoot()).perform(ViewActions.waitElement(AboutApp.aboutPrivacyPolicyValue, 10000)); // ожидаем появление нужного элемента
        onView(AboutApp.aboutPrivacyPolicyValue).perform(click()); // кликаем по ссылке Политика конфиденциальности
        intended(allOf(hasData("https://vhospice.org/#/privacy-policy/"), hasAction(Intent.ACTION_VIEW))); // переход по ссылке, открытие браузера
    }

    public static void clickUserAgreement() {
        onView(isRoot()).perform(ViewActions.waitElement(AboutApp.aboutTermsOfUseValue, 10000)); // ожидаем появление нужного элемента
        onView(AboutApp.aboutTermsOfUseValue).perform(click()); // кликаем по ссылке Пользовательское соглашение
        intended(allOf(hasData("https://vhospice.org/#/terms-of-use"), hasAction(Intent.ACTION_VIEW))); // переход по ссылке, открытие браузера
    }

}
