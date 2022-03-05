package xyz.teamgravity.dictionary.injection

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.teamgravity.dictionary.core.util.GsonParser
import xyz.teamgravity.dictionary.core.util.JsonParser
import xyz.teamgravity.dictionary.data.local.database.DictionaryConst
import xyz.teamgravity.dictionary.data.local.database.DictionaryConverters
import xyz.teamgravity.dictionary.data.local.database.DictionaryDao
import xyz.teamgravity.dictionary.data.local.database.DictionaryDatabase
import xyz.teamgravity.dictionary.data.remote.api.DictionaryApi
import xyz.teamgravity.dictionary.data.repository.DictionaryRepositoryImpl
import xyz.teamgravity.dictionary.domain.repository.DictionaryRepository
import xyz.teamgravity.dictionary.domain.usecase.GetWordInfos
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideDictionaryApi(factory: GsonConverterFactory): DictionaryApi =
        Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(factory)
            .build()
            .create(DictionaryApi::class.java)

    @Provides
    @Singleton
    fun provideJsonParser(): JsonParser = GsonParser()

    @Provides
    @Singleton
    fun provideDictionaryConverters(parser: JsonParser): DictionaryConverters = DictionaryConverters(parser)

    @Provides
    @Singleton
    fun provideDictionaryDatabase(
        app: Application,
        converters: DictionaryConverters
    ): DictionaryDatabase = Room.databaseBuilder(app, DictionaryDatabase::class.java, DictionaryConst.NAME)
        .addTypeConverter(converters)
        .addMigrations()
        .build()

    @Provides
    @Singleton
    fun provideDictionaryDao(db: DictionaryDatabase): DictionaryDao = db.dictionaryDao()

    @Provides
    @Singleton
    fun provideDictionaryRepository(
        dao: DictionaryDao,
        api: DictionaryApi
    ): DictionaryRepository = DictionaryRepositoryImpl(dao = dao, api = api)

    @Provides
    @Singleton
    fun provideGetWordInfos(repository: DictionaryRepository): GetWordInfos = GetWordInfos(repository)
}