package xyz.teamgravity.dictionary.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import xyz.teamgravity.dictionary.core.util.Resource
import xyz.teamgravity.dictionary.domain.model.WordInfoModel
import xyz.teamgravity.dictionary.domain.repository.DictionaryRepository

class GetWordInfos(
    private val repository: DictionaryRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfoModel>>> {
        if (word.isBlank()) return flow { }
        return repository.getWordInfos(word)
    }
}