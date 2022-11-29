package ru.iteco.fmhandroid.ui.elements;

import androidx.test.espresso.ViewInteraction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class Quotes {
    //Цитаты
    public static ViewInteraction missionTitleText = onView(withText("Хоспис в своем истинном понимании - это творчество"));
    public static ViewInteraction descriptionText = onView(withText("Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий."));

}
