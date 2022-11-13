package com.example.scanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.scanner.databinding.ActivityScanBinding

class ScanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityScanBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_scan)
    }
}