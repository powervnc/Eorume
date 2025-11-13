package com.example.app.data.dao.models

data class FormState (
    var name: String,
    var biologicalName: String,
    var type: PlantType,
    var bloomTime: BloomTime,
    var meaning: String
)