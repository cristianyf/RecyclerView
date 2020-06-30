package com.example.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom(items: ArrayList<Platillo>, var listener: InterfaceClickListener, var longClickListener: InterfaceLongClickListener) : RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items: ArrayList<Platillo>? = null
    var multiSeleccion = false
    var itemsSeleccionados: ArrayList<Int>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
        itemsSeleccionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_platillo, parent, false)
        viewHolder = ViewHolder(vista, listener, longClickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.foto?.setImageResource(item?.foto!!)
        holder.nombre?.text = item?.nombre
        holder.precio?.text = "$" + item?.precio.toString()
        holder.rating?.rating = item?.rating!!

        if (itemsSeleccionados?.contains(position)!!) {
            holder.vista.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }

    fun iniciarActionMode() {
        multiSeleccion = true
    }

    fun destruirActionMode() {
        multiSeleccion = false
        itemsSeleccionados?.clear()
        notifyDataSetChanged()
    }

    fun terminarActionMode() {
        //eliminar elementos seleccionados
        for (item in itemsSeleccionados!!) {
            itemsSeleccionados?.remove(item)
        }
        multiSeleccion = false
        notifyDataSetChanged()
    }

    fun seleccionarItem(index: Int) {
        if (multiSeleccion) {
            if (itemsSeleccionados?.contains(index)!!) {
                itemsSeleccionados?.remove(index)
            } else {
                itemsSeleccionados?.add(index)
            }
            notifyDataSetChanged()
        }
    }

    fun obtenerNumeroElementosSeleccionados(): Int {
        return itemsSeleccionados?.count()!!
    }

    fun eliminarSeleccionados(){
        if  (itemsSeleccionados?.count()!! > 0){
            val itemEliminados = ArrayList<Platillo>()

            for (index in itemsSeleccionados!!){
                itemEliminados.add(items?.get(index)!!)
            }
            items?.removeAll(itemEliminados)
            itemsSeleccionados?.clear()
        }
    }

    class ViewHolder(vista: View, listener: InterfaceClickListener, longClickListener: InterfaceLongClickListener) : RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener {
        var vista = vista
        var foto: ImageView? = null
        var nombre: TextView? = null
        var precio: TextView? = null
        var rating: RatingBar? = null
        var listener: InterfaceClickListener? = null
        var longListener: InterfaceLongClickListener? = null

        init {
            foto = vista.findViewById(R.id.ivFoto)
            nombre = vista.findViewById(R.id.tvNombre)
            precio = vista.findViewById(R.id.tvPrecio)
            rating = vista.findViewById(R.id.tvRating)

            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.click(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClick(v!!, adapterPosition)
            return true
        }
    }
}