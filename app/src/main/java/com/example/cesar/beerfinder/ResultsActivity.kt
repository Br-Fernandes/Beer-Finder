package com.example.cesar.beerfinder

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
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

        val scrollViewLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        if (marcas != null) {
            for (marca in marcas) {
                val textView = createNewTextView(marca)
                val imageView = createNewImageView(marca)

                val linearLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(16, 16, 16, 16)
                    }
                }

                linearLayout.addView(textView)
                linearLayout.addView(imageView)

                scrollViewLayout.addView(linearLayout)
            }

            scrollView = findViewById(R.id.brands_sv)
            scrollView.addView(scrollViewLayout)
        }

    }

    fun createNewTextView(text: String): TextView {
        val textView = TextView(this)

        val textParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
        )

        textParams.gravity = Gravity.START
        textParams.setMargins(16, 16, 16, 16)
        textView.layoutParams = textParams
        textView.text = text

        return textView
    }

    fun createNewImageView(imageName: String): ImageView {
        val res = resources
        val styledImageName = styleImageName(imageName)
        val id = res.getIdentifier(styledImageName, "drawable", packageName)

        val imageView = ImageView(this)
        var layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
        )
        layoutParams.gravity = Gravity.END
        layoutParams.setMargins(16, 16, 16, 16)
        imageView.layoutParams = layoutParams


        val bitmap = BitmapFactory.decodeResource(res, id)
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 350, 600, false)
        imageView.setImageBitmap(resizedBitmap)

        return imageView
    }


    private fun styleImageName(name: String): String{
        var nameSplit = name.split(" ")
        return nameSplit.joinToString(separator = "_").lowercase()
    }
}


