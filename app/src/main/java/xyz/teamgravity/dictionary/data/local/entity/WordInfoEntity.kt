package xyz.teamgravity.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import xyz.teamgravity.dictionary.data.local.database.DictionaryConst
import xyz.teamgravity.dictionary.domain.model.MeaningModel
import xyz.teamgravity.dictionary.domain.model.WordInfoModel

@Entity(tableName = DictionaryConst.TABLE_WORD_INFO)
data class WordInfoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val word: String = "",
    val origin: String = "",
    val phonetic: String = "",
    val meanings: List<MeaningModel>
) {
    fun toWordInfoModel(): WordInfoModel {
        return WordInfoModel(
            word = word,
            origin = origin,
            phonetic = phonetic,
            meanings = meanings
        )
    }
}
