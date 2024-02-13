package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.marginTop
import  com.example.todolist.TaskControl
import  com.example.todolist.Storage
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val context : Context = this

        val containerRelative = findViewById<RelativeLayout>(R.id.container)

        val mainContainer = findViewById<LinearLayout>(R.id.main)

        var textTaskView:TextView = findViewById(R.id.inputTask);

        val addBtn = findViewById<Button>(R.id.addTaskBtn);

        val storage = Storage(this)

        val dataStorage =  storage.getData()

        if(dataStorage!=null)
        {
            val list = dataStorage.split("").filter { it != "[" && it != "]" }.joinToString (separator =  "").split((", "))

            if(list.isNotEmpty()){

                for(task in list){

                    val newText = TextView(this)

                    val task = TaskControl(task ,newText,mainContainer, context ,storage);

                    task.checkEmpty()

                    task.createTask()


                }


            }


        }

        addBtn.setOnClickListener {

            val newText = TextView(this)

            val taskValue = textTaskView.text.toString()

            val task = TaskControl(taskValue,newText,mainContainer, context ,storage);

            task.checkEmpty()

            task.createTask()

        }


    }




}
