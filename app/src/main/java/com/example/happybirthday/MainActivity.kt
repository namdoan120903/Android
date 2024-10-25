package com.example.happybirthday

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listViewEmails: ListView
    private lateinit var emailAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewEmails = findViewById(R.id.listViewEmails)

        // Dữ liệu mẫu
        val emails = listOf(
            Email("Edurila.com", "$19 Only (First 10 spots) - Bestselling...", "12:34 PM"),
            Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM"),
            Email("Tuto.com", "8h de formation gratuite et les nouvea...", "11:04 AM"),
            Email("Support", "Société Ovh : suivi de vos services ...", "10:26 AM"),
            Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"),
            Email("Edurila.com", "$19 Only (First 10 spots) - Bestselling...", "12:34 PM"),
            Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM"),
            Email("Tuto.com", "8h de formation gratuite et les nouvea...", "11:04 AM"),
            Email("Support", "Société Ovh : suivi de vos services ...", "10:26 AM"),
            Email("Edurila.com", "$19 Only (First 10 spots) - Bestselling...", "12:34 PM"),
            Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM"),
            Email("Tuto.com", "8h de formation gratuite et les nouvea...", "11:04 AM"),
            Email("Support", "Société Ovh : suivi de vos services ...", "10:26 AM")
        )

        emailAdapter = Adapter(this, emails)
        listViewEmails.adapter = emailAdapter
    }
}