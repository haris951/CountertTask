package com.example.componentstask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.componentstask.databinding.ActivityThirdBinding
import com.example.componentstask.utils.setupBudgetSummary
import com.example.componentstask.utils.setupFilepicker

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityThirdBinding=ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFilepicker(binding.filepicker1, R.string.camera, R.drawable.camera)
        setupFilepicker(binding.filepicker2, R.string.image, R.drawable.gallery)
        setupFilepicker(binding.filepicker3, R.string.document, R.drawable.doc)

        setupBudgetSummary(binding.budgetsummary,R.string.income)

    }
}