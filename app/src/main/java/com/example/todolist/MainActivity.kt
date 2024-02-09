package com.example.todolist

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.marginTop
import  com.example.todolist.TaskControl
import  com.example.todolist.Teste




 class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val containerRelative = findViewById<RelativeLayout>(R.id.container)

        val mainContainer = findViewById<LinearLayout>(R.id.main)

        var textTaskView:TextView = findViewById(R.id.inputTask);


        val addBtn = findViewById<Button>(R.id.addTaskBtn);


        addBtn.setOnClickListener {

            var removeBtn = Button(this)

            val newText = TextView(this)

            val taskValue = textTaskView.text.toString()

            var taskView = LinearLayout(this)

            val task = TaskControl(taskValue,newText,  removeBtn,mainContainer,taskView );

            task.checkEmpty();

            task.createTask()

        }


    }




}
