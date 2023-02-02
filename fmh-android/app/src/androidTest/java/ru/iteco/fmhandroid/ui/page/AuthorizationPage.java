package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.ui.elements.Authorization;

public class AuthorizationPage {
    //Отображение окна авторизации
    public static void isAuthorizationWindow() {
        Authorization.authorization.check(matches(isDisplayed()));
    }

    //Выполняем вход в аккаунт
    public static void logIn(String login, String password) throws InterruptedException {
        Authorization.loginInput.perform(replaceText(login));
        Authorization.loginInput.check(matches(withText(login)));
        Authorization.passwordInput.perform(replaceText(password));
        Authorization.passwordInput.check(matches(withText(password)));
        Authorization.signInButton.perform(click());
        Thread.sleep(4000);
    }

    //Кликаем на конпку Войти
    public static void clickInButton() {
        Authorization.signInButton.perform(click());
    }


}

