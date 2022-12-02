package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import org.hamcrest.Matchers;
import ru.iteco.fmhandroid.R;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import ru.iteco.fmhandroid.ui.elements.News;

public class NewsPage {
    //создание новости
    public static void creationOfNews() {
        News.buttonEditNews.perform(click());
        News.buttonAddNews.check(matches(isDisplayed()));
        News.buttonAddNews.perform(click());
        News.titleNewsCreatWindow.check(matches(isDisplayed()));
    }

    //заполняем поля при создании Новости
    public static void fillNewsFields(String emptyCategory, String choiceOfCategory, String chosenCategory, String category, String title, String emptyDate, String emptyTime, String withDialPadOrTextInput, String saveOrCancelTime, String emptyDescription, String description) {
        Integer categoryPosition = null;
        if (chosenCategory == "Объявление") {
            categoryPosition = 0;
        } else if (chosenCategory == "День рождения") {
            categoryPosition = 1;
        } else if (chosenCategory == "Зарплата") {
            categoryPosition = 2;
        } else if (chosenCategory == "Профсоюз") {
            categoryPosition = 3;
        } else if (chosenCategory == "Праздник") {
            categoryPosition = 4;
        } else if (chosenCategory == "Массаж") {
            categoryPosition = 5;
        } else if (chosenCategory == "Благодарность") {
            categoryPosition = 6;
        } else if (chosenCategory == "Нужна помощь") {
            categoryPosition = 7;
        }

        // заполнение поля "Категория"
        if (emptyCategory == "no") {
            if (choiceOfCategory == "yes") {
                News.buttonShowingDropdownMenu.perform(click());
                Espresso.onData(Matchers.anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(categoryPosition).perform(ViewActions.click());
            } else {
                News.categoryNews.perform(replaceText(category));
                News.categoryNews.check(matches(isDisplayed()));
            }
        }

        //заполняем поле заголовок
        News.titleTextInputNews.perform(replaceText(title));

        //заполняем поле Дата
        if (emptyDate == "no") {
            News.dateTextInputNews.perform(click());
            News.okButton.perform(click());
        }
        //заполняем поле Время
        if (emptyTime == "no") {
            if (withDialPadOrTextInput == "dial") {
                News.timeTextInputNews.perform(click());
                if (saveOrCancelTime == "save") {
                    News.okButton.perform(click());
                } else {
                    News.cancelButton.perform(click());
                }
            }
        }

        //заполняем поле Описание
        if (emptyDescription == "no") {
            News.descriptionTextInputNews.perform(replaceText(description));
            News.descriptionTextInputNews.check(matches(withText(description)));
        }
    }

    //Сохраняем новость — нажимаем на кнопку SAVE
    public static void saveNews() {
        News.buttonSaveNews.perform(click());
    }

    //фильтруем Новости (после нажатия на кнопку редактировать)
    public static void filterNews(String category) {
        News.buttonEditNews.perform(click());
        News.buttonFilterNews.perform(click());
        News.categoryNews.perform(replaceText(category));
        News.dateStartFilter.perform(click());
        News.okButton.perform(click());
        News.dateEndFilter.perform(click());
        News.okButton.perform(click());
        News.buttonNotActive.perform(click());
        News.buttonFilter.perform(click());

    }

    //удаление новости
    public static void deleteNews(String title) {
        onView(allOf(withId(R.id.delete_news_item_image_view), hasSibling(withText(title)))).perform(click());
        News.okButton.check(matches(isDisplayed()));
        // подтверждаем удаление новости
        News.okButton.perform(click());
        // проверка возвращения к новостям (видна кнопка добавления новости)
        News.buttonAddNews.check(matches(isDisplayed()));
    }
}