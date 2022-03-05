package xyz.teamgravity.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import xyz.teamgravity.dictionary.data.local.entity.WordInfoEntity

data class WordInfoDto(
    @SerializedName("word") val word: String,
    @SerializedName("origin") val origin: String?,
    @SerializedName("phonetic") val phonetic: String?,
    @SerializedName("meanings") val meanings: List<MeaningDto>,
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            word = word,
            origin = origin ?: "",
            phonetic = phonetic ?: "",
            meanings = meanings.map { it.toMeaningModel() },
        )
    }
}