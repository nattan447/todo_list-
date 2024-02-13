package com.example.todolist

import android.content.Context
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.drawable.Drawable
import android.service.dreams.DreamService
import android.text.InputFilter
import android.text.InputType
import android.text.Layout
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.example.todolist.MainActivity
import com.example.todolist.R
import java.io.File.separator
import java.io.StringReader


class TaskControl (val task : String, private val newText:TextView,private val mainContainer: LinearLayout, private  val context : Context,val Storage:Storage){


    private  val taskView : LinearLayout = LinearLayout(context)

    private val taskText : EditText = EditText(context)

    private var empty = true;


   private val btnParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        )








    private fun createTaskView(){


        taskView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,


            )

        val marginLayoutParams = MarginLayoutParams(taskView.layoutParams)

        marginLayoutParams.setMargins(0, 100, 0, 0)

        taskView.layoutParams = marginLayoutParams

        taskView.orientation = LinearLayout.HORIZONTAL


        taskView.gravity = Gravity.CENTER_VERTICAL


        taskView.gravity = Gravity.END





    }


     fun checkEmpty():Boolean{
         val regex  =  Regex("^\\S+$")

         val nonSpaceList =  task.split("").filter {  regex.matches(it) === true }

         val taskLength = nonSpaceList.joinToString(separator = "").length

         if(taskLength>=1){
             empty = false
            return false
         }
             return true



     }


    private  fun editButton () {
        val editBtn : Button = Button (context)

        editBtn.text = "edit"

        editBtn.layoutParams = btnParams

        editBtn.setTextColor(context.getColor(R.color.red))

        editBtn.background = context.getDrawable(R.drawable.edit_text_border)

        btnParams.rightMargin = 30;


        taskView.addView(editBtn)


        editBtn.setOnClickListener {

          taskText.isEnabled = true;

            println("it is possible to edit")

        }











    }




    private  fun removeButton(){


        val removeButton : Button = Button (context)

        removeButton.text = "Remove"

        btnParams.height = 100

        removeButton.layoutParams = btnParams

        btnParams.gravity = Gravity.END

        removeButton.setTextColor(context.getColor(R.color.red))

        removeButton.background = context.getDrawable(R.drawable.edit_text_border) ;



        taskView.addView(removeButton)

        removeButton.setOnClickListener{

            deleteTask();

            Storage.removeData(task)


        }

    }








    fun createTask(){

        if(!empty){

            val taskParams =  LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )


            createTaskView()


            taskText.setText(task);

            taskText.isEnabled = false

            taskText.textSize = 18f

            taskText.setTextColor(RED);



            taskText.maxLines = 1;

            taskText.inputType = InputType.TYPE_CLASS_TEXT;

            taskText.id = View.generateViewId()

            taskText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))

            taskText.layoutParams = taskParams

            taskParams.rightMargin = 300

            taskText.background = context.getDrawable(R.drawable.task_text_background)

            taskView.addView(taskText)



            taskText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {

                    Storage.removeData(task);

                    Storage.addTask(taskText.text.toString());

                    taskText.isEnabled = false

                    return@OnKeyListener true

                }

                false
            })



            editButton()

            removeButton();

            Storage.addTask(task)


            mainContainer.addView(taskView)


        }
        else {
            println("is not possible add empty tasks")
        }

    }

   private fun deleteTask(){

       mainContainer.removeView(taskView)

    }




}