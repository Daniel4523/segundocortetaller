package com.example.segundocorte.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.segundocorte.R

class FragmentElectronica : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)
        val layoutProductos = view.findViewById<ViewGroup>(R.id.layoutProductos)

        val productosFiltrados = productosDisponibles.filter { it.categoria == "Electrónica" }

        for (producto in productosFiltrados) {
            val productoView = layoutInflater.inflate(R.layout.item_producto, layoutProductos, false)

            val txtNombre = productoView.findViewById<TextView>(R.id.txtNombreProducto)
            val txtPrecio = productoView.findViewById<TextView>(R.id.txtPrecioProducto)
            val imgProducto = productoView.findViewById<ImageView>(R.id.imgProducto)
            val btnAgregar = productoView.findViewById<Button>(R.id.btnAgregarProducto)

            txtNombre.text = producto.nombre
            txtPrecio.text = "$${producto.precio}"
            imgProducto.setImageResource(producto.imagenResId)

            btnAgregar.setOnClickListener {
                ProductosFragment.Carrito.agregarProducto(producto)
                ProductosFragment.Carrito.guardarEnSharedPreferences(requireContext())
                Toast.makeText(requireContext(), "${producto.nombre} añadido al carrito", Toast.LENGTH_SHORT).show()
            }

            layoutProductos.addView(productoView)
        }

        return view
    }

    companion object {
        private val productosDisponibles = listOf(
            ProductosFragment.ProductoCarrito(
                nombre = "Auriculares Bluetooth",
                precio = 200000.000,
                categoria = "Electrónica",
                imagenResId = R.drawable.ic_electronica
            )
        )
    }
}
