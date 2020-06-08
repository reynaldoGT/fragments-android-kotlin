package com.dosan.fragment_land_scape

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Detalles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        /*val index = intent.getIntExtra("INDEX", 0)
        val foto = findViewById<ImageView>(R.id.ivFoto)
        foto.setImageResource(Lista_Peliculas.peliculas?.get(index)?.imagen!!)*/
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }
        if (savedInstanceState == null) {
            val fdetalles = Contenido_Peliculas()
            fdetalles.arguments = intent.extras
            supportFragmentManager.beginTransaction().add(R.id.container, fdetalles).commit()
        }
    }
}
