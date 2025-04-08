package com.example.segundocorte.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.segundocorte.R

class CarritoFragment : Fragment() {

    private lateinit var layoutCarrito: LinearLayout
    private lateinit var txtResumen: TextView
    private lateinit var btnPagar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)

        layoutCarrito = view.findViewById(R.id.layoutCarrito)
        txtResumen = view.findViewById(R.id.txtResumen)
        btnPagar = view.findViewById(R.id.btnPagar)

        ProductosFragment.Carrito.cargarDesdeSharedPreferences(requireContext())
        mostrarProductos()

        btnPagar.setOnClickListener {
            Toast.makeText(requireContext(), "Gracias por su compra", Toast.LENGTH_LONG).show()
            ProductosFragment.Carrito.limpiar()
            ProductosFragment.Carrito.guardarEnSharedPreferences(requireContext())
            mostrarProductos()
        }

        return view
    }

    private fun mostrarProductos() {
        layoutCarrito.removeAllViews()

        var totalProductos = 0
        var precioTotal = 0.0

        for ((index, producto) in ProductosFragment.Carrito.productos.withIndex()) {
            val itemLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24, 24, 24, 24)
                setBackgroundResource(R.drawable.edit_text_rounded)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 24)
                }
            }

            val nombre = TextView(requireContext()).apply {
                text = "📦 ${producto.nombre}"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
            }

            val cantidad = TextView(requireContext()).apply {
                text = "Cantidad: ${producto.cantidad}"
            }

            val precio = TextView(requireContext()).apply {
                text = "Precio unitario: $${producto.precio}"
            }

            val subtotal = TextView(requireContext()).apply {
                text = "Subtotal: $${"%.2f".format(producto.precio * producto.cantidad)}"
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark))
            }

            val btnEliminar = Button(requireContext()).apply {
                text = "Eliminar"
                setBackgroundResource(R.drawable.boton_redondeado)
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                setOnClickListener {
                    ProductosFragment.Carrito.productos.removeAt(index)
                    ProductosFragment.Carrito.guardarEnSharedPreferences(requireContext())
                    mostrarProductos()
                }
            }

            itemLayout.apply {
                addView(nombre)
                addView(cantidad)
                addView(precio)
                addView(subtotal)
                addView(btnEliminar)
            }

            layoutCarrito.addView(itemLayout)

            totalProductos += producto.cantidad
            precioTotal += producto.precio * producto.cantidad
        }

        txtResumen.text = "Resumen: $totalProductos productos, Total: $${"%.2f".format(precioTotal)}"
        layoutCarrito.addView(txtResumen)
        layoutCarrito.addView(btnPagar)
    }
}
