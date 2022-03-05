package xyz.teamgravity.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import xyz.teamgravity.dictionary.domain.model.MeaningModel

data class MeaningDto(
    @SerializedName("definitions") val definitions: List<DefinitionDto>,
    @SerializedName("partOfSpeech") val partOfSpeech: String
) {
    fun toMeaningModel(): MeaningModel {
        return MeaningModel(
            definitions = definitions.map { it.toDefinitionModel() },
            partOfSpeech = partOfSpeech
        )
    }
}