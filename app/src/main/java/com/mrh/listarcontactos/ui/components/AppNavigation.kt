package com.mrh.listarcontactos.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mrh.listarcontactos.Contacto
import kotlinx.serialization.Serializable
import java.math.BigInteger


@Serializable
object PantallaInicio

@Serializable
data class DetalleContactoDestination(
    val nombre: String,
    val apellido: String,
    val mail: String,
    val telefono: String
)


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PantallaInicio
    ) {
        composable<PantallaInicio>{
            MainView(navController)
        }

        composable<DetalleContactoDestination>{ rutaNavegacion ->
            val contacto = rutaNavegacion.toRoute<DetalleContactoDestination>()
            val contactoReal = Contacto(
                nombre = contacto.nombre,
                apellido = contacto.apellido,
                mail = contacto.mail,
                telefono = BigInteger(contacto.telefono)
            )
            ContactoDetailScreen(
                contacto = contactoReal,
                //Volver a la pantalla anterior
                onBackClick = {
                    navController.popBackStack()
            })
        }

    }
}