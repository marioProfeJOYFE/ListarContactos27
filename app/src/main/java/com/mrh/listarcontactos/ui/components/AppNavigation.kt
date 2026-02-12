package com.mrh.listarcontactos.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mrh.listarcontactos.Contacto
import kotlinx.serialization.Serializable

@Serializable
object PantallaInicio

@Serializable
object PantallaAjustes

@Serializable
data class DetalleContactoDestino(
    val nombre: String,
    val telefono: String,
    val mail: String
)

@SuppressLint("RestrictedApi")
@Composable
fun AppNavigation() {

    val navBarRoutes = listOf(
        NavRoute(
            label = "Inicio",
            icon = Icons.Default.Home,
            routeObject = PantallaInicio
        ),
        NavRoute(
            label = "Ajustes",
            icon = Icons.Default.Settings,
            routeObject = PantallaAjustes
        )
    )


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showNavBar = navBarRoutes.any{ navRoute ->
        currentDestination?.hasRoute(navRoute.routeObject::class) == true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = showNavBar,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                NavigationBar {
                    navBarRoutes.forEach { navRoute ->
                        NavigationBarItem(
                            selected = currentDestination?.hasRoute(navRoute.routeObject::class) == true,
                            onClick = { navController.navigate(navRoute.routeObject) },
                            label = {
                                Text(navRoute.label)
                            },
                            icon = {
                                Icon(
                                    imageVector = navRoute.icon,
                                    contentDescription = "Icono de ${navRoute.label}"
                                )
                            }

                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            startDestination = PantallaInicio
        ) {
            composable<PantallaInicio>{
                MainView(navController=navController)
            }
            composable<PantallaAjustes>{
                Text("Holaaa")
            }
            composable<DetalleContactoDestino> { backStackEntry ->
                val contacto = backStackEntry.toRoute<DetalleContactoDestino>()
                ContactoDetailView(
                    contacto = contacto,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}