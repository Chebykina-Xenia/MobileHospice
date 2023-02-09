package ru.iteco.fmhandroid.ui.test;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@RunWith(AllureAndroidJUnit4.class)

public class NewsTest {

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

    @Test
    @DisplayName("Создание новости без категории")
    public void shouldErrorNotCategory() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("yes", "no", "Объявление", "no", "Now News", "no", "no", "dial", "save", "no", "Description Test");
        NewsPage.saveNews();
        NewsPage.checkIconVisible();
    }

    @Test
    @DisplayName("Создание новости")
    public void shouldCreateNews() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("no", "yes", "Объявление", "no", "New News Test", "no", "no", "dial", "save", "no", "Description Test");
        NewsPage.saveNews();
        NewsPage.checkCreatedNews("New News Test");
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.deleteNews("New News Test");
    }

    @Test
    @DisplayName("Удаление новости")
    public void shouldDeleteNews() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("no", "yes", "Объявление", "no", "Test new", "no", "no", "dial", "save", "no", "Description Test11");
        NewsPage.saveNews();
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.deleteNews("Test new");

        MenuPage.goNews();
        NewsPage.checkDeleteNews("Test new");
    }

    @Test
    @DisplayName("Проверка работы фильтра")
    public void shouldFilterNewsCategory() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("no", "yes", "Объявление", "no", "Chek Filter", "no", "no", "dial", "save", "no", "Description");
        NewsPage.saveNews();

        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.checkCreatedNews("Chek Filter");
        NewsPage.deleteNews("Chek Filter");
    }

    @Test
    @DisplayName("Редактирование новости")
    public void shouldChangeNews() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("no", "yes", "Объявление", "no", "Now Change", "no", "no", "dial", "save", "no", "Description Change");
        NewsPage.saveNews();
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.updateTitleNews("Now Change", "Now Change Test");
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.checkCreatedNews("Now Change Test");
        NewsPage.deleteNews("Now Change Test");
    }
}
