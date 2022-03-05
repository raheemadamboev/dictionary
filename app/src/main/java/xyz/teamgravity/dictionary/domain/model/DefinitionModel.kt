package xyz.teamgravity.dictionary.domain.model

data class DefinitionModel(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
)
