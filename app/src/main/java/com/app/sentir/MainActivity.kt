package com.app.sentir

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.sentir.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Corrección del error de sintaxis aquí
        super.onCreate(savedInstanceState)
        setContent {
            SentirTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundGray
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(24.dp))
        NewAlertBanner()
        Spacer(modifier = Modifier.height(16.dp))
        AlertDetailsCard()
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Logo",
                tint = AlertRed,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "SENTIR",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "Alerta · Prevención · Tu seguridad",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Perfil",
            tint = TextSecondary,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun NewAlertBanner() {
    Card(
        colors = CardDefaults.cardColors(containerColor = AlertBackground),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "ALERTA",
                color = AlertRed,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Nueva alerta en tu zona",
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Se ha emitido una alerta para el área donde te encuentras. Revisa el detalle y sigue las recomendaciones.",
                color = TextTertiary,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun AlertDetailsCard() {
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Sección Ubicación
            Text(text = "Tu zona", fontSize = 12.sp, color = TextSecondary)
            Text(
                text = "Barrio La Esperanza",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(text = "Ocaña, Norte de Santander", fontSize = 14.sp, color = TextSecondary)

            // Corrección de Divider a HorizontalDivider
            HorizontalDivider(color = DividerColor, modifier = Modifier.padding(vertical = 12.dp))

            // Sección Riesgo y Fecha
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = AlertRed,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Riesgo alto",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "09:24 a. m.\n23 sept. 2025",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            // Corrección de Divider a HorizontalDivider
            HorizontalDivider(color = DividerColor, modifier = Modifier.padding(vertical = 12.dp))

            // Sección Descripción
            Text(text = "Tipo de alerta", fontSize = 12.sp, color = TextSecondary)
            Text(
                text = "Lluvias fuertes",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Se esperan lluvias intensas en la zona durante las próximas horas, con posible aumento de caudales y deslizamientos.",
                fontSize = 14.sp,
                color = TextTertiary,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de acción
            Button(
                onClick = {
                    Toast.makeText(context, "Ver detalles", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = AlertRed),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Ver detalle de la alerta →",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}