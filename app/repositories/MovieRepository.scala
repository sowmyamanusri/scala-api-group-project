package repositories

import models.Movie

import scala.collection.mutable

class MovieRepository {

  private val movieList = mutable.Set[Movie]()
  movieList += Movie(
    "1",
    "Title",
    "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6800_AL_.jpg",
    "Léon: The Professional",
     "Adult Movie",
     "A",
     4
  )
  movieList += Movie(
    "2",
    "Title",
    "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6800_AL_.jpg",
    "The Jungle Book",
    "kids Movie",
    "U",
     9,
  )

  def getAllMovies: mutable.Set[Movie] = movieList

  def rateMovie(movieId: String, rateMovie: Movie): Option[Movie] = {
    // If book already exists then return none
    println(s"movieId:$movieId")
    var matchMovie = movieList.find(_.id == movieId)
    println(s"movieId:$movieId is found: ${matchMovie.toString}")
    matchMovie match {
      case Some(movie) => {
        movieList.remove(movie)
        movieList += rateMovie
        return Option(rateMovie)
      }
      case None => throw new Exception("Movie not found")
    }
  }
  @throws(classOf[Exception])
  def deleteMovie(movieId: String): Unit = {
    if(!movieList.exists(_.id == movieId)) {
      throw new Exception("Movie not found")
    }
    movieList--= movieList.filter( _.id == movieId)
  }

}
