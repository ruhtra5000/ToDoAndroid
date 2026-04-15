package com.example.todo.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.todo.R

val topBarName = "To-Do"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar () {
    TopAppBar(
        title = { Text(topBarName) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBack (navegacao: NavController) {
    TopAppBar(
        title = { Text(topBarName) },
        navigationIcon = {
            IconButton(
                onClick = { navegacao.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back24),
                    contentDescription = "Voltar"
                )
            }
        }
    )
}