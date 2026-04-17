package com.example.todo.screens

import android.text.Layout
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.data.BancoProvider
import com.example.todo.data.ViewModelFactory
import com.example.todo.data.ViewModelGeral
import com.example.todo.entity.Tarefa

@Composable
fun TelaPrincipal(navegacao: NavController) {
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = { BotaoAdicionar(navegacao) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Text(
                text = "Lista de Tarefas",
                fontSize = 24.sp
            )

            Spacer(Modifier.height(15.dp))

            ListaTarefas(navegacao)
        }
    }
}

@Composable
fun ListaTarefas(navegacao: NavController) {
    // Conexão com o banco
    val contexto = LocalContext.current
    val db = BancoProvider.getBanco(contexto)
    val dao = db.tarefaDao()
    val viewModel: ViewModelGeral = viewModel(
        factory = ViewModelFactory(dao)
    )

    // Constantes
    val tarefas = viewModel.tarefas.collectAsState().value
    val coresBotao = ButtonColors(
        containerColor = Color.Transparent,
        contentColor = Color.White,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = Color.White
    )

    // Confirmação de exclusão genérica
    var abrirDialog by remember { mutableStateOf(false) }
    var tarefaSelec by remember { mutableStateOf<Tarefa?>(null) }

    if (abrirDialog && tarefaSelec != null) {
        AlertDialog(
            title = { Text("Confirmação de Exclusão") },
            text = { Text("Deseja excluir a tarefa: \"${tarefaSelec!!.conteudo}\"?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deletar(tarefaSelec!!);
                        abrirDialog = false
                        Toast.makeText(contexto, "Tarefa deletada!", Toast.LENGTH_SHORT).show()
                    }
                ) { Text("Sim") }
            },
            dismissButton = {
                Button(onClick = { abrirDialog = false }) { Text("Não") }
            },
            onDismissRequest = { abrirDialog = false }
        )
    }

    // Design
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
    ) {
        items(
            items = tarefas,
            key = { it.id }
        ) { tarefa ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(corner = CornerSize(15.dp)))
                    .wrapContentHeight()
                    .padding(all = 10.dp)
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = tarefa.concluida,
                    onCheckedChange = { viewModel.concluir(tarefa) }
                )

                Spacer(Modifier.width(10.dp))

                Column() {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.wrapContentHeight()
                    ) {
                        Text(
                            text = tarefa.conteudo,
                            fontSize = 16.sp,
                            textDecoration = if (tarefa.concluida) TextDecoration.LineThrough else null,
                            maxLines = 3,
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = Color.LightGray
                    )

                    Row() {
                        Button(
                            onClick = { navegacao.navigate("telaEditar/${tarefa.id}") },
                            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                            modifier = Modifier.weight(1F),
                            border = BorderStroke(width = 1.dp, color = Color.Yellow),
                            colors = coresBotao
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.edit24),
                                contentDescription = "Editar"
                            )
                        }

                        Spacer(Modifier.width(10.dp))

                        Button(
                            onClick = {
                                tarefaSelec = tarefa;
                                abrirDialog = true;
                            },
                            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                            modifier = Modifier.weight(1F),
                            border = BorderStroke(width = 1.dp, color = Color.Red),
                            colors = coresBotao
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.delete24),
                                contentDescription = "Deletar"
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(15.dp))
        }
    }
}

@Composable
fun BotaoAdicionar(navegacao: NavController) {
    FloatingActionButton(
        onClick = { navegacao.navigate("telaAdicionar") },
        shape = RoundedCornerShape(corner = CornerSize(100.dp)),
        containerColor = Color(red = 0.0235F, green = 0.4745F, blue = 0.9765F),
        contentColor = Color.White
    ) {
        Icon(
            painter = painterResource(R.drawable.add32),
            contentDescription = "Adicionar"
        )
    }
}