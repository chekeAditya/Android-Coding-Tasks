package com.example.unit_5assignment.di

import android.content.Context
import androidx.room.Room
import com.example.unit_5assignment.remote.APIClient
import com.example.unit_5assignment.remote.localDatabase.AppDao
import com.example.unit_5assignment.remote.localDatabase.ApplicationRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    private const val BASE_URL = "https://api.tvmaze.com/"

    @Provides
    fun provideAPIService(): APIClient {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(APIClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): ApplicationRoomDatabase {
        val builder = Room.databaseBuilder(
            context, ApplicationRoomDatabase::class.java, "movies_db"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideDao(db: ApplicationRoomDatabase): AppDao {
        return db.getResponseFromDao()
    }

}