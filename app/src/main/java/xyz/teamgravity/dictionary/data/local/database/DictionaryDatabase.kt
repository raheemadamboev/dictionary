package xyz.teamgravity.dictionary.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import xyz.teamgravity.dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = DictionaryConst.VERSION
)
@TypeConverters(DictionaryConverters::class)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao
}