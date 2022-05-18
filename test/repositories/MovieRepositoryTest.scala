package repositories

import repositories.MovieRepository
import models.Movie

class MovieRepositoryTest extends org.scalatest.FunSuite {
  var movie = Movie(
    "1",
    "Title",
    "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6800_AL_.jpg",
    "Inception",
    "(2010)",
    "R",
    0
  )

  val dataRepository: MovieRepository

  var ratemovie = dataRepository.rateMovie("1", movie).get
}

