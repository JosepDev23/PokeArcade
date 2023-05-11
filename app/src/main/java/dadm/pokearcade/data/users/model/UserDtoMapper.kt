package dadm.pokearcade.data.users.model

import dadm.pokearcade.domain.model.User

fun UserDto.toDomain(): User = User(
    username = username,
    password = password,
    wins = wins,
    games = games
)

fun User.toDto(): UserDto = UserDto(
    username = username,
    password = password,
    wins = wins,
    games = games
)