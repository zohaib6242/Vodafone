package com.zohaib.vodafone.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.zohaib.vodafone.domain.model.User
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(user: User?, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(16.dp)
    ) {
        user?.let {
            if (!it.image.isNullOrEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(it.image),
                    contentDescription = "avatar",
                    modifier = Modifier.size(96.dp).clip(CircleShape)
                        .border(BorderStroke(1.dp, Color.Black), CircleShape)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Name: ${it.firstName} ${it.lastName}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(4.dp))
            Text(text = "Email: ${it.email}")
            Spacer(Modifier.height(4.dp))
            Text(text = "Username: ${it.username}")
        }
        Spacer(Modifier.weight(1f))
        Button(onClick = onLogout, modifier = Modifier.fillMaxWidth()) {
            Text("Logout")
        }
    }
}
