package com.example.recyclerview

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity: AppCompatActivity() {

    var lista:RecyclerView? = null
    var adaptador
    var layoutManager:RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platillos = ArrayList<Platillo>()

        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo01))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo02))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo03))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo04))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo05))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo06))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo07))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo08))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo09))
        platillos.add(Platillo("Plato 1", 250.0, 3.5, R.drawable.platillo10))

        lista = findViewById(R.id.listaRecycler)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        adaptador = AdaptadorCustom()
        lista?.adapter = adaptador
    }
}