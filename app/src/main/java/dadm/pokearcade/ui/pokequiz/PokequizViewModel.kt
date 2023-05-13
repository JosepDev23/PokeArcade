package dadm.pokearcade.ui.pokequiz

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import dadm.pokearcade.R
import dadm.pokearcade.domain.model.Difficulty
import dadm.pokearcade.domain.model.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokequizViewModel @Inject constructor(application: Application) : ViewModel() {
    val context: Context = application.applicationContext
    private val quizzes: List<Quiz> = listOf(
        Quiz(
            context.getString(R.string.question_1),
            context.getString(R.string.raichu),
            Difficulty.EASY
        ),
        Quiz(
            context.getString(R.string.question_2),
            context.getString(R.string.charmander),
            Difficulty.EASY
        ),
        Quiz(
            context.getString(R.string.question_3),
            context.getString(R.string.squirtle),
            Difficulty.EASY
        ),
        Quiz(
            context.getString(R.string.question_4),
            context.getString(R.string.bulbasaur),
            Difficulty.EASY
        ),
        Quiz(
            context.getString(R.string.question_5),
            context.getString(R.string.kanto),
            Difficulty.NORMAL
        ),
        Quiz(
            context.getString(R.string.question_6),
            context.getString(R.string.dragonite),
            Difficulty.NORMAL
        ),
        Quiz(
            context.getString(R.string.question_7),
            context.getString(R.string.ditto),
            Difficulty.NORMAL
        ),
        Quiz(
            context.getString(R.string.question_8),
            context.getString(R.string.paldea),
            Difficulty.HARD
        ),
        Quiz(
            context.getString(R.string.question_9),
            context.getString(R.string.kartana),
            Difficulty.HARD
        ),
        Quiz(
            context.getString(R.string.question_10),
            context.getString(R.string.kekrom),
            Difficulty.HARD
        ),
    )

    fun getRandomQuiz(): Quiz {
        return quizzes.random()
    }
}