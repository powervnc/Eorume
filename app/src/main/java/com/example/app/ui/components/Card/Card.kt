package com.example.app.ui.components.Card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import com.example.app.ui.components.CustomButton
import com.example.app.ui.components.Text.LargeText
import com.example.app.ui.components.Text.MediumText.MediumText20
import com.example.app.ui.components.Text.MediumText.MediumText24
import com.example.app.ui.theme.Purple40

@Composable
fun Card(
    name: String = "NAME",
    botanicalName: String = "BOTANICAL NAME",
    perfumeType: String = "Perfume type",
    strengthType: String = "Bloom time",
    meaning: String = "meaning...",
    isFavorite: Boolean,
    onDeletePerfume: () -> Unit,
    onEditPerfume: () -> Unit,
    onToggleFavorite : () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth().padding(horizontal = 15.dp),

        //.padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 15.dp),
    )
    {
        LargeText(name, Color.Black)
        Spacer(modifier = Modifier.height(1.dp))
        MediumText24(botanicalName, Purple40)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TYPE label + value
            Column() {
                MediumText20("TYPE", Color.Black)
                MediumText20(perfumeType, Purple40)
            }

            // WHEN label + value
            Column(horizontalAlignment = Alignment.End) {
                MediumText20("WHEN", Color.Black)
                MediumText20(strengthType, Purple40)
            }
        }
        //Spacer(modifier = Modifier.height(10.dp))
//        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
//            SmallText(meaning, Color.White, maxLines = 1)
//        }
        //Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CustomButton(
                text="EDIT",
                backgroundColor = Color.Black,
                onClick = onEditPerfume,
                modifier = Modifier.weight(0.4f),

            )
            Spacer(modifier = Modifier.weight(0.09f))
            CustomButton(text="DELETE", textColor=Color.White, backgroundColor = Color.Black, onClick = onDeletePerfume , modifier = Modifier.weight(0.4f))

        }

    }
}

