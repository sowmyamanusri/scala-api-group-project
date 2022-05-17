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

  def getAllMovies: mutable.Set[Movie] = movieList

  /**
   * @param movieId The ID of the movie to find
   * @return Option[Movie] An option means this method can return a Movie or optionally a "None"
   *         https://www.scala-lang.org/api/2.13.8/scala/Option.html
   */
  def getMovie(movieId: Long): Option[Movie] = movieList.collectFirst {
    case movie if movie.id == movieId => movie
  }

//  def addBook(book: Book): Option[Book] = {
//    // If book already exists then return none
//    if(bookList.exists(b => b.id == book.id)) {
//      None
//    }
//    else {
//      // otherwise return the saved after adding it
//      bookList.addOne(book).collectFirst {
//        case b if b.id == book.id => b
//      }
//    }
//  }

}
