package com.example.todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.screens.TelaAdicionar
import com.example.todo.screens.TelaEditar
import com.example.todo.screens.TelaPrincipal

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