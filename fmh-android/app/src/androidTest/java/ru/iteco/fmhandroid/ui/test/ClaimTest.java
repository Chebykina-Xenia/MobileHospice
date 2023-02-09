package ru.iteco.fmhandroid.ui.test;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
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

    //Успешное добавление заявки со статусом в работе (in progress)
    @Test
    @DisplayName("Добавление заявки")
    public void shouldCreateClaimInProgress() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.startCreationClaim();
        ClaimPage.fillClaimFields("no", "Уборка территории зимой", "no", "yes", "Ivanov Ivan Ivanovich", "no", "no", "no", "dial", "save", "no", "Not description");
        ClaimPage.saveClaim();

        ClaimPage.filterStatusClaim(Claim.openStatusFilter);
        ClaimPage.checkCreatedClaimInClaimsBlock("Уборка территории зимой");
    }

    @Test
    @DisplayName("Добавление заявки с пустыми полями")
    public void shouldNotCreateClaim() {
        MenuPage.goClaims();
        ClaimPage.startCreationClaim();
        ClaimPage.fillClaimFields("yes", null, "yes", "yes", "Ivanov Ivan Ivanovich", null, "yes", "yes", "dial", "save", "yes", null);
        ClaimPage.saveClaim();
        MenuPage.checkDisplayErrorMessage(activityTestRule, "Fill empty fields");
    }

    //статус заявки будет open
    @Test
    @DisplayName("Добавление заявки без исполнителя")
    public void shouldNotExecuteClaim() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.startCreationClaim();
        ClaimPage.fillClaimFields("no", "Уборка территории зимой", "yes", "yes", "Ivanov Ivan Ivanovich", null, "no", "no", "dial", "save", "no", "Not description");
        ClaimPage.saveClaim();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        ClaimPage.checkCreatedClaimInClaimsBlock("Уборка территории зимой");
    }

    //Отмена заявки в статусе Открыта - то есть перевод заявки в статус Cancel
    //Иногда кнопка Cancel не доступна. Не совсем понимаю...от чего это зависит. Возможно баг
    @Test
    @DisplayName("Отмена заявки в статусе Открыта")
    public void shouldClaimCancel() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        ClaimPage.openFirstClaim();
        ClaimPage.editStatusClaim("Open", "Cancel");
        ClaimPage.checkStatusClaim("Canceled");
    }

    @Test
    @DisplayName("Переводим заявку в статус Выполнена")
    public void shouldClaimExecute() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        ClaimPage.openFirstClaim();
        ClaimPage.editStatusClaim("Open", "take to work");
        ClaimPage.editStatusClaim("In progress", "To execute");
        ClaimPage.addCommentStatusClaim("Test", "Executed");
        ClaimPage.checkStatusClaim("Executed");
    }

    @Test
    @DisplayName("Переводим заявку в статус в Работе")
    public void shouldInProgress() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        ClaimPage.openFirstClaim();
        ClaimPage.editStatusClaim("Open", "take to work");
        ClaimPage.checkStatusClaim("In progress");
    }

    @Test
    @DisplayName("Сброс заявки со статусом в Работе")
    public void shouldThrowOff() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.progressStatusFilter);
        ClaimPage.openFirstClaim();
        ClaimPage.editStatusClaim("Open", "take to work");
        ClaimPage.editStatusClaim("In progress", "Throw off");
        ClaimPage.addCommentStatusClaim("Test", "Open");
        ClaimPage.checkStatusClaim("Open");
    }

    @Test
    @DisplayName("Добавление комментария к заявке")
    public void shouldAddComment() throws InterruptedException {
        MenuPage.goClaims();
        ClaimPage.filterStatusClaim(Claim.openStatusFilter);
        ClaimPage.openFirstClaim();
        ClaimPage.addCommentClaim("По заявке 356");
        ClaimPage.checkCreatedComment("По заявке 356");
    }
}