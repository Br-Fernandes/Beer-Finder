package com.example.cesar.beerfinder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BuscarCervejaActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner)
        val searchButton = findViewById<Button>(R.id.search_brands_btn)

        setSupportActionBar(findViewById(R.id.toolbar_main))
        supportActionBar?.title = "${resources.getString(R.string.main_title)}"

        ArrayAdapter.createFromResource(
            this,
            R.array.beer_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        searchButton.setOnClickListener {
            val selectedItem: String? = spinner.selectedItem.toString()
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra("selectedItem", selectedItem!!)
            startActivity(intent)
        }
    }
}