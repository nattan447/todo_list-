package com.example.todolist

import android.graphics.Color
import android.text.Layout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
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


class TaskControl (val task : String, private val newText:TextView,  private val removeButton: Button,private val mainContainer: LinearLayout, var taskview: LinearLayout ){

    private var empty = true;


   private val defaultParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,

        LinearLayout.LayoutParams.WRAP_CONTENT,
        )

    private val textStyle = defaultParams

    private  val btnStyle =  defaultParams


     fun checkEmpty(){
         val regex  =  Regex("^\\S+$")

         val nonSpaceList =  task.split("").filter {  regex.matches(it) === true }

         val taskLength = nonSpaceList.joinToString(separator = "").length

         if(taskLength>=1)
             empty = false

     }


    private  fun createButton(){

        btnStyle.leftMargin = 300;

        removeButton.text = "remove"

        removeButton.layoutParams = btnStyle

//        removeButton.setBackgroundColor(Color.RED)

        removeButton.setTextColor(Color.WHITE)

        taskview.addView(removeButton)

        removeButton.setOnClickListener{

            deleteTask();

        }

    }





    fun createTask(){

        taskview.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,


        )

        val marginLayoutParams = MarginLayoutParams(taskview.layoutParams)

        marginLayoutParams.setMargins(0, 100, 0, 0)


        taskview.layoutParams = marginLayoutParams

        taskview.orientation = LinearLayout.HORIZONTAL


        taskview.gravity = Gravity.CENTER_VERTICAL


        taskview.gravity = Gravity.CENTER_HORIZONTAL



        taskview.setBackgroundColor(Color.CYAN)


        if(!empty){
            newText.text = task


            newText.textSize= 18f;


            newText.setTextColor(Color.RED);

            newText.id = View.generateViewId();


            newText.layoutParams = textStyle


            taskview.addView(newText)

                 createButton();

            mainContainer.addView(taskview)


        }
        else {
            println("is not possible add empty tasks")
        }

    }

   private fun deleteTask(){

       mainContainer.removeView(taskview)

    }




}