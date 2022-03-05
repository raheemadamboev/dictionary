package xyz.teamgravity.dictionary.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.teamgravity.dictionary.domain.model.WordInfoModel

interface DictionaryRepository {

    fun getWordInfos(word: String): Flow<List<WordInfoModel>>
}