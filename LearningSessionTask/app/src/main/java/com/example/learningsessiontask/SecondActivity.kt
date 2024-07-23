package com.example.learningsessiontask

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    init {
        Log.e(TAG, "Starting SecondActivity: ", )
    }
    companion object{
        private const val TAG = "SecondActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        Log.e(TAG, "onCreate: ", )
        findViewById<View>(R.id.firstactivity).setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))

        }
    }
    override fun onStart() {
        super.onStart()
        Log.e(ContentValues.TAG, "onStart: " )
    }

    override fun onResume() {
        super.onResume()
        Log.e(ContentValues.TAG,"onResume: ")
    }
    override fun onPause() {
        super.onPause()
        Log.e(ContentValues.TAG, "onPause: " )
    }

    override fun onStop() {
        super.onStop()
        Log.e(ContentValues.TAG, "onStop: " )
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(ContentValues.TAG, "onRestart: " )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(ContentValues.TAG, "onDestroy: ")
    }
}