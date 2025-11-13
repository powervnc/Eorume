package com.example.app.ui.validators

import com.example.app.data.dao.models.Perfume

class PerfumeValidator {

    private val maxNameLength = 30
    private val maxOfficialNameLength = 50
    private val namePattern = "^[A-Za-z]+( [A-Za-z]+)*$".toRegex()

    fun validate(perfume: Perfume): List<String> {
        val errors = mutableListOf<String>()
        if (perfume.name.isBlank()) {
            errors.add("Name is required")
        } else {
            if (!namePattern.matches(perfume.name)) {
                errors.add("Name can only contain letters and a single space between words")
            }
            if (perfume.name.length > maxNameLength) {
                errors.add("Name cannot be longer than $maxNameLength characters")
            }
        }

        if (perfume.officialName.isBlank()) errors.add("Official name is required")
        else {
            if (!namePattern.matches(perfume.officialName)) {
                errors.add("Official name can only contain letters and a single space between words")
            }
            if (perfume.officialName.length >maxOfficialNameLength) {
                errors.add("Official name cannot be longer than $maxOfficialNameLength characters")
            }
        }
        if (perfume.meaning.isBlank()) errors.add("Meaning is required")
        return errors
    }
}
