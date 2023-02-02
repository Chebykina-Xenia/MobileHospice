package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.action.ViewActions.click;

import ru.iteco.fmhandroid.ui.elements.Quotes;

public class QuotePage {

    public static void viewQuote() {
        // просмотр цитаты и её описания
        Quotes.missionTitleText.check(matches(isDisplayed()));
        Quotes.missionTitleText.perform(click());
    }

    public static void checkQuoteDisplay() {
        //проверка отображения цитаты и описания
        Quotes.descriptionText.check(matches(isDisplayed()));
    }
}
