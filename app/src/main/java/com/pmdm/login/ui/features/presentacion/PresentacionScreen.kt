package com.pmdm.login.ui.features.presentacion

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pmdm.login.model.CategoriaColeccion
import com.pmdm.login.model.Pieza
import com.pmdm.login.ui.theme.LoginTheme

@Composable
fun PresentacionScreen(
    piezaMostrar: Pieza?,
    onCrearCuentaClick: () -> Unit,
    onIniciarSesionClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CabeceraPresentacion()
        TarjetaMetricasColeccion()
        TarjetaPiezaDestacada(piezaMostrar = piezaMostrar)
        AccionesPresentacion(
            onCrearCuentaClick = onCrearCuentaClick,
            onIniciarSesionClick = onIniciarSesionClick
        )
    }
}

@Composable
private fun CabeceraPresentacion(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "REGISTRO DE ACCESO EXCLUSIVO",
            style = MaterialTheme.typography.labelSmall.copy(
                letterSpacing = 1.5.sp,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = "El santuario digital de piezas espectaculares",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Archive, certifique procedencias y compute la valorización en tiempo real de arte contemporáneo, alta relojería, numismática y rarezas.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun TarjetaMetricasColeccion(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IndicadorSeguridad()
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "+14,850 piezas",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Catalogadas por 3,200 coleccionistas y galerías",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun IndicadorSeguridad(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f), RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Security,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun TarjetaPiezaDestacada(
    piezaMostrar: Pieza?,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {
            if (piezaMostrar != null) {
                AsyncImage(
                    model = piezaMostrar.foto,
                    contentDescription = piezaMostrar.titulo,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.85f)
                            ),
                            startY = 100f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "PIEZA DESTACADA",
                    style = MaterialTheme.typography.labelSmall.copy(
                        letterSpacing = 1.2.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Text(
                    text = piezaMostrar?.titulo ?: "Ninguno",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun AccionesPresentacion(
    onCrearCuentaClick: () -> Unit,
    onIniciarSesionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = onCrearCuentaClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.VerifiedUser,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Crear Cuenta",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }

        OutlinedButton(
            onClick = onIniciarSesionClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PresentacionScreenPreview() {
    val piezas = listOf(
        Pieza(
            id = 1,
            titulo = "Patek Philippe Calatrava",
            categoria = CategoriaColeccion.ALTA_HOROLOGIA,
            epoca = "1954",
            autor = "Patek Philippe",
            valorEstimado = 34500.0,
            notas = "Esfera de esmalte blanco cocido, caja de oro amarillo 18k. Calibre de cuerda manual intacto.",
            foto = "imagen1.png"
        ),
        Pieza(
            id = 2,
            titulo = "Óleo 'Nocturno en Siena'",
            categoria = CategoriaColeccion.PINTURA,
            epoca = "S. XVII",
            autor = "Escuela Florentina",
            valorEstimado = 18200.0,
            notas = "Óleo sobre lienzo original, bastidor y barniz de época con craquelado noble y marco dorado.",
            foto = "imagen2.png"
        )
    )
    LoginTheme {
        PresentacionScreen(
            piezaMostrar = piezas[1],
            onCrearCuentaClick = {},
            onIniciarSesionClick = {},
        )
    }
}
