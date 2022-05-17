package repositories

import models.Movie

import javax.inject.Singleton
import scala.collection.mutable

@Singleton
class MovieRepository {

  private val movieList = mutable.Set[Movie]()
  movieList += Movie(1,
    "Programming in Scala, Fifth Edition",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsDu0hHNcE_X__PdbkeJl1nIKCe0xUbgDcnw&usqp=CAU",
    "Scala programming language",
    "Development",
    "U",
    4
  )

  /**
   * @param movieId The ID of the movie to find
   * @return Option[Movie] An option means this method can return a Movie or optionally a "None"
   */
  def getMovieById(movieId: Long): Option[Movie] = movieList.collectFirst {
    case movie if movie.id == movieId => movie
  }
}
