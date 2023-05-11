package dadm.pokearcade.di

import android.content.Context
import androidx.room.Room
import dadm.pokearcade.data.users.UsersContract
import dadm.pokearcade.data.users.UsersDao
import dadm.pokearcade.data.users.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsersProviderModule {

    @Provides
    @Singleton
    fun provideUsersDatabase(@ApplicationContext context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context,
            UsersDatabase::class.java,
            UsersContract.DATABASE
        ).build()
    }

    @Provides
    fun provideUsersDao(usersDatabase: UsersDatabase): UsersDao =
        usersDatabase.usersDao()
}