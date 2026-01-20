package com.mrh.listarcontactos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mrh.listarcontactos.ui.theme.ListarContactosTheme
import java.math.BigInteger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListarContactosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeView(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeView(modifier: Modifier = Modifier) {

    val listaNombres = remember {
        mutableStateListOf(
            "Pepe",
            "Almudena",
            "Alberto",
            "Pepe",
            "Almudena",
            "Alberto"
        )
    }
    val listaContactos = remember {
        mutableStateListOf(
            Contacto(
                nombre = "Mario",
                apellido = "Rios",
                mail = "mario.rios@iepgroup.es",
                telefono = BigInteger("1234567"),
                imagenId = R.drawable.vini
            )
        )
    }
    var inputNombre by remember { mutableStateOf("") }

    /*
    Column(
        modifier = modifier
    )
    {
        Text("nombre")
        //FORMAS DE ITERAR UNA LISTA
        /*
        for(nombre in listaNombres){
            Text(text = nombre)
        }
        */
        listaNombres.forEach { nombre ->
            Text(text = nombre)
        }
    }*/

    LazyVerticalGrid(
        modifier = modifier.padding(20.dp),
        columns = GridCells.Adaptive(minSize = 100.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Pintar un elemento particular
        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            TextField(
                value = inputNombre,
                onValueChange = { textoTeclado ->
                    inputNombre = textoTeclado
                },
                modifier = Modifier.padding(20.dp)
            )
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            Button(
                onClick = {
                    var nuevo = Contacto(
                        nombre = inputNombre,
                        apellido = "",
                        mail = "",
                        telefono = BigInteger("00")
                    )
                    listaContactos.add(nuevo)
                    listaNombres.add(inputNombre)
                    inputNombre = ""
                },
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)
            ) {
                Text("Hola")
            }
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            Image(
                modifier = Modifier.height(100.dp),
                painter = painterResource(R.drawable.vini),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
        }

        items(items = listaContactos) { contacto ->

            Card(

                onClick = {
                    Log.d("DEMO", "Aqui me encuentro")
                    listaContactos.remove(contacto)
                }
            ) {
                if (contacto.imagenId != null) {
                    Image(
                        modifier = Modifier.height(100.dp),
                        painter = painterResource(contacto.imagenId?:0),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight
                    )
                }

                Text(
                    text = contacto.nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}