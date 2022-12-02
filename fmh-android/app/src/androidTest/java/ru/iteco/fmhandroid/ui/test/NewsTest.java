package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.elements.News;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

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

    //оставляем поле категория пустым
    @Test
    public void shouldErrorNotCategory() throws InterruptedException {
        String emptyCategory = "yes";
        String withCategoryChoice = "no";
        String chosenCategory = "Объявление";
        String category = "no";
        String title = "Now News";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "Description Test";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();
        onView(withText(R.string.empty_fields));
    }

    //успешное создание новости
    @Test
    public void shouldCreateNews() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String chosenCategory = "Объявление";
        String category = "no";
        String title = "Now News";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "Description Test";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();
        News.buttonAddNews.check(matches(isDisplayed()));
    }

    //Удаляем новость
    @Test
    public void shouldDeleteNews() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String chosenCategory = "Объявление";
        String category = "no";
        String title = "Test11";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "Description Test11";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();
        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        onView(withText(title)).check(matches(isDisplayed())).perform(click());
        NewsPage.deleteNews(title);

        MenuPage.goNews();
        //как проверить, что в списке новостей нет новости с данным заголовком?
        //onView(withId(R.id.news_item_title_text_view)).check(matches(not(withText(title))));
    }

    //проверяем работу фильтра
    @Test
    public void shouldFilterNewsCategory() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String chosenCategory = "Объявление";
        String category = "no";
        String title = "Chek Filter";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "Description";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();

        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        onView(withText(title)).check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.edit_news_item_image_view), hasSibling(withText(title)))).perform(click());
        News.categorySee.check(matches(withText(chosenCategory)));
    }

    //редактирование новости
    @Test
    public void shouldChangeNews() throws InterruptedException {
        String emptyCategory = "no";
        String withCategoryChoice = "yes";
        String chosenCategory = "Объявление";
        String category = "no";
        String title = "Now Change";
        String newTitle = "Now Change Test";
        String emptyDate = "no";
        String emptyTime = "no";
        String withDialPadOrTextInput = "dial";
        String saveOrCancelTime = "save";
        String emptyDescription = "no";
        String description = "Description Change";
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields(emptyCategory, withCategoryChoice, chosenCategory, category, title, emptyDate, emptyTime, withDialPadOrTextInput, saveOrCancelTime, emptyDescription, description);
        NewsPage.saveNews();
        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        onView(withText(title)).check(matches(isDisplayed())).perform(click());
        onView(allOf(withId(R.id.edit_news_item_image_view), hasSibling(withText(title)))).perform(click());

        News.titleTextInputNews.perform(replaceText(newTitle));
        News.buttonSaveNews.perform(click());

        MenuPage.goNews();
        NewsPage.filterNews(chosenCategory);
        onView(withText(newTitle)).check(matches(isDisplayed()));
        onView(withText(newTitle)).check(matches(isDisplayed())).perform(click());
        NewsPage.deleteNews(newTitle);
    }

}
