package com.example.componentstask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.componentstask.databinding.ActivitySecondBinding
import com.example.componentstask.utils.setupToolbar

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySecondBinding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
binding.apply {
    setupToolbar(binding.toolbar, R.string.login) {
        finish()
    }
    btnSecondActivity.setOnClickListener{
        startActivity(Intent(this@SecondActivity,ThirdActivity::class.java))
    }
}
}
}