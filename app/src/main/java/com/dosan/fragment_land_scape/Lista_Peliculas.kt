package com.dosan.fragment_land_scape

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.layout_fragment.view.*


class Lista_Peliculas : Fragment() {
    companion object {
        var peliculas: ArrayList<Pelicula>? = null
    }

    var nombrePeliculas: ArrayList<String>? = null
    var lista: ListView? = null
    var hayDoblePanel = false
    var posicioActual = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configurarListView()

        val frameDetalles = activity!!.findViewById<FrameLayout>(R.id.detalles)
        // para saber si nuestro fragment esta visible
        hayDoblePanel = frameDetalles != null && frameDetalles.visibility == View.VISIBLE
        if (savedInstanceState != null) {
            posicioActual = savedInstanceState.getInt("INDEX", 0)
        }
        if (hayDoblePanel) {
            lista?.choiceMode = ListView.CHOICE_MODE_SINGLE
            mostrarDetalles(posicioActual)
        }
    }

    private fun configurarListView() {
        peliculas = ArrayList()
        peliculas?.add(Pelicula("Finding nemo", R.drawable.p1_finding_nemo))
        peliculas?.add(Pelicula("Moon Ligth", R.drawable.p2_moon_ligth))
        peliculas?.add(Pelicula("Batman", R.drawable.p3_batman_joker))
        peliculas?.add(Pelicula("Guadianes de la galaxia", R.drawable.p4_guardians_galaxy))

        nombrePeliculas = obtenerNombrePelicula(peliculas!!)

        val adaptador =
            ArrayAdapter(
                activity!!.applicationContext,
                android.R.layout.simple_list_item_activated_1,
                nombrePeliculas!!
            )
        lista = activity!!.findViewById(R.id.lista)
        lista?.adapter = adaptador
        lista?.setOnItemClickListener { parent, view, position, id ->
            mostrarDetalles(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var vista = inflater.inflate(R.layout.fragment_lista__peliculas, container, false)

        return vista
    }

    fun mostrarDetalles(index: Int) {
        posicioActual = index
        if (hayDoblePanel) {
            var fDetalles =
                activity!!.supportFragmentManager.findFragmentById(R.id.detalles) as? Contenido_Peliculas
            if (fDetalles == null || fDetalles.obtenerIndex() != index) {
                fDetalles = Contenido_Peliculas().nuevaInstancia(index)

                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.detalles, fDetalles)

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                fragmentTransaction.commit()
            }
        } else {
            val intent = Intent(activity, Detalles::class.java)
            intent.putExtra("INDEX", index)
            startActivity(intent)
        }
    }

    fun obtenerNombrePelicula(peliculas: ArrayList<Pelicula>): ArrayList<String> {
        val nombres = ArrayList<String>()
        for (pelicula in peliculas) {
            nombres.add(pelicula.nombre)
        }
        return nombres
    }

}