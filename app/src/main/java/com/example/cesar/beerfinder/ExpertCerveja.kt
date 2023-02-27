package com.example.cesar.beerfinder

class ExpertCerveja {

    private val brandByBeerType: Map<String, List<String>> = mapOf(
        "Lager" to listOf("Heineken", "Budweiser", "Stella Artois"),
        "Ale" to listOf("Sierra Nevada Pale Ale", "Goose Island IPA", "Anchor Steam Beer"),
        "Lambic" to listOf("Cantillon", "Drie Fonteinen", "Hanssens")
    )

    fun getMarcas(tipo: String): List<String>? {
        return brandByBeerType[tipo]
    }

}