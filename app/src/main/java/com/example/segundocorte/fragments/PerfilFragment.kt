package com.example.segundocorte.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.segundocorte.R
import com.example.segundocorte.activities.perfilActivity

class PerfilFragment : Fragment() {

    private lateinit var txtNombreCompleto: TextView
    private lateinit var txtCorreo: TextView
    private lateinit var txtTelefono: TextView
    private lateinit var btnEditarPerfil: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        txtNombreCompleto = view.findViewById(R.id.txtNombreCompleto)
        txtCorreo = view.findViewById(R.id.txtCorreo)
        txtTelefono = view.findViewById(R.id.txtTelefono)
        btnEditarPerfil = view.findViewById(R.id.btnEditarPerfil)

        val sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("nombre", "") ?: ""
        val apellido = sharedPreferences.getString("apellido", "") ?: ""
        val telefono = sharedPreferences.getString("telefono", "") ?: ""
        val email = sharedPreferences.getString("email", "No registrado") ?: "No registrado"

        txtNombreCompleto.text = "$nombre $apellido"
        txtCorreo.text = "Correo: $email"
        txtTelefono.text = "Teléfono: $telefono"

        btnEditarPerfil.setOnClickListener {
            val intent = Intent(requireContext(), perfilActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}