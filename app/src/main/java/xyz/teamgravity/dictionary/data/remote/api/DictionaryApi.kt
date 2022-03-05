package xyz.teamgravity.dictionary.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import xyz.teamgravity.dictionary.data.remote.dto.WordInfoDto

interface DictionaryApi {

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): List<WordInfoDto>
}