package com.example.componentstask.utils

import android.icu.util.CurrencyAmount
import androidx.appcompat.widget.AppCompatImageView
import com.example.componentstask.databinding.ComponentBudgetsummaryBinding
import com.example.componentstask.databinding.ComponentFilepickerBinding
import com.example.componentstask.databinding.ComponentToolbarBinding

/**
 * Sets up the toolbar with a title and a back arrow click listener.
 *
 * @param toolbar The toolbar binding object of type `ComponentToolbarBinding` to be set up.
 * @param title The resource ID of the string to be used as the toolbar title.
 * @param onBack A lambda function to be invoked when the back arrow is clicked.
 *
 * The function sets the toolbar title to the string resource identified by the `title` parameter.
 * It also sets up a click listener on the back arrow image view which triggers the `onBack` lambda.
 */

fun setupToolbar(toolbar: ComponentToolbarBinding,title:Int, onBack: () ->Unit){
    toolbar.ivTitle.text=toolbar.root.context.getString(title)
    toolbar.ivBackArrow.setOnClickListener{
        onBack.invoke()
    }
}

// ----------------------------------------------------------------------------------------------//

// COMPONENT FILE PICKER
/**
 * Sets up the file picker component with a title and an image.
 *
 * @param filepicker The file picker binding object of type `ComponentFilepickerBinding` to be set up.
 * @param title The resource ID of the string to be used as the title of the file picker.
 * @param imageResId The resource ID of the image to be displayed in the file picker.
 *
 * This function sets the title of the file picker to the string resource identified by the `title`
 * parameter and sets the image of the file picker to the drawable resource identified by the
 * `imageResId` parameter.
 */
fun setupFilepicker(filepicker: ComponentFilepickerBinding, title: Int, imageResId: Int) {
    filepicker.tvTitle.text = filepicker.root.context.getString(title)
    filepicker.imageView.setImageResource(imageResId)
}



// ----------------------------------------------------------------------------------------------//

// COMPONENT BUDGET SUMMARY
/**
 * Sets up the budget summary component with a title.
 *
 * @param budgetsummary The budget summary binding object of type `ComponentBudgetsummaryBinding` to be set up.
 * @param title The resource ID of the string to be used as the title of the budget summary.
 *
 * This function sets the title of the budget summary to the string resource identified by the `title`
 * parameter.
 */
fun setupBudgetSummary(budgetsummary: ComponentBudgetsummaryBinding, title: Int) {
    budgetsummary.textViewTitle.text = budgetsummary.root.context.getString(title)
}



