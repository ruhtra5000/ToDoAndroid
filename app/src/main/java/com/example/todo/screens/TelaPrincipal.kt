package com.example.todo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaPrincipal(navegacao: NavController) {
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = { BotaoAdicionar(navegacao) },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Spacer(Modifier.height(100.dp))
            Text(
                text = "Lista de Tarefas",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun BotaoAdicionar(navegacao: NavController) {
    FloatingActionButton(
        onClick = { navegacao.navigate("telaAdicionar") }
    ) {
        Icon(
            painter = painterResource(R.drawable.add32),
            contentDescription = "Adicionar"
        )
    }
}