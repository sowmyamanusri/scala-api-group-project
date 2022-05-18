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

  def addMovie(movie: Movie): Option[Movie] = {
    // If book already exists then return none
    if(MovieList.exists(m => m.id == movie.id)) {
      None
    }
    else {
      // otherwise return the saved after adding it
      MovieList.addOne(movie).collectFirst {
        case m if m.id == movie.id => m
      }
    }
  }
}
