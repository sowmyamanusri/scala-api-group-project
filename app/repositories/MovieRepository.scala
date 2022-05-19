package repositories

import models.Movie

import javax.inject.Singleton
import scala.collection.mutable

@Singleton
class MovieRepository {

// Trial val - data comes from IMDb-API
// URL: https://imdb-api.com/API/AdvancedSearch/[API-KEY]/?title=inception
 private val movieList = mutable.Set[Movie]()
  movieList += Movie(
    "tt1375666",
    "Advanced Search",
    "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6837_AL_.jpg",
    "Inception",
    "(2010)",
    "8.8",
    "PG-13"
  )

  def getAllMovies: mutable.Set[Movie] = movieList

}