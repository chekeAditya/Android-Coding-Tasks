package com.example.customisablepizzas.di

import android.content.Context
import androidx.room.Room
import com.example.customisablepizzas.remote.APIClient
import com.example.customisablepizzas.remote.local.PizzaDao
import com.example.customisablepizzas.remote.local.PizzaDatabase
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
object PizzaModule {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/chekeAditya/30bb6e2f20558100fe5fcf4c04e91892/raw/f4fbe0fbb1b081834e790e30e15be531f858a527/"

    @Provides
    fun provideAPISERVICE(): APIClient {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(APIClient::class.java)
    }


    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): PizzaDatabase {
        val builder = Room.databaseBuilder(
            context, PizzaDatabase::class.java, "grow_db"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideTaskDAO(db: PizzaDatabase): PizzaDao {
        return db.getResponsePizzaDao()
    }

}