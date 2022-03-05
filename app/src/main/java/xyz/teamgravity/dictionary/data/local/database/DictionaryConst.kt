package xyz.teamgravity.dictionary.data.local.database

object DictionaryConst {

    /**
     * Database name
     */
    const val NAME = "dictionary_database"

    /**
     * Database version
     */
    const val VERSION = 1

    /**
     * Table word info
     * Entity -> [xyz.teamgravity.dictionary.data.local.entity.WordInfoEntity]
     * DTO -> [xyz.teamgravity.dictionary.data.remote.dto.WordInfoDto]
     * Model -> [xyz.teamgravity.dictionary.domain.model.WordInfoModel]
     */
    const val TABLE_WORD_INFO = "table_word_info"
}