package ru.iteco.fmhandroid.ui.test;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

@RunWith(AllureAndroidJUnit4.class)
//тестирование Авторизации
public class AuthorizationTest {
    // указываем какое приложение будем запускать. Cмотрим activity в AndroidManifest.xml
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";
    String invalidLogin = "login3";
    String invalidPassword = "password33";

    @Before
    public void sleep() throws InterruptedException {
        Thread.sleep(7000);
        try {
            AuthorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            MenuPage.logOut();
        }
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    public void shouldLogInWithValidData() throws InterruptedException {
        AuthorizationPage.logIn(validLogin, validPassword);
        MenuPage.checkTradeMark();
    }

    @Test
    @DisplayName("Авторизация с невалидными данными")
    public void shouldLogInWithInValidData() throws InterruptedException {
        AuthorizationPage.logIn(invalidLogin, invalidPassword);
        MenuPage.checkDisplayErrorMessage(activityTestRule, "Wrong login or password");
    }

    @Test
    @DisplayName("Авторизация с пустыми полями")
    public void shouldTryLogInWithEmptyField() throws InterruptedException {
        AuthorizationPage.clickInButton();
        MenuPage.checkDisplayErrorMessage(activityTestRule, "Login or password cannot be empty");
    }
}
