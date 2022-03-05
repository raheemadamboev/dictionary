package xyz.teamgravity.dictionary.domain.model

data class MeaningModel(
    val definitions: List<DefinitionModel>,
    val partOfSpeech: String
)
