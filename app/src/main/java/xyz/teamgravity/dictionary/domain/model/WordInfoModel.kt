package xyz.teamgravity.dictionary.domain.model

data class WordInfoModel(
    val word: String,
    val origin: String,
    val phonetic: String,
    val meanings: List<MeaningModel>
)
