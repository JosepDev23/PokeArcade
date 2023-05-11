package dadm.pokearcade.di

import dadm.pokearcade.data.users.UsersDataSource
import dadm.pokearcade.data.users.UsersDataSourceImpl
import dadm.pokearcade.data.users.UsersRepository
import dadm.pokearcade.data.users.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersBinderModule {

    @Binds
    abstract fun bindUsersDataSource(usersDataSourceImpl: UsersDataSourceImpl): UsersDataSource

    @Binds
    abstract fun bindUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UsersRepository

}