package com.example.recyclerview

import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.util.*

class MainActivity : AppCompatActivity() {

    var lista: RecyclerView? = null
    var adaptador: AdaptadorCustom? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var isActionMode = false
    var actionMode:ActionMode? = null

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

        val callback = object : ActionMode.Callback{
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.iEliminar -> {
                        adaptador?.eliminarSeleccionados()
                    }
                    else->{return true}
                }
                adaptador?.terminarActionMode()
                mode?.finish()
                isActionMode = false

                return true
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                //inicializar action mode
                adaptador?.iniciarActionMode()
                actionMode = mode
                //inflar menu
                menuInflater.inflate(R.menu.menu_contextual, menu!!)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode?.title="0 seleccionados "
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                //Destruir action mode
                adaptador?.destruirActionMode()
                isActionMode = false
            }
        }

        adaptador = AdaptadorCustom(platillos, object : InterfaceClickListener {
            override fun click(view: View, index: Int) {
                Toast.makeText(applicationContext, platillos.get(index).nombre, Toast.LENGTH_SHORT).show()
            }
        }, object : InterfaceLongClickListener {
            override fun longClick(vista: View, index: Int) {
                if (!isActionMode){
                    startSupportActionMode(callback)
                    isActionMode = true
                    adaptador?.seleccionarItem(index)
                }else{
                    //Hacer selecciones o deselecciones
                    adaptador?.seleccionarItem(index)
                }
                actionMode?.title = adaptador?.obtenerNumeroElementosSeleccionados().toString() + " seleccionados"
            }
        })
        lista?.adapter = adaptador

        val swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeToRefresh.setOnRefreshListener {
            for (i in 1..100000) {
                swipeToRefresh.isRefreshing = false
                platillos.add(Platillo("Alas", 250.0, 3.5F, R.drawable.platillo10))
                adaptador?.notifyDataSetChanged()
            }
        }
    }
}