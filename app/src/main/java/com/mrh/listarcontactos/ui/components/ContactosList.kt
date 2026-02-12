package com.mrh.listarcontactos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.mrh.listarcontactos.Contacto
import java.math.BigInteger


@Composable
fun MainView(modifier: Modifier = Modifier, navController: NavController? = null) {
    // 1. Lista de contactos inicial (puedes añadir algunos de prueba)
    val contactos = remember {
        mutableStateListOf<Contacto>(
            Contacto("Marta","Pepe" , "marta@mail.com", BigInteger("600000000")),
            Contacto("Juan", "Pepe", "juan@mail.com", BigInteger("600000000"))
        )
    }

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true } // 2. Abrir el diálogo
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir contacto"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactosList(contactos = contactos, navController = navController)
        }

        if (showDialog) {
            AddContactoDialog(
                onDismiss = { showDialog = false },
                onConfirm = { nuevoNombre, nuevoTelefono ->
                    // 3. Crear el objeto y añadirlo a la lista
                    val nuevoContacto = Contacto(
                        nombre = nuevoNombre,
                        apellido = "Pepe",
                        telefono = BigInteger(nuevoTelefono),
                        mail = "${nuevoNombre.lowercase()}@mail.com",
                        imagenId = null // Por defecto sin foto
                    )
                    contactos.add(nuevoContacto)
                    showDialog = false // Cerrar tras añadir
                }
            )
        }
    }
}
@Composable
fun ContactosList(
    contactos: List<Contacto>,
    modifier: Modifier = Modifier,
    navController: NavController?
){
    LazyColumn(
        modifier = modifier.padding(horizontal = 10.dp),
    ) {

        items(contactos){  contacto ->
            ContactoRowCard(contacto=contacto, onClick = {
                navController?.navigate(DetalleContactoDestino(contacto.nombre, contacto.telefono.toString(), contacto.mail))
            })
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
    }
}
@Composable
fun AddContactoDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit // Pasamos los datos al confirmar
) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        // Usamos un Card o Surface para que el diálogo tenga forma y color
        Card(
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nuevo Contacto",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(8.dp))

                TextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    singleLine = true,
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    Button(
                        onClick = {
                            if (nombre.isNotBlank() && telefono.isNotBlank()) {
                                onConfirm(nombre, telefono)
                            }
                        },
                        enabled = nombre.isNotBlank() && telefono.isNotBlank()
                    ) {
                        Text("Añadir")
                    }
                }
            }
        }
    }
}