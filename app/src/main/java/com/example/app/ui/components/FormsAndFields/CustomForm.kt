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
import com.example.app.data.dao.models.ScentStrength
import com.example.app.data.dao.models.FormState
import com.example.app.data.dao.models.Perfume
import com.example.app.data.dao.models.FragranceType
import com.example.app.ui.components.CustomButton
import com.example.app.ui.components.Select
import com.example.app.ui.components.Text.MediumText.MediumText20
import com.example.app.ui.validators.PerfumeValidator

//@Preview(showBackground = true)
@Composable
fun CustomForm(existingPerfume: Perfume? = null, buttonText: String, onSubmit: (Perfume) -> Unit) {
    var enabled by remember {
        mutableStateOf(true)
    }
    var perfumeFormState by remember {
        mutableStateOf(
            FormState(
                name = existingPerfume?.name ?: "",
                biologicalName = existingPerfume?.officialName ?: "",
                type = existingPerfume?.type ?: FragranceType.FLORAL,
                scentStrength = existingPerfume?.scentStrength ?: ScentStrength.LIGHT,
                meaning = existingPerfume?.meaning ?: ""
            )
        )
    }

    val validator = PerfumeValidator()
    var validationErrors by remember { mutableStateOf<List<String>>(emptyList()) }
    val typesList: List<String> = FragranceType.entries.map { p -> p.name }
    val bloomTimeList: List<String> = ScentStrength.entries.map { p -> p.name }
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
            value = perfumeFormState.name,
            onValueChange = { newName ->
                perfumeFormState=perfumeFormState.copy(name = newName)
            })
        CustomTextField(
            placeholder = "Type...",
            labelText = "OFFICIAL NAME",
            value = perfumeFormState.biologicalName,
            onValueChange = { newBotanicalName ->
                perfumeFormState=perfumeFormState.copy(biologicalName = newBotanicalName)
            })
        Row {
            Select(
                selectedValue = perfumeFormState.type.name,
                values = typesList,
                selectLabel = "TYPE",
                modifier = Modifier.weight(1f),
                onValueChange = { newPerfumeType ->
                    perfumeFormState=perfumeFormState.copy(type=  FragranceType.valueOf(newPerfumeType))

                }
            )
            Spacer(Modifier.width(40.dp))
            Select(
                selectedValue = perfumeFormState.scentStrength.name,
                values = bloomTimeList,
                selectLabel = "STRENGTH",
                modifier = Modifier.weight(1f),
                onValueChange = { newBloomTime ->
                    perfumeFormState=perfumeFormState.copy(scentStrength =  ScentStrength.valueOf(newBloomTime))
                }
            )

        }
        TextArea(perfumeFormState.meaning, { newMeaning ->perfumeFormState= perfumeFormState.copy(meaning = newMeaning) })

        if (validationErrors.isNotEmpty()) {
            validationErrors.forEach { error ->
                MediumText20(text = error, colorText = Color.Red)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        CustomButton(
            text = buttonText, backgroundColor = Color.Black, onClick = {
                val newPerfume = Perfume(
                    id = existingPerfume?.id ?: 0, name = perfumeFormState.name,
                    officialName = perfumeFormState.biologicalName,
                    meaning = perfumeFormState.meaning,
                    type = perfumeFormState.type,
                    scentStrength = perfumeFormState.scentStrength
                )
                validationErrors = validator.validate(newPerfume)
                if (validationErrors.isEmpty()) {
                    enabled = false
                    onSubmit(newPerfume)

                }
            }, modifier = Modifier
                .height(60.dp)
                .width(160.dp),
            enabled=enabled,
            textColor = Color.White
        )


    }

}