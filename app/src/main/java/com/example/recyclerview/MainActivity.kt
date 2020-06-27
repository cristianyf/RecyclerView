package com.example.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    var lista: RecyclerView? = null
    var adaptador: AdaptadorCustom? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platillos = ArrayList<Platillo>()

        platillos.add(Platillo("Plato 1", 250.0, 3.5F, R.drawable.platillo01))
        platillos.add(Platillo("Plato 2", 250.0, 3.5F, R.drawable.platillo02))
        platillos.add(Platillo("Plato 3", 250.0, 3.5F, R.drawable.platillo03))
        platillos.add(Platillo("Plato 4", 250.0, 3.5F, R.drawable.platillo04))
        platillos.add(Platillo("Plato 5", 250.0, 3.5F, R.drawable.platillo05))
        platillos.add(Platillo("Plato 6", 250.0, 3.5F, R.drawable.platillo06))
        platillos.add(Platillo("Plato 7", 250.0, 3.5F, R.drawable.platillo07))
        platillos.add(Platillo("Plato 8", 250.0, 3.5F, R.drawable.platillo08))
        platillos.add(Platillo("Plato 9", 250.0, 3.5F, R.drawable.platillo09))
        platillos.add(Platillo("Plato 10", 250.0, 3.5F, R.drawable.platillo10))

        lista = findViewById(R.id.listaRecycler)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        adaptador = AdaptadorCustom(platillos, object : InterfaceClickListener{
            override fun click(view: View, index: Int) {
                Toast.makeText(applicationContext, platillos.get(index).nombre, Toast.LENGTH_SHORT).show()
            }

        })
        lista?.adapter = adaptador
    }
}