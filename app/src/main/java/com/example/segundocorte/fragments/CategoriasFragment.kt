package com.example.segundocorte.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.segundocorte.R

class CategoriasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoriaAdapter


    private val categorias = listOf(
        Categoria("Electrónica", R.drawable.ic_electronica, 1),
        Categoria("Ropa", R.drawable.ic_ropa, 1),
        Categoria("Hogar", R.drawable.ic_hogar, 1),
        Categoria("Deportes", R.drawable.ic_deportes, 1),
        Categoria("Accesorios", R.drawable.ic_accesorios, 1)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categorias, container, false)
        recyclerView = view.findViewById(R.id.recyclerCategorias)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CategoriaAdapter(categorias) { categoria ->

            when (categoria.nombre) {
                "Electrónica" -> findNavController().navigate(R.id.FragmentElectronica)
                "Ropa" -> findNavController().navigate(R.id.FragmentRopa)
                "Hogar" -> findNavController().navigate(R.id.FragmentHogar)
                "Deportes" -> findNavController().navigate(R.id.fragmentDeportes)
                "Accesorios" -> findNavController().navigate(R.id.FragmentAccesorios)
            }
        }

        recyclerView.adapter = adapter
        return view
    }


    data class Categoria(
        val nombre: String,
        val imagenResId: Int,
        val cantidadProductos: Int
    )


    class CategoriaAdapter(
        private val categorias: List<Categoria>,
        private val onClick: (Categoria) -> Unit
    ) : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

        inner class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nombre: TextView = itemView.findViewById(R.id.nombreCategoria)
            val cantidad: TextView = itemView.findViewById(R.id.cantidadProductos)
            val imagen: ImageView = itemView.findViewById(R.id.imagenCategoria)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categoria, parent, false)
            return CategoriaViewHolder(view)
        }

        override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
            val categoria = categorias[position]
            holder.nombre.text = categoria.nombre
            holder.cantidad.text = "${categoria.cantidadProductos} productos"
            holder.imagen.setImageResource(categoria.imagenResId)
            holder.itemView.setOnClickListener { onClick(categoria) }
        }

        override fun getItemCount(): Int = categorias.size
    }
}
