package xyz.teamgravity.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import xyz.teamgravity.dictionary.domain.model.DefinitionModel

data class DefinitionDto(
    @SerializedName("antonyms") val antonyms: List<String>,
    @SerializedName("definition") val definition: String,
    @SerializedName("example") val example: String?,
    @SerializedName("synonyms") val synonyms: List<String>
) {
    fun toDefinitionModel(): DefinitionModel {
        return DefinitionModel(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}