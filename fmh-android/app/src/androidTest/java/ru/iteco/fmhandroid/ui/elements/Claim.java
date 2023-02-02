package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.date.DataHelper;

public class Claim {
    // Название блока
    public static ViewInteraction titleClaimsBlock = onView(withText("Claims"));
    // Создание заявки
    public static ViewInteraction addNewClaimButton = onView(withId(R.id.add_new_claim_material_button));
    public static ViewInteraction titleClaimsCreating = onView(withText("Creating"));
    public static ViewInteraction titleTextInputClaim = onView(withId(R.id.title_edit_text));
    public static ViewInteraction executorTextInput = onView(withId(R.id.executor_drop_menu_text_input_layout));
    public static ViewInteraction dateClaim = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public static ViewInteraction okButton = onView(withText("OK"));
    public static ViewInteraction timeClaim = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public static ViewInteraction cancelButton = onView(withText("CANCEL"));
    public static ViewInteraction descriptionTextInputClaim = onView(withId(R.id.description_edit_text));
    //Кнопка сохранить
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction firstClaim = onView(DataHelper.withIndex(withId(R.id.claim_list_card), 0));

    // Фильтрация заявок
    //кнопка фильтра заявок (по статусам)
    public static ViewInteraction buttonClaimFilter = onView(withId(R.id.filters_material_button));
    //кнопка вкл/выкл статус в процессе
    public static ViewInteraction progressStatusFilter = onView(withId(R.id.item_filter_in_progress));
    //кнопка вкл/выкл статус Open
    public static ViewInteraction openStatusFilter = onView(withId(R.id.item_filter_open));
    //статус заявки текущий
    public static ViewInteraction statusClaimNow = onView(withId(R.id.status_label_text_view));
    //кнопка для добавления комментария к заявке
    public static ViewInteraction addComment = onView(withId(R.id.add_comment_image_button));
    //Кнопка для смены статуса заявки
    public static ViewInteraction statusEdit = onView(withId(R.id.status_processing_image_button));
    public static ViewInteraction executor = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public static Matcher<View> statusLabelText = withId(R.id.status_label_text_view);
}
