package com.example.android.shoppinglist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Button
import android.widget.Toast

class AddItemActivity : AppCompatActivity() {

    lateinit var cheeseButton: Button
    lateinit var riceButton: Button
    lateinit var applesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        cheeseButton = findViewById(R.id.cheese_button)
        riceButton = findViewById(R.id.rice_button)
        applesButton = findViewById(R.id.apples_button)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        cheeseButton.setOnClickListener { itemToMain("cheese") }
        riceButton.setOnClickListener { itemToMain("rice") }
        applesButton.setOnClickListener { itemToMain("apples") }
    }

    private fun itemToMain(item: String) {
        val intent = Intent(this, AddItemActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, item)
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
