package com.example.todo.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.screens.TelaAdicionar
import com.example.todo.screens.TelaEditar
import com.example.todo.screens.TelaPrincipal
import com.example.todo.screens.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navegacao() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "telaPrincipal") {
        composable("telaPrincipal") {
            TelaPrincipal(navController)
        }

        composable("telaAdicionar") {
            TelaAdicionar(navController)
        }

        composable(
            route = "telaEditar/{id}",
            arguments = listOf(navArgument("id") {type = NavType.IntType})
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("id") ?.let { id ->
                TelaEditar(navController, id)
            }
        }
    }

}