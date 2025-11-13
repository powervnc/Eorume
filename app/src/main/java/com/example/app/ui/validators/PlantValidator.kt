package com.example.app.ui.validators

import com.example.app.data.dao.models.Plant

class PlantValidator {

    private val maxNameLength = 30
    private val maxBiologicalNameLength = 50
    private val namePattern = "^[A-Za-z]+( [A-Za-z]+)*$".toRegex()

    fun validate(plant: Plant): List<String> {
        val errors = mutableListOf<String>()
        if (plant.name.isBlank()) {
            errors.add("Name is required")
        } else {
            if (!namePattern.matches(plant.name)) {
                errors.add("Name can only contain letters and a single space between words")
            }
            if (plant.name.length > maxNameLength) {
                errors.add("Name cannot be longer than $maxNameLength characters")
            }
        }

        if (plant.biologicalName.isBlank()) errors.add("Biological name is required")
        else {
            if (!namePattern.matches(plant.biologicalName)) {
                errors.add("Botanical name can only contain letters and a single space between words")
            }
            if (plant.biologicalName.length >maxBiologicalNameLength) {
                errors.add("Botanical name cannot be longer than $maxBiologicalNameLength characters")
            }
        }
        if (plant.meaning.isBlank()) errors.add("Meaning is required")
        return errors
    }
}
