package com.tuempresa.miaplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tuempresa.miaplicacion.ui.theme.MiAplicacionTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private var numeroAleatorio = Random.nextInt(100) + 1 // Generar número aleatorio al iniciar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiAplicacionTheme {
                AdivinaElNumeroScreen()
            }
        }
    }
}

@Composable
fun AdivinaElNumeroScreen() {
    var numeroIngresado by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("Ingrese un número entre 1 y 100") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = resultado)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = numeroIngresado,
                onValueChange = { numeroIngresado = it },
                label = { Text("Ingresa un número") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                val numero = numeroIngresado.toIntOrNull()
                if (numero != null && numero in 1..100) {
                    var numeroAleatorio: Int? = null;
                    resultado = when {
                        numero > numeroAleatorio!! -> "El número es menor"
                        numero < numeroAleatorio -> "El número es mayor"
                        else -> {
                            numeroAleatorio = Random.nextInt(100) + 1 // Reiniciar número aleatorio
                            "¡Felicidades! Acertaste"
                        }
                    }
                } else {
                    resultado = "Ingrese un número válido (entre 1 y 100)"
                }
            }) {
                Text("Comprobar")
            }
        }
    }
}
