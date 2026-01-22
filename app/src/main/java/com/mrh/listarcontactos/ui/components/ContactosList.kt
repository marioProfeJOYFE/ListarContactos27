package com.mrh.listarcontactos.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mrh.listarcontactos.Contacto
import com.mrh.listarcontactos.R
import java.math.BigInteger


@Composable
fun MainView(modifier: Modifier = Modifier) {

    val listaContactos = remember {
        mutableStateListOf(
            Contacto(
                nombre = "VINI",
                apellido = "Rios",
                mail = "mario.rios@iepgroup.es",
                telefono = BigInteger("1234567"),
                imagenId = R.drawable.vini
            )
        )
    }

    ContactosList(listaContactos, modifier)


}

@Composable
fun ContactosList(contactos: List<Contacto>, modifier: Modifier = Modifier){
    LazyColumn(
        modifier
    ) {

        items(contactos){  contacto ->
            ContactoRowCard(contacto)
        }
    }
}