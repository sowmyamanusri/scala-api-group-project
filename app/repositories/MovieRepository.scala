package repositories

import models.Movie

import java.util.regex.Pattern
import javax.inject.Singleton
import scala.collection.mutable
import scala.util.matching.Regex

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

  movieList += Movie(2,
    "Scala, Fifth Edition",
    "",
    "Scala programming languages",
    "Development type",
    "UD",
    3
  )

  /**
   * @param movietitle The title of the movie to find
   * @return Option[Movie] An option means this method can return a Movie or optionally a "None"
   */
  def getMovieByTitle(title: String): mutable.Set[Movie] = {
    movieList.collect {
      case movie if movie.Title.toLowerCase().contains(title.toLowerCase()) => movie
    }
  }
}
