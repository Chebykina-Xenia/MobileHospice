package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
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
    //Авторизация с валидными данными
    public void shouldLogInWithValidData() throws InterruptedException {
        AuthorizationPage.logIn(validLogin, validPassword);
        MenuPage.checkTradeMark();
        MenuPage.logOut();
    }

    @Test
    //Авторизация с невалидными данными
    public void shouldLogInWithInValidData() throws InterruptedException {
        AuthorizationPage.logIn(invalidLogin, invalidPassword);
        onView(withText("Wrong login or password"));
    }

    @Test
    //Оставляем поля пустами и нажимаем кнопку Авторизации
    public void shouldTryLogInWithEmptyField() throws InterruptedException {
        AuthorizationPage.clickInButton();
        onView(withText(R.string.empty_login_or_password));
    }
}
