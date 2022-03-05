package xyz.teamgravity.dictionary.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import xyz.teamgravity.dictionary.core.util.Resource
import xyz.teamgravity.dictionary.data.local.database.DictionaryDao
import xyz.teamgravity.dictionary.data.remote.api.DictionaryApi
import xyz.teamgravity.dictionary.domain.model.WordInfoModel
import xyz.teamgravity.dictionary.domain.repository.DictionaryRepository
import java.io.IOException

class DictionaryRepositoryImpl(
    private val dao: DictionaryDao,
    private val api: DictionaryApi
) : DictionaryRepository {

    override fun getWordInfos(word: String): Flow<Resource<List<WordInfoModel>>> = flow {
        emit(Resource.Loading())

        val localWordInfos = dao.getWordInfos(word).map { it.toWordInfoModel() }
        emit(Resource.Loading(data = localWordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = localWordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection!",
                    data = localWordInfos
                )
            )
        }

        val newLocalWordInfos = dao.getWordInfos(word).map { it.toWordInfoModel() }
        emit(Resource.Success(data = newLocalWordInfos))
    }
}