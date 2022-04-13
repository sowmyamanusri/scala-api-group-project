package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class BooksControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "BooksController GET" should {

    "should return 200 OK for all books request" in {
      val controller = new BooksController(stubControllerComponents())
      val allBooks = controller.getAll().apply(FakeRequest(GET, "/books"))

      status(allBooks) mustBe OK
      contentType(allBooks) mustBe Some("application/json")
    }
  }
}