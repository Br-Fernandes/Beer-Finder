package com.example.cesar.beerfinder

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.cesar.beerfinder.R.style.MyCustomActionBarTitleStyle


class ResultsActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "DiscouragedApi", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        lateinit var scrollView: ScrollView
        val selectedBeerType = intent.getStringExtra("selectedItem")
        val expertCerveja = ExpertCerveja()
        val marcas = expertCerveja.getMarcas(selectedBeerType)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "${resources.getString(R.string.results_title)} $selectedBeerType"

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
                linearLayout.setBackgroundResource(R.drawable.border_bottom)
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

        textParams.gravity = Gravity.CENTER
        textParams.setMargins(16, 16, 16, 16)
        textView.layoutParams = textParams
        textView.textSize = 20f
        textView.text = text
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setTextColor(ContextCompat.getColor(this, R.color.red))
        textView.setShadowLayer(2f, 1f, 1f, ContextCompat.getColor(this, R.color.text_shadow))

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


