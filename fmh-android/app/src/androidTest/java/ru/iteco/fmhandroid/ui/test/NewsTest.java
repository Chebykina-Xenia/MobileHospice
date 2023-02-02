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

    String chosenCategory = "Объявление";
    String withDialPadOrTextInput = "dial";
    String saveOrCancelTime = "save";

    @Test
    @DisplayName("Создание новости без категории")
    public void shouldErrorNotCategory() throws InterruptedException {
        String emptyCategory = "yes";
        String withCategoryChoice = "no";
        String category = "no";
        String title = "Now News";
        String emptyDate = "no";
        String emptyTime = "no";
        String emptyDescription = "no";
        String description = "Description Test";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();
        NewsPage.checkIconVisible();
    }

    @Test
    @DisplayName("Создание новости")
    public void shouldCreateNews() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String category = "no";
        String title = "New News Test";
        String emptyDate = "no";
        String emptyTime = "no";
        String emptyDescription = "no";
        String description = "Description Test";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();
        NewsPage.checkCreatedNews(title);
        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        NewsPage.deleteNews(title);
    }

    @Test
    @DisplayName("Удаление новости")
    public void shouldDeleteNews() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String category = "no";
        String title = "Test new";
        String emptyDate = "no";
        String emptyTime = "no";
        String emptyDescription = "no";
        String description = "Description Test11";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();
        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        NewsPage.deleteNews(title);

        MenuPage.goNews();
        NewsPage.checkDeleteNews(title);
    }

    @Test
    @DisplayName("Проверка работы фильтра")
    public void shouldFilterNewsCategory() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String category = "no";
        String title = "Chek Filter";
        String emptyDate = "no";
        String emptyTime = "no";
        String emptyDescription = "no";
        String description = "Description";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();

        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        NewsPage.checkCreatedNews(title);
        NewsPage.deleteNews(title);
    }

    @Test
    @DisplayName("Редактирование новости")
    public void shouldChangeNews() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String category = "no";
        String title = "Now Change";
        String newTitle = "Now Change Test";
        String emptyDate = "no";
        String emptyTime = "no";
        String emptyDescription = "no";
        String description = "Description Change";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();
        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        NewsPage.updateTitleNews(title, newTitle);
        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        NewsPage.checkCreatedNews(newTitle);
        NewsPage.deleteNews(newTitle);
    }
}
