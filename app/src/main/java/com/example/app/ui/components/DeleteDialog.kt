package com.example.app.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.app.data.dao.models.Plant
import com.example.app.ui.components.Text.MediumText.MediumText20

@Composable
fun DeleteBirdDialog(
    showDialog: Boolean,
    plantToDelete: Plant?,
    onDeleteConfirmed: (Plant) -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog && plantToDelete != null) {
        // Main dialog box container
        Box(
            modifier = Modifier
                .fillMaxSize().padding(top=150.dp, start = 50.dp, end=50.dp),


        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFE0D5ED),
                modifier = Modifier
                    .height(300.dp)
                    .width(330.dp)
            ) {
                Column(
                    modifier = Modifier.padding(start = 15.dp,end=15.dp,top=50.dp, bottom = 20.dp).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    MediumText20(
                        text = "ARE YOU SURE YOU WANT TO DELETE ${plantToDelete.name.uppercase()}?",
                        colorText = Color.White,
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CustomButton(
                            text = "CANCEL",
                            backgroundColor = Color(0xFFFA7298), // Light pink background for button
                            onClick = onDismiss,
                            textColor = Color.Black,

                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        CustomButton(
                            text = "DELETE",
                            backgroundColor = Color(0xFFFA7298),
                            onClick = {
                                onDeleteConfirmed(plantToDelete)
                                onDismiss()
                            },
                            modifier = Modifier.width(250.dp),
                            textColor = Color.Black
                        )
                    }
                }
            }
        }
    }
}

