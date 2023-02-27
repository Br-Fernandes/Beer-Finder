package com.example.cesar.beerfinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tipoCervejaSpinner: Spinner
    private lateinit var buscarMarcasBtn: Button
    private lateinit var marcasCervejaScrollView: ScrollView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.search_brands_btn).setOnClickListener {
            Toast.makeText(this, "Botão clicado", Toast.LENGTH_SHORT).show()
        }



        val spinner: Spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.beer_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        tipoCervejaSpinner = findViewById(R.id.spinner)
        buscarMarcasBtn = findViewById<Button>(R.id.search_brands_btn)

        tipoCervejaSpinner.bringToFront()
        buscarMarcasBtn.bringToFront()

        val searchButton = findViewById<Button>(R.id.search_brands_btn)
        val beerTypeSpinner = findViewById<Spinner>(R.id.spinner)

        searchButton.setOnClickListener {
            val selectedBeerType = beerTypeSpinner.selectedItem.toString()
            val expertCerveja = ExpertCerveja()
            val marcas = expertCerveja.getMarcas(selectedBeerType)

            if (marcas != null) {
                for (marca in marcas) {
                    val textView = TextView(this)
                    textView.text = marca

                    val res = resources
                    val imageName = styleImageName(marca)
                    val id = res.getIdentifier("$imageName", "drawable", packageName)

                    val imageView = ImageView(this)
                    imageView.setImageResource(id)

                    val linearLayout = LinearLayout(this)
                    linearLayout.orientation = LinearLayout.HORIZONTAL

                    // Criando um novo LayoutParams com as dimensões desejadas
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    linearLayout.addView(textView, params)
                    linearLayout.addView(imageView, params)

                    marcasCervejaScrollView.addView(linearLayout)
                }
            }
        }
    }


    private fun styleImageName(name: String): String{
        var nameSplit = name.split(" ")
        return nameSplit.joinToString(separator = "_").lowercase()
    }

}