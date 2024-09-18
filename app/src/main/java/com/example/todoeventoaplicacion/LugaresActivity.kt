package com.example.todoeventoaplicacion

//Genser Catalan
//Pantalla Lugares de Eventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoeventoaplicacion.ui.theme.TodoEventoAplicacionTheme

class LugaresActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoAplicacionTheme {
                LugaresScreen()
            }
        }
    }
}

@Composable
fun LugaresScreen() {
    // Pantalla que muestra una lista de lugares
    val lugares = listOf(
        Lugar("Guns And Roses LA", "LA Hall"),
        Lugar("Guns and Roses Denver", "Denver Hall"),
        Lugar("Guns and Roses New York", "Maddison Square Garden")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Listado de Lugares",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Lista de lugares
        for (lugar in lugares) {
            LugarItem(lugar = lugar)
        }
    }
}

@Composable
fun LugarItem(lugar: Lugar) {
    // Componente que muestra un lugar individual
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .size(40.dp)
                .padding(end = 16.dp),
            color = MaterialTheme.colorScheme.primary,
            shape = MaterialTheme.shapes.medium
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "A",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = lugar.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = lugar.location,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
        }
    }
}

data class Lugar(
    val name: String,
    val location: String
)

@Preview(showBackground = true)
@Composable
fun PreviewLugaresScreen() {
    TodoEventoAplicacionTheme {
        LugaresScreen()
    }
}
