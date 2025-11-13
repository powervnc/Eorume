package com.example.app.ui.components.FormsAndFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.app.data.dao.models.BloomTime
import com.example.app.data.dao.models.FormState
import com.example.app.data.dao.models.Plant
import com.example.app.data.dao.models.PlantType
import com.example.app.ui.components.CustomButton
import com.example.app.ui.components.Select
import com.example.app.ui.components.Text.MediumText.MediumText20
import com.example.app.ui.validators.PlantValidator

//@Preview(showBackground = true)
@Composable
fun CustomForm(existingPlant: Plant? = null, buttonText: String, onSubmit: (Plant) -> Unit) {
    var enabled by remember {
        mutableStateOf(true)
    }
    var plantFormState by remember {
        mutableStateOf(
            FormState(
                name = existingPlant?.name ?: "",
                biologicalName = existingPlant?.biologicalName ?: "",
                type = existingPlant?.type ?: PlantType.ANNUALS,
                bloomTime = existingPlant?.bloomTime ?: BloomTime.VARIES,
                meaning = existingPlant?.meaning ?: ""
            )
        )
    }

    val validator = PlantValidator()
    var validationErrors by remember { mutableStateOf<List<String>>(emptyList()) }
    val typesList: List<String> = PlantType.entries.map { p -> p.name }
    val bloomTimeList: List<String> = BloomTime.entries.map { p -> p.name }
    Column(
        modifier = Modifier
            .fillMaxSize().
            background(MaterialTheme.colorScheme.background)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomTextField(
            placeholder = "Type...",
            labelText = "NAME",
            value = plantFormState.name,
            onValueChange = { newName ->
                plantFormState=plantFormState.copy(name = newName)
            })
        CustomTextField(
            placeholder = "Type...",
            labelText = "BOTANICAL NAME",
            value = plantFormState.biologicalName,
            onValueChange = { newBotanicalName ->
                plantFormState=plantFormState.copy(biologicalName = newBotanicalName)
            })
        Row {
            Select(
                selectedValue = plantFormState.type.name,
                values = typesList,
                selectLabel = "PLANT TYPE",
                modifier = Modifier.weight(1f),
                onValueChange = { newPlantType ->
                    plantFormState=plantFormState.copy(type=  PlantType.valueOf(newPlantType))

                }
            )
            Spacer(Modifier.width(40.dp))
            Select(
                selectedValue = plantFormState.bloomTime.name,
                values = bloomTimeList,
                selectLabel = "BLOOM TIME",
                modifier = Modifier.weight(1f),
                onValueChange = { newBloomTime ->
                    plantFormState=plantFormState.copy(bloomTime =  BloomTime.valueOf(newBloomTime))
                }
            )

        }
        TextArea(plantFormState.meaning, { newMeaning ->plantFormState= plantFormState.copy(meaning = newMeaning) })

        if (validationErrors.isNotEmpty()) {
            validationErrors.forEach { error ->
                MediumText20(text = error, colorText = Color.Red)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        CustomButton(
            text = buttonText, backgroundColor = Color.Black, onClick = {
                val newPlant = Plant(
                    id = existingPlant?.id ?: 0, name = plantFormState.name,
                    biologicalName = plantFormState.biologicalName,
                    meaning = plantFormState.meaning,
                    type = plantFormState.type,
                    bloomTime = plantFormState.bloomTime
                )
                validationErrors = validator.validate(newPlant)
                if (validationErrors.isEmpty()) {
                    enabled = false
                    onSubmit(newPlant)

                }
            }, modifier = Modifier
                .height(60.dp)
                .width(160.dp),
            enabled=enabled,
            textColor = Color.White
        )


    }

}