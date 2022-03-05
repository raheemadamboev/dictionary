package xyz.teamgravity.dictionary.data.local.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import xyz.teamgravity.dictionary.core.util.JsonParser
import xyz.teamgravity.dictionary.domain.model.MeaningModel

@ProvidedTypeConverter
class DictionaryConverters(
    private val parser: JsonParser
) {

    @TypeConverter
    fun toListMeaning(data: String): List<MeaningModel> {
        return parser.fromJson<List<MeaningModel>>(
            json = data,
            type = object : TypeToken<List<MeaningModel>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun fromListMeaning(data: List<MeaningModel>): String {
        return parser.toJson(
            obj = data,
            type = object : TypeToken<List<MeaningModel>>() {}.type
        ) ?: "[]"
    }
}