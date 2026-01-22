package com.mrh.listarcontactos.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mrh.listarcontactos.Contacto


@Composable
fun ContactoRowCard(contacto: Contacto) {
    Card(){
        Text(contacto.nombre)
    }
}