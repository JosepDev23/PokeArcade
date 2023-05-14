package dadm.pokearcade.data.users.model

import dadm.pokearcade.domain.model.User

/**
 * Mapping from user DTO to Domain.
 */
fun UserDto.toDomain(): User = User(
    id = id,
    username = username,
    password = password,
    wins = wins,
    games = games,
    difficulty = difficulty
)

/**
 * Mapping from user domain to DTO.
 */
fun User.toDto(): UserDto = UserDto(
    id = id,
    username = username,
    password = password,
    wins = wins,
    games = games,
    difficulty = difficulty
)