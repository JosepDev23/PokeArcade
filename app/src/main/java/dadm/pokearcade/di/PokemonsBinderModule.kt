package dadm.pokearcade.di

import dadm.pokearcade.data.pokemons.PokemonsDataSource
import dadm.pokearcade.data.pokemons.PokemonsDataSourceImpl
import dadm.pokearcade.data.pokemons.PokemonsRepository
import dadm.pokearcade.data.pokemons.PokemonsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonsBinderModule {
    @Binds
    abstract fun bindPokemonsRepository(pokemonsRepositoryImpl: PokemonsRepositoryImpl): PokemonsRepository

    @Binds
    abstract fun bindPokemonsDataSource(pokemonsDataSourceImpl: PokemonsDataSourceImpl): PokemonsDataSource
}