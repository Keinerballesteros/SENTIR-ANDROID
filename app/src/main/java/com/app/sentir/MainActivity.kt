package com.app.sentir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.sentir.ui.theme.SentirTheme

// --- COLORES EXTRAÍDOS DEL MOCKUP ---
val BgHeader = Color(0xFF0D2C4D)
val BgApp = Color(0xFFF0F5FA)
val CardBg = Color(0xFFF8FAFC)
val TextDark = Color(0xFF0F172A)
val TextMuted = Color(0xFF64748B)

// Colores del recuadro de información
val InfoBannerBg = Color(0xFFEAF2F8)
val InfoDarkBlue = Color(0xFF1A365D)

// Colores del menú flotante
val BottomNavSelectedBg = Color(0xFF1D5C99) // Azul oscuro para el botón seleccionado
val BottomNavUnselectedColor = Color(0xFF1A365D) // Azul oscuro para los iconos inactivos

// Colores de Severidad
val CriticalRed = Color(0xFFDC2626)
val CriticalRedBg = Color(0xFFFEE2E2)
val HighOrange = Color(0xFFEA580C)
val HighOrangeBg = Color(0xFFFFEDD5)
val MediumYellow = Color(0xFFCA8A04)
val MediumYellowBg = Color(0xFFFEF9C3)

val ReasonBg = Color(0xFFF1F5F9)

// --- DATOS DE EJEMPLO ---
data class HelpRequest(
    val title: String,
    val location: String,
    val peopleInfo: String,
    val score: String,
    val severityText: String,
    val severityColor: Color,
    val severityBg: Color,
    val icon: ImageVector,
    val reason: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SentirTheme {
                Scaffold(
                    bottomBar = { FloatingEmergencyBottomNav() },
                    containerColor = BgApp
                ) { paddingValues ->
                    EmergencyScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun EmergencyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        EmergencyHeader()

        Column(modifier = Modifier.padding(16.dp)) {
            ScreenTitleRow()
            Spacer(modifier = Modifier.height(16.dp))
            ScoreInfoBanner()
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de solicitudes
            RequestCard(
                HelpRequest(
                    title = "Inundación de vivienda",
                    location = "Vereda El Paraíso, San Vicente",
                    peopleInfo = "3 personas  •  2 niños",
                    score = "96",
                    severityText = "Crítica",
                    severityColor = CriticalRed,
                    severityBg = CriticalRedBg,
                    icon = Icons.Default.WaterDamage,
                    reason = "Alta urgencia (inundación), 2 niños (vulnerabilidad), lleva 3 h esperando, a 1.2 km (cercanía)."
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            RequestCard(
                HelpRequest(
                    title = "Deslizamiento de tierra",
                    location = "Vía La Esperanza, San Vicente",
                    peopleInfo = "5 personas  •  1 adulto mayor",
                    score = "78",
                    severityText = "Alta",
                    severityColor = HighOrange,
                    severityBg = HighOrangeBg,
                    icon = Icons.Default.Terrain,
                    reason = "Alta urgencia (deslizamiento), adulto mayor (vulnerabilidad), lleva 1.5 h esperando, a 2.8 km (cercanía)."
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            RequestCard(
                HelpRequest(
                    title = "Persona herida",
                    location = "Barrio La Playa, San Vicente",
                    peopleInfo = "1 persona  •  adulto",
                    score = "62",
                    severityText = "Media",
                    severityColor = MediumYellow,
                    severityBg = MediumYellowBg,
                    icon = Icons.Default.MedicalServices,
                    reason = "Urgencia media (herida), sin vulnerabilidad adicional, lleva 45 min esperando, a 4.5 km (cercanía)."
                )
            )
        }
    }
}

@Composable
fun EmergencyHeader() {
    Surface(
        color = BgHeader,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Canvas(modifier = Modifier.matchParentSize().align(Alignment.BottomCenter)) {
                val width = size.width
                val height = size.height
                val path = Path().apply {
                    moveTo(0f, height)
                    lineTo(width * 0.25f, height * 0.4f)
                    lineTo(width * 0.5f, height * 0.7f)
                    lineTo(width * 0.8f, height * 0.2f)
                    lineTo(width, height * 0.6f)
                    lineTo(width, height)
                    close()
                }
                drawPath(path, color = Color.White.copy(alpha = 0.08f))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 16.dp, top = 48.dp, bottom = 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "SENTIR",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Equipo de emergencia",
                        fontSize = 15.sp,
                        color = Color.White.copy(alpha = 0.85f),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Surface(
                    color = Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.End) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF6EE7B7))
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Sin conexión",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = "Última sincronización\nhace 12 min",
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 9.sp,
                                textAlign = TextAlign.End,
                                lineHeight = 11.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Outlined.CloudOff,
                            contentDescription = "Sin conexión",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenTitleRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Solicitudes de ayuda",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Text(
                text = "Ordenadas por puntaje de prioridad",
                fontSize = 13.sp,
                color = TextMuted
            )
        }

        Surface(
            color = InfoBannerBg,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = null,
                    tint = InfoDarkBlue,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Más prioritarias",
                    fontSize = 12.sp,
                    color = InfoDarkBlue,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = InfoDarkBlue,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun ScoreInfoBanner() {
    Surface(
        color = InfoBannerBg,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = InfoDarkBlue,
                shape = CircleShape,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.StarBorder,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(6.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "¿Cómo se calcula el puntaje?",
                    fontWeight = FontWeight.Bold,
                    color = InfoDarkBlue,
                    fontSize = 13.sp
                )
                Text(
                    text = "Urgencia + Vulnerabilidad + Tiempo de espera + Cercanía",
                    color = InfoDarkBlue.copy(alpha = 0.8f),
                    fontSize = 11.sp,
                    lineHeight = 14.sp
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = InfoDarkBlue
            )
        }
    }
}

@Composable
fun RequestCard(req: HelpRequest) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBg),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(req.severityColor)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                        Surface(
                            color = req.severityBg,
                            shape = CircleShape,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = req.icon,
                                contentDescription = null,
                                tint = req.severityColor,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = req.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextDark
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = TextMuted, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = req.location, color = TextMuted, fontSize = 12.sp)
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.People, contentDescription = null, tint = TextMuted, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = req.peopleInfo, color = TextMuted, fontSize = 12.sp)
                            }
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Surface(
                            color = req.severityBg,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Icon(Icons.Default.Warning, contentDescription = null, tint = req.severityColor, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = req.severityText, color = req.severityColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Puntaje", fontSize = 10.sp, color = TextMuted)
                                Text(text = req.score, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextDark)
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextMuted)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    color = ReasonBg,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Schedule,
                            contentDescription = null,
                            tint = req.severityColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Motivo del puntaje",
                                fontWeight = FontWeight.Bold,
                                color = TextDark,
                                fontSize = 11.sp
                            )
                            Text(
                                text = req.reason,
                                color = TextMuted,
                                fontSize = 11.sp,
                                lineHeight = 14.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// NUEVO MENÚ INFERIOR FLOTANTE PERSONALIZADO
@Composable
fun FloatingEmergencyBottomNav() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp) // Genera el efecto flotante
    ) {
        Surface(
            shape = RoundedCornerShape(50), // Bordes totalmente curvos tipo píldora
            color = BgApp, // Mismo color de fondo de la app según requerimiento
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Item 1: Solicitudes (Seleccionado, color azul oscuro, estilo horizontal)
                Row(
                    modifier = Modifier
                        .background(BottomNavSelectedBg, RoundedCornerShape(50))
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Tune, // Icono parecido a los sliders de filtro
                        contentDescription = "Solicitudes",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Solicitudes",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                // Item 2: Mapa (Inactivo, vertical)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Icon(Icons.Outlined.Map, contentDescription = "Mapa", tint = BottomNavUnselectedColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Mapa", color = BottomNavUnselectedColor, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }

                // Item 3: Zonas (Inactivo, vertical)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Icon(Icons.Outlined.WarningAmber, contentDescription = "Zonas", tint = BottomNavUnselectedColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Zonas", color = BottomNavUnselectedColor, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }

                // Item 4: Perfil (Inactivo, vertical)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Icon(Icons.Outlined.Person, contentDescription = "Perfil", tint = BottomNavUnselectedColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Perfil", color = BottomNavUnselectedColor, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}