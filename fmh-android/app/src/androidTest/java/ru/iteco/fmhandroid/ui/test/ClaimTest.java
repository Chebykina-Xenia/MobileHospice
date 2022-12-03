package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.date.DataHelper;
import ru.iteco.fmhandroid.ui.elements.Claim;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.ClaimPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;
@RunWith(AllureAndroidJUnit4.class)

public class ClaimTest {

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

    String withDialPadOrTextInput = "dial";
    String saveOrCancelTime = "save";

    //Успешное добавление заявки со статусом в работе (in progress)
    //заполняем все поля
    @Test
    public void shouldCreateClaimInProgress() throws InterruptedException {
        String emptyTitle = "no";
        String title = "Уборка территории зимой";
        String emptyExecutor = "no";
        String withExecutorChoice = "yes";
        String chosenExecutor = "Иванов Данил Данилович";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String emptyDescription = "no";
        String description = "Not description";
        MenuPage.goClaims();
        ClaimPage.startCreationClaim();
        ClaimPage.fillClaimFields(emptyTitle, title, emptyExecutor, withExecutorChoice, chosenExecutor, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimPage.saveClaim();

        ClaimPage.filterStatusClaim(Claim.openStatusFilter);
        ClaimPage.checkCreatedClaimInClaimsBlock(title);
    }

    //Переводим заявку в статус Выполнена
    @Test
    public void shouldClaimExecute() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        Claim.secondClaim.perform(click());
        ClaimPage.editStatusClaim("Open", "take to work");
        ClaimPage.editStatusClaim("In progress", "To execute");
        ClaimPage.addCommentStatusClaim("Test", "Executed");
    }

    //Добавляем заявку - отсавляем поля пустыми
    @Test
    public void shouldNotCreateClaim() {
        String emptyTitle = "yes";
        String emptyExecutor = "yes";
        String withExecutorChoice = "yes";
        String emptyDate = "yes";
        String emptyTime = "yes";
        String emptyDescription = "yes";
        MenuPage.goClaims();
        ClaimPage.startCreationClaim();
        ClaimPage.fillClaimFields(emptyTitle, null, emptyExecutor, withExecutorChoice, null, null, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, null);
        ClaimPage.saveClaim();
        onView(withText("Fill empty fields"));
    }


    //Добавляем заявку без заполнения поля Исполнитель
    //статус заявки будет open
    @Test
    public void shouldNotExecuteClaim() throws InterruptedException {
        String emptyTitle = "no";
        String title = "Уборка снега зимой not executor";
        String emptyExecutor = "yes";
        String withExecutorChoice = "no";
        String executor = "no";
        String emptyDate = "no";
        String emptyTime = "no";
        String emptyDescription = "no";
        String description = "Not description";
        MenuPage.goClaims();
        ClaimPage.startCreationClaim();
        ClaimPage.fillClaimFields(emptyTitle, title, emptyExecutor, withExecutorChoice, null, executor, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        ClaimPage.saveClaim();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        ClaimPage.checkCreatedClaimInClaimsBlock(title);
    }

    //Отмена заявки в статусе Открыта - то есть перевод заявки в статус Cancel
    //Иногда кнопка Cancel не доступна. Не совсем понимаю...от чего это зависит. Возможно баг
    @Test
    public void shouldClaimCancel() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        Claim.firstClaim.perform(click());
        ClaimPage.editStatusClaim("Open", "Cancel");
        Claim.statusClaimNow.check(matches(withText("Canceled")));
    }

    //Перевод заявки из статуса открыта в статус в Работе
    @Test
    public void shouldInProgress() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        Claim.secondClaim.perform(click());
        ClaimPage.editStatusClaim("Open", "take to work");
        Claim.statusClaimNow.check(matches(withText("In progress")));
    }

    //Сбросить заявку со статусом В работе
    @Test
    public void shouldThrowOff() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        Claim.secondClaim.perform(click());
        ClaimPage.editStatusClaim("Open", "take to work");
        ClaimPage.editStatusClaim("In progress", "Throw off");
        ClaimPage.addCommentStatusClaim("Test", "Open");
    }

    //Добавление комментария к заявке
    @Test
    public void shouldAddComment() throws InterruptedException {
        String commentText = "По заявке 356";
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.openStatusFilter);
        Claim.firstClaim.perform(click());
        Claim.statusClaimNow.check(matches(withText("In progress")));
        Claim.addComment.perform(scrollTo()).perform(click());
        onView(withHint("Comment")).perform(replaceText(commentText));
        Claim.saveButton.perform(click());
        Thread.sleep(3000);
        DataHelper.isDisplayedSwipe(onView(withHint(commentText)), 2, true);
    }
}