package ru.iteco.fmhandroid.ui.test;
import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.rule.ActivityTestRule;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;

public class RequestTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

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



    //Успешное добавление заявки со статусом в работе

    //Добавляем заявку - отсавляем поля пустыми

    //Добавление заявки время меньше системного

    //Добавление заявки без заполнения поля Исполнитель

    //Отмена заявки в статусе Открыта

    //Перевод заявки из статуса открыта в статус в Работе

    //Сбросить заявку со статусом В работе

    //Переводим заявку в статус Выполнена

    //Добавление комментария к заявке

}
