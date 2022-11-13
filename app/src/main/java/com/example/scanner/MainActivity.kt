package com.example.scanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.scanner.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class MainActivity : AppCompatActivity() {

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val barcode: String = data?.getStringExtra("barcode") ?: ""
                val extBarcode: String = data?.getStringExtra("extBarcode") ?: ""
                Toast.makeText(
                    this,
                    "Scanned: $barcode, $extBarcode",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    fun openSomeActivityForResult() {
        val intent = Intent(this, ScanActivity::class.java)
        resultLauncher.launch(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
    }


    fun startScan(view: View?) {
        val intent = Intent(this, ScanActivity::class.java)
        resultLauncher.launch(intent)
    }

    fun startScanOnce(view: View?) {
        barcodeLauncher.launch(ScanOptions())
    }

}