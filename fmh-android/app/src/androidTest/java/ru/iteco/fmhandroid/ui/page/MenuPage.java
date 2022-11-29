package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import ru.iteco.fmhandroid.ui.elements.AboutApp;
import ru.iteco.fmhandroid.ui.elements.Menu;
import ru.iteco.fmhandroid.ui.elements.Quotes;

public class MenuPage {

    //Выход из личного кабинета
    public static void logOut() {
        Menu.authorizationButton.perform(click());
        Menu.logOutButton.check(matches(isDisplayed()));
        Menu.logOutButton.perform(click());
    }

    //Проверряем видимость логотипа приложения
    public static void checkTradeMark() {
        Menu.tradeMark.check(matches(isDisplayed()));
    }

    //Переходим на страницу О приложении
    public static void goToAboutApp() {
        Menu.menuButton.perform(click());
        Menu.aboutOfMenu.check(matches(isDisplayed()));
        Menu.aboutOfMenu.perform(click());
        AboutApp.versionValue.check(matches(isDisplayed()));
    }

    public static void goQuotes() {
        //переходим в цитаты - нажимаем на бабочку
        Menu.goQuotesButton.perform(click());
        Quotes.missionTitleText.check(matches(isDisplayed()));
    }
}
