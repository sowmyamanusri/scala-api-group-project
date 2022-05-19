package controllers

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import repositories.MovieRepository
import models.Movie
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import play.api.libs.json._
import scala.collection.mutable

class MoviesControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar {

  val mockDataService: MovieRepository = mock[MovieRepository]
  val sampleMovie: mutable.Set[Movie] = mutable.Set(Movie("123SRF",
    "Programming in Scala, Fifth Edition",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsDu0hHNcE_X__PdbkeJl1nIKCe0xUbgDcnw&usqp=CAU",
    "Scala programming language",
    "Development",
    "U",
    4
  ))



  "MoviesController GET movieById" should {

    "return 200 OK for single movie request" in {

      // Here we utilise Mockito for stubbing the request to getMovieById
      when(mockDataService.getMovieById("123S")) thenReturn sampleMovie

      val controller = new MoviesController(stubControllerComponents(), mockDataService)
      val movie = controller.getMovieById("123S").apply(FakeRequest(GET, "/movies/123S"))

      status(movie) mustBe OK
      contentType(movie) mustBe Some("application/json")
    }

    "return Exception for unavailable movie Id request" in {

       //Here we utilise Mockito for stubbing the request to getMovieById
      when(mockDataService.getMovieById("145R"))  thenThrow (new RuntimeException("No Movies found"))

      val controller = new MoviesController(stubControllerComponents(), mockDataService)
      val exceptionCaught = intercept[RuntimeException] {
        controller.getMovieById("145R").apply(FakeRequest(GET, "/movies/145R"))
      }

      exceptionCaught.getMessage mustBe "No Movies found"
    }
  }
}