package com.example.app.ui.components.Card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.ui.theme.Purple
import com.example.app.ui.theme.Purple40
import com.example.app.ui.theme.Purple80


@Composable
fun CardWithBorder(
    name: String,
    botanicalName: String,
    plantType: String,
    bloomTime: String,
    meaning: String,
    isFavorite: Boolean,
    onDeletePlant: () -> Unit,
    onEditPlant: () -> Unit,
    onToggleFavorite: () -> Unit,
) {
    Box(modifier = Modifier.border( width = 2.dp, color = Color.Black).fillMaxWidth().background(
        Purple80),
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(R.drawable.decorative_border),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
           contentScale = ContentScale.FillWidth

        )
        Card(
            name = name,
            botanicalName = botanicalName,
            plantType = plantType,
            bloomTime = bloomTime,
            meaning = meaning,
            isFavorite = isFavorite,
            onDeletePlant = onDeletePlant,
            onEditPlant = onEditPlant,
            onToggleFavorite = onToggleFavorite,
            modifier = Modifier
                .fillMaxWidth(0.88f)
        )
        IconButton(onClick = onToggleFavorite, modifier = Modifier.align(Alignment.TopEnd).padding(end = 30.dp, top = 25.dp).size(100.dp)) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Toggle Favorite",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCardWithBorder() {
    Box(
        modifier = Modifier
            .fillMaxSize()


    ) {
        CardWithBorder(

            name = "Flower",
            botanicalName = "Plany",
            bloomTime = "winter",
            plantType = "perena",
            meaning = "love, passion",
            isFavorite = true,
            onDeletePlant = {},
            onEditPlant = {},
            onToggleFavorite = {},

        )
    }
}

