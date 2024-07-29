package com.example.componentstask

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.componentstask.databinding.ActivityMainBinding
import com.example.componentstask.databinding.ComponentToolbarBinding
import com.example.componentstask.utils.setupToolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            setupToolbar(binding.toolbar,R.string.sign_up){}
          btnNext.setOnClickListener{
           startActivity(Intent(this@MainActivity,SecondActivity::class.java))
           finish()
          }
        }

    }
}