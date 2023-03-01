package com.example.cesar.beerfinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ResultsActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId", "DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)


        lateinit var scrollView: ScrollView
        val selectedBeerType = intent.getStringExtra("selectedItem")
        val expertCerveja = ExpertCerveja()
        val marcas = expertCerveja.getMarcas(selectedBeerType)

        val scrollViewLayout = LinearLayout(this)

        if (marcas != null) {
            for (marca in marcas) {
                //create and configure a new TextView
                val textView = TextView(this)
                textView.text = marca

                //create and configure a new ImageView
                val res = resources
                val imageName = styleImageName(marca)
                val id = res.getIdentifier(imageName, "drawable", packageName)
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

                scrollViewLayout.orientation = LinearLayout.VERTICAL
                scrollViewLayout.addView(linearLayout)


            }
            scrollView = findViewById(R.id.brands_sv)
            scrollView.addView(scrollViewLayout)
        }
    }

    private fun styleImageName(name: String): String{
        var nameSplit = name.split(" ")
        return nameSplit.joinToString(separator = "_").lowercase()
    }
}


