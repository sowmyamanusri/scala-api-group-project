package repositories

import models.Movie

import scala.collection.mutable

class MovieRepository {

  private val MovieList = mutable.Set[Movie]()
  MovieList += Movie(
    "1",
    "Title",
    "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6800_AL_.jpg",
    "Inception",
    "(2010)",
    "R",
    0
  )

  def getAllMovies: mutable.Set[Movie] = MovieList

  def rateMovie(movieId: String, rateMovie: Movie): Option[Movie] = {
    // If book already exists then return none
    println(s"movieId:$movieId")
    var matchMovie = MovieList.find(_.id == movieId)
    println(s"movieId:$movieId is found: ${matchMovie.toString}")
    matchMovie match {
      case Some(movie) => {
        MovieList.remove(movie)
        MovieList += rateMovie
        return Option(rateMovie)
      }
      case None => throw new Exception("Movie not found")
    }
  }
}
