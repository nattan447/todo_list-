package com.example.todolist

import android.content.Context

class Storage(val context : Context) {

    private var tasks : MutableList<String> = mutableListOf()

    fun addTask(task : String){

        tasks?.add(task)

        saveData(tasks as MutableList<String>)

    }

    fun saveData (tasks:MutableList<String>){

        val sharedPreferences = context.getSharedPreferences("Actvities",Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        editor.putString("tasks",tasks.toString())

        editor.apply()
    }

    fun getData(): String? {

        val sharedPreferences = context.getSharedPreferences("Actvities", Context.MODE_PRIVATE)

        return sharedPreferences.getString("tasks",null)
    }

    fun removeData (task : String){
        tasks?.remove(task)

        saveData(tasks as MutableList<String>)



    }


}