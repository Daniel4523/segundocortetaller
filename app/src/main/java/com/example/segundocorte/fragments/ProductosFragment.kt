package com.example.segundocorte.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.segundocorte.R
import org.json.JSONArray
import org.json.JSONObject

class ProductosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)
        val layoutProductos = view.findViewById<ViewGroup>(R.id.layoutProductos)

        val categoriaFiltrada = arguments?.getString("categoria")
        val productosFiltrados = if (categoriaFiltrada != null) {
            productosDisponibles.filter { it.categoria == categoriaFiltrada }
        } else {
            productosDisponibles
        }

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
                Carrito.agregarProducto(producto)
                Carrito.guardarEnSharedPreferences(requireContext())
                Toast.makeText(requireContext(), "${producto.nombre} añadido al carrito", Toast.LENGTH_SHORT).show()
            }

            layoutProductos.addView(productoView)
        }

        return view
    }

    companion object {
        private val productosDisponibles = listOf(
            ProductoCarrito("Camiseta Deportiva", 30000.000, categoria = "Ropa", imagenResId = R.drawable.ic_ropa),
            ProductoCarrito("Auriculares Bluetooth", 200000.000, categoria = "Electrónica", imagenResId = R.drawable.ic_electronica),
            ProductoCarrito("Licuadora", 350000.000, categoria = "Hogar", imagenResId = R.drawable.ic_hogar),
            ProductoCarrito("Balón de fútbol", 20000.000, categoria = "Deportes", imagenResId = R.drawable.ic_deportes),
            ProductoCarrito("Reloj elegante", 150000.000, categoria = "Accesorios", imagenResId = R.drawable.ic_accesorios)
        )

        fun newInstance(categoria: String?): ProductosFragment {
            val fragment = ProductosFragment()
            val args = Bundle()
            args.putString("categoria", categoria)
            fragment.arguments = args
            return fragment
        }
    }

    object Carrito {
        val productos = mutableListOf<ProductoCarrito>()

        fun agregarProducto(producto: ProductoCarrito) {
            productos.add(producto)
        }

        fun obtenerTotal(): Double = productos.sumOf { it.precio * it.cantidad }

        fun obtenerCantidadTotal(): Int = productos.sumOf { it.cantidad }

        fun limpiar() = productos.clear()

        fun guardarEnSharedPreferences(context: Context) {
            val prefs = context.getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            val jsonArray = JSONArray()
            for (producto in productos) {
                val json = JSONObject()
                json.put("nombre", producto.nombre)
                json.put("precio", producto.precio)
                json.put("cantidad", producto.cantidad)
                json.put("categoria", producto.categoria)
                json.put("imagenResId", producto.imagenResId)
                jsonArray.put(json)
            }

            editor.putString("carrito", jsonArray.toString())
            editor.apply()
        }

        fun cargarDesdeSharedPreferences(context: Context) {
            val prefs = context.getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)
            val jsonString = prefs.getString("carrito", null) ?: return

            productos.clear()

            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val json = jsonArray.getJSONObject(i)
                val producto = ProductoCarrito(
                    nombre = json.getString("nombre"),
                    precio = json.getDouble("precio"),
                    cantidad = json.getInt("cantidad"),
                    categoria = json.getString("categoria"),
                    imagenResId = json.getInt("imagenResId")
                )
                productos.add(producto)
            }
        }
    }

    data class ProductoCarrito(
        val nombre: String,
        val precio: Double,
        val cantidad: Int = 1,
        val categoria: String,
        val imagenResId: Int
    )
}
