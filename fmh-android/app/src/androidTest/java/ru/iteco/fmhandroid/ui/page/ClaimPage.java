package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.ui.date.DataHelper;
import ru.iteco.fmhandroid.ui.elements.Claim;

public class ClaimPage {
    //начало создания заявки
    public static void startCreationClaim() {
        Claim.addNewClaimButton.perform(click());
        Claim.titleClaimsCreating.check(matches(isDisplayed()));
    }

    public static void fillClaimFields(String emptyTitle, String title, String emptyExecutor, String choiceOfExecutor, String chosenExecutor, String executor, String emptyDate, String emptyTime, String withDialPadOrTextInput, String saveOrCancelTime, String emptyDescription, String description) {
        // позиции всех исполнителей
        Integer executorPosition = null;
        if (chosenExecutor == "Ivanov Ivan Ivanovich") {
            executorPosition = 0;
        } else if (chosenExecutor == "Иванов Данил Данилович") {
            executorPosition = 1;
        } else if (chosenExecutor == "Петров Егор Егорович") {
            executorPosition = 2;
        } else if (chosenExecutor == "Сидоров Дмитрий Дмитриевич") {
            executorPosition = 3;
        } else if (chosenExecutor == "Тестов Тест Тестович") {
            executorPosition = 4;
        } else if (chosenExecutor == "Netology Diplom QAMID") {
            executorPosition = 5;
        }

        // заполнение поля "Тема"
        if (emptyTitle == "no") {
            Claim.titleTextInputClaim.perform(replaceText(title));
            Claim.titleTextInputClaim.check(matches(withText(title)));
        }
        // заполнение поля "Исполнитель"
        if (emptyExecutor == "no") {
            if (choiceOfExecutor == "yes") {
                Claim.executor.perform(click());
                Espresso.onData(Matchers.anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(executorPosition).perform(ViewActions.click());
            } else {
                Claim.executorTextInput.perform(replaceText(executor));
                Claim.executorTextInput.check(matches(withText(executor)));
            }
        }
        // заполнение поля "Дата"
        if (emptyDate == "no") {
            Claim.dateClaim.perform(click());
            Claim.okButton.perform(click());
        }
        // заполнение поля "Время"
        if (emptyTime == "no") {
            if (withDialPadOrTextInput == "dial") {
                Claim.timeClaim.perform(click());
                if (saveOrCancelTime == "save") {
                    Claim.okButton.perform(click());
                } else {
                    Claim.cancelButton.perform(click());
                }
            }
        }
        // заполнение поля "Описание"
        if (emptyDescription == "no") {
            Claim.descriptionTextInputClaim.perform(replaceText(description));
            Claim.descriptionTextInputClaim.check(matches(withText(description)));
        }
    }

    //сохранение заявки
    public static void saveClaim() {
        Claim.saveButton.perform(click());
    }

    //проверка созданной заявки
    public static void checkCreatedClaimInClaimsBlock(String title) {
        DataHelper.isDisplayedSwipe(onView(withText(title)), 1, true);
    }

    //проверка текущего статуса и кликаем на кнопку редактирования статуса
    //выбираем новый статус
    public static void editStatusClaim(String status, String newStatus) {
        Claim.statusClaimNow.check(matches(withText(status)));
        Claim.statusEdit.perform(scrollTo()).perform(click());
        onView(withText(newStatus)).perform(click());
    }

    //выбираем - по какому статусу фильтруем
    public static void filterStatusClaim(ViewInteraction status) throws InterruptedException {
        Claim.buttonClaimFilter.perform(click());
        status.perform(click());
        Claim.okButton.perform(click());
        Thread.sleep(3000);
    }

    //добавление комментария при смене статуса заявки
    public static void addCommentStatusClaim(String textComment, String newStatus) {
        onView(withHint("Comment")).perform(replaceText(textComment));
        Claim.okButton.perform(click());
    }

    //открываем первую заявку
    public static void openFirstClaim() {
        Claim.firstClaim.perform(click());
    }

    //проверяем статус
    public static void checkStatusClaim(String newStatus) {
        getStatusLabel().check(matches(withText(newStatus)));
    }

    //выводим текущий статус
    public static ViewInteraction getStatusLabel() {
        return onView(Claim.statusLabelText);
    }

    //добавление комментария
    public static void addCommentClaim(String commentText) throws InterruptedException {
        Claim.addComment.perform(scrollTo()).perform(click());
        onView(withHint("Comment")).perform(replaceText(commentText));
        Claim.saveButton.perform(click());
        Thread.sleep(3000);
    }

    //проверка созданного комментария
    public static void checkCreatedComment(String commentText) {
        DataHelper.isDisplayedSwipe(onView(withHint(commentText)), 2, true);
    }
}
