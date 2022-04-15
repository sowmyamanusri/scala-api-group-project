package controllers

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import repositories.BookRepository
import models.Book
import org.mockito.Mockito.when

import scala.collection.mutable.ListBuffer

class BooksControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar {

  val mockDataService: BookRepository = mock[BookRepository]
  var sampleBook: Option[Book] = Option(Book(1,
    "Fantastic Mr. Fox",
    "Roald Dahl",
    "Brilliant",
    "Childs fiction"
  ))

  "BooksController GET allBooks" should {

    "return 200 OK for all books request" in {

      when(mockDataService.getAllBooks).thenReturn(new ListBuffer[Book]())

      val controller = new BooksController(stubControllerComponents(), mockDataService)
      val allBooks = controller.getAll().apply(FakeRequest(GET, "/books"))

      status(allBooks) mustBe OK
      contentType(allBooks) mustBe Some("application/json")
    }

    "return empty JSON array of books for all books request" in {

      when(mockDataService.getAllBooks).thenReturn(new ListBuffer[Book]())

      val controller = new BooksController(stubControllerComponents(), mockDataService)
      val allBooks = controller.getAll().apply(FakeRequest(GET, "/books"))

      status(allBooks) mustBe OK
      contentType(allBooks) mustBe Some("application/json")
      contentAsString(allBooks) mustEqual "[]"
    }
  }

  "BooksController GET bookById" should {

    "should return 200 OK for single book request" in {
      when(mockDataService.getBook(1)) thenReturn sampleBook
      val controller = new BooksController(stubControllerComponents(), mockDataService)
      val book = controller.getBook(1).apply(FakeRequest(GET, "/books/1"))

      status(book) mustBe OK
      contentType(book) mustBe Some("application/json")
    }
  }
}