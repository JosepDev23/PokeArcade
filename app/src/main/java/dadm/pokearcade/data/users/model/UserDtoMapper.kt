package dadm.pokearcade.data.users.model

import dadm.pokearcade.domain.model.User

/**
 * Mapping from user DTO to Domain.
 */
fun UserDto.toDomain(): User = User(
    username = username,
    password = password,
    wins = wins,
    games = games
)

/**
 * Mapping from user domain to DTO.
 */
fun User.toDto(): UserDto = UserDto(
    username = username,
    password = password,
    wins = wins,
    games = games
)