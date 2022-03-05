package xyz.teamgravity.dictionary.data.local.database

import androidx.room.Database
import androidx.room.TypeConverters
import xyz.teamgravity.dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(DictionaryConverters::class)
abstract class DictionaryDatabase {

    abstract fun dictionaryDao(): DictionaryDao
}