package xyz.teamgravity.dictionary.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.teamgravity.dictionary.data.local.entity.WordInfoEntity

@Dao
interface DictionaryDao {

    ///////////////////////////////////////////////////////////////////////////
    // Insert
    ///////////////////////////////////////////////////////////////////////////

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(info: List<WordInfoEntity>)

    ///////////////////////////////////////////////////////////////////////////
    // Delete
    ///////////////////////////////////////////////////////////////////////////

    @Query("DELETE FROM ${DictionaryConst.TABLE_WORD_INFO} WHERE word IN(:words)")
    suspend fun deleteWordInfos(words: List<String>)

    ///////////////////////////////////////////////////////////////////////////
    // Get
    ///////////////////////////////////////////////////////////////////////////

    @Query("SELECT * FROM ${DictionaryConst.TABLE_WORD_INFO} WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfos(word: String): List<WordInfoEntity>
}