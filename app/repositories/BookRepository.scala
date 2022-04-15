package repositories

import models.Book

import javax.inject.Singleton
import scala.collection.mutable.ListBuffer

@Singleton
class BookRepository {

  private val bookList = new ListBuffer[Book]()
  bookList += Book(1, "Title", "James", "Some book description", "Horror")

  def getAllBooks : ListBuffer[Book] = bookList

  /**
   * @param bookId The ID of the book to find
   * @return Option[Book] An option means this method can return a Book or optionally a "None"
   * https://www.scala-lang.org/api/2.13.8/scala/Option.html
   */
  def getBook(bookId: Long) : Option[Book] = bookList.collectFirst {
    case book if book.id == bookId => book
  }

}
