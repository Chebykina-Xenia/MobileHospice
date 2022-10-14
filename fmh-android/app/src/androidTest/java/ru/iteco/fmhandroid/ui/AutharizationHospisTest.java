package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AutharizationHospisTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityTestRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before // Выполняется перед тестами
    public void registerIdlingResources(){ //Подключаемся к “счетчику”
       IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);}
   @After // Выполняется после тестов
   public void unregisterIdlingResources(){ //Отключаемся от “счетчика”
    IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);}

    @Test
    public void successfulАuthorization() {
        //SystemClock.sleep(10000);
        ViewInteraction loginInput = onView(withId(R.id.login_text_input_layout));
       // ViewInteraction loginInput = onView(withText("Логин"));
        //loginInput.check(matches(isDisplayed()));
       // loginInput.check(matches(withText("Логин")));
        loginInput.perform(click(), typeText("login2"), closeSoftKeyboard());
        //loginInput.perform(replaceText("login2"));
       // loginInput.perform(typeText("login2"));
     //ViewInteraction passwordInput = onView(withId(R.id.password_text_input_layout));
     //passwordInput.perform(replaceText("password2"), closeSoftKeyboard());
     //ViewInteraction enterButton = onView(withId(R.id.enter_button));
     //enterButton.perform(click());
       // ViewInteraction materialButton = onView(
         //       allOf(withId(R.id.enter_button), withText("�����"), withContentDescription("���������"),
           //             childAtPosition(
             //                   childAtPosition(
               //                         withClassName(is("android.widget.RelativeLayout")),
                 //                       1),
                   //             2),
                     //   isDisplayed()));
        //materialButton.perform(click());

        //ViewInteraction imageView = onView(
          //      allOf(withId(R.id.trademark_image_view),
            //            withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
              //                  withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                //        isDisplayed()));
        //imageView.check(matches(isDisplayed()));
    }

}
