package com.example.cesar.beerfinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ResultsActivity : AppCompatActivity() {

    private lateinit var scrollView: ScrollView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_results)


        val searchButton = findViewById<Button>(R.id.search_brands_btn)
        val beerTypeSpinner = findViewById<Spinner>(R.id.spinner)

        val selectedBeerType = beerTypeSpinner.selectedItem.toString()
        val expertCerveja = ExpertCerveja()
        val marcas = expertCerveja.getMarcas(selectedBeerType)

        val scrollView_Layout = LinearLayout(this)

        if (marcas != null) {
            for (marca in marcas) {
                //create and configure a new TextView
                val textView = TextView(this)
                textView.text = marca

                //create and configure a new ImageVeiw
                val res = resources
                val imageName = styleImageName(marca)
                val id = res.getIdentifier("$imageName", "drawable", packageName)
                val imageView = ImageView(this)
                imageView.setImageResource(id)

                val linearLayout = LinearLayout(this)
                linearLayout.orientation = LinearLayout.HORIZONTAL

                // Criando um novo LayoutParams com as dimensões desejadas
                val paramsLayout = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,

                    )

                linearLayout.addView(textView, paramsLayout)
                linearLayout.addView(imageView, paramsLayout)

                scrollView_Layout.orientation = LinearLayout.VERTICAL
                scrollView_Layout.addView(linearLayout)


            }
            scrollView = findViewById(R.id.brands_sv)
            scrollView.addView(scrollView_Layout)
        }
    }

    private fun styleImageName(name: String): String{
        var nameSplit = name.split(" ")
        return nameSplit.joinToString(separator = "_").lowercase()
    }
}


