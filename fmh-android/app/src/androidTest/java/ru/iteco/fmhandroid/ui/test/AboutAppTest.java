package ru.iteco.fmhandroid.ui.test;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AboutAppPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

@RunWith(AllureAndroidJUnit4.class)

public class AboutAppTest {

    // указываем какое приложение будем запускать
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        Thread.sleep(7000);
        try {
            AuthorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationPage.logIn("login2", "password2");
    }

    @Test
    @DisplayName("Проверяем информацию о приложении")
    public void shouldBeInfInAboutApp() {
        MenuPage.goToAboutApp();
        AboutAppPage.checkAboutAppInfIsFull();
    }

    @Test
    @DisplayName("Переход по ссылке Политики конфиденциальности") // БАГ
    public void shouldViewPrivacyPolicy() {
        MenuPage.goToAboutApp();
        AboutAppPage.clickPrivacyPolicy();
    }

    @Test
    @DisplayName("Переход по ссылке Пользовательское соглашение") // БАГ
    public void shouldViewUserAgreement() {
        MenuPage.goToAboutApp();
        AboutAppPage.clickUserAgreement();
    }

}
