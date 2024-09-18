package com.example.todoeventoaplicacion

//Genser Catalan
//Pantalla Inicial/Favorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoeventoaplicacion.ui.theme.TodoEventoAplicacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoAplicacionTheme {
                val navController = rememberNavController()

                // Configuración de navegación
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") { MainContent(navController) }
                    composable("cardDetail") { CardScreen(navController) }
                    composable("profile") { PerfilScreen() }
                    composable("lugares") { LugaresScreen() }
                }

            }
        }
    }
}

@Composable
fun MainContent(navController: androidx.navigation.NavController) {
    // Pantalla principal que muestra el encabezado y una cuadrícula de las Cards de eventos
    Column {
        AppHeader(navController)

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Your Favorites",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        EventCardGrid(navController)
    }
}


@Composable
fun AppHeader(navController: androidx.navigation.NavController) {
    // Encabezado con el título y el botón de navegación al perfil
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "TodoEventos+",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(start = 6.dp)
        )

        IconButton(onClick = {
            navController.navigate("profile")
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home Icon",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun EventCardGrid(navController: androidx.navigation.NavController) {
    // Cuadrícula de Cards de eventos
    val items = listOf(
        Item("Title 1", "Supporting text", R.drawable.travis),
        Item("Title 2", "Supporting text", R.drawable.image2),
        Item("Title 3", "Supporting text", R.drawable.image3),
        Item("Title 4", "Supporting text", R.drawable.image4)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { item ->
            CardItem(item = item, navController)
        }
    }
}

@Composable
fun CardItem(item: Item, navController: androidx.navigation.NavController) {
    // Cards de evento individual que navega a la pantalla de detalle del evento al hacer clic
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("cardDetail")
            },
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = item.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

data class Item(val title: String, val description: String, val imageRes: Int)

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    TodoEventoAplicacionTheme {
        val navController = rememberNavController()
        MainContent(navController)
    }
}
