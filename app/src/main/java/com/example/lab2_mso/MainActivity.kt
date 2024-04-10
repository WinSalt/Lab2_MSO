package com.example.lab2_mso

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val startButton = findViewById<Button>(R.id.button1)
        lateinit var progressBar: ProgressBar
        progressBar = findViewById(R.id.progressBar1)
        startButton.setOnClickListener {
            val text1 = findViewById<TextView>(R.id.textView1)
            text1.text = "Click!"
            val job = lifecycleScope.launch {
                //Log.d("YourTAG", "Job started")
                var progress = 0
                progressBar.progress = progress
                var text_tmp = "Working"
                var totalTime = 1000 // 5000 milisekund = 5 sekund
                val interval = 50 // Interwał aktualizacji postępu (100 milisekund)
                launch(Dispatchers.Main) {
                    text1.text = "Current progress: ${text_tmp}."
                }
                while (progress < 100) {
                    // Aktualizujemy postęp co interwał
                    progress += 100 * interval / totalTime
                    progressBar.progress = progress

                    // Czekamy przez interwał przed kolejną aktualizacją
                    delay(interval.toLong())
                }
                text_tmp = "Done!"
                launch(Dispatchers.Main) {
                    text1.text = "Current progress: ${text_tmp}."
                }
            }
        }
    }
}