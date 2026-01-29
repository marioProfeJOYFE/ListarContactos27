package com.mrh.listarcontactos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrh.listarcontactos.Contacto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactoDetailScreen(contacto: Contacto, onBackClick: () -> Unit){

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detalles del Contacto")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                contentAlignment = Alignment.Center
            ){
                Card(
                    modifier = Modifier.size(150.dp).clip(CircleShape)
                ) {

                }
                Text(contacto.nombre[0].toString(), fontSize = 80.sp)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = contacto.nombre, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.size(60.dp).clip(CircleShape)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ){
                            Icon(
                                imageVector = Icons.Filled.Phone,
                                contentDescription = null
                            )
                        }
                    }
                    Text("Llamar", textAlign = TextAlign.Center)
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.size(60.dp).clip(CircleShape)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ){
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = null
                            )
                        }
                    }
                    Text("Mail", textAlign = TextAlign.Center)
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.size(60.dp).clip(CircleShape)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ){
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = null
                            )
                        }
                    }
                    Text("Compartir", textAlign = TextAlign.Center)
                }

            }
            Spacer(modifier = Modifier.padding(15.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ){
                Column(
                    Modifier.fillMaxWidth()
                        .padding(20.dp)
                ){
                    InfoRow(titulo = "Teléfono", texto = contacto.telefono.toString(), icono = Icons.Filled.Phone)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    InfoRow(titulo = "Mail", texto = contacto.mail, icono = Icons.Filled.Email)
                }

            }
        }
    }
}


@Composable
fun InfoRow(titulo: String, texto: String, icono: ImageVector){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = icono,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column{
            Text( text=titulo, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.outline)
            Text( text=texto, style = MaterialTheme.typography.bodyLarge)
        }
    }
}