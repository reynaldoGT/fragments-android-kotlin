package com.dosan.fragment_land_scape

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class Contenido_Peliculas : Fragment() {
    var vista: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_contenido__peliculas, container, false)
        cambiarFoto()
        return vista
    }

     fun obtenerIndex(): Int {
        val index = arguments?.getInt("INDEX", 0)!!
        return index
    }

     fun cambiarFoto() {
        val foto = vista!!.findViewById<ImageView>(R.id.ivFoto)
        foto.setImageResource(Lista_Peliculas.peliculas?.get(obtenerIndex())?.imagen!!)
    }

    fun nuevaInstancia(index: Int): Contenido_Peliculas {
        val f = Contenido_Peliculas()
        val args = Bundle()
        args.putInt("INDEX", index)
        f.arguments = args
        return f
    }


}