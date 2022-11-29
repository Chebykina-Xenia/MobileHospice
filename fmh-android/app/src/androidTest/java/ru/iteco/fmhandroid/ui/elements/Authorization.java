package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class Authorization {

    public static ViewInteraction authorization = onView(withText("Authorization"));
    public static ViewInteraction loginInput = onView(withHint("Login")); // ожидаем появление нужного элемента
    public static ViewInteraction passwordInput = onView(withHint("Password"));
    public static ViewInteraction signInButton = onView(withId(R.id.enter_button));
}
