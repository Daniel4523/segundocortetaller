package com.example.segundocorte.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.segundocorte.R

class InicioFragment : Fragment() {

    private lateinit var txtBienvenida: TextView
    private lateinit var txtInfoTienda: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        txtBienvenida = view.findViewById(R.id.txtBienvenida)
        txtInfoTienda = view.findViewById(R.id.txtInfoTienda)


        sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("nombre", "usuario")

        txtBienvenida.text = "¡Bienvenido, $nombre!"
        return view
    }
}
