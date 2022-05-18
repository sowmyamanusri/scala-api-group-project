package controllers

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import repositories.MovieRepository
import models.Movie
import org.mockito.ArgumentMatchers.{any, anyString}
import org.mockito.Mockito.when
import play.api.libs.json._

import scala.collection.mutable

class MoviesControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar  {
  val mockDataService :MovieRepository = mock[MovieRepository]
  var sampleMovie : Option[Movie] = Option(Movie( "3",
    "Title",
    "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6800_AL_.jpg",
    "Avatar",
    "Thriller Movie",
    "U/A",
    9,
  )
  )
  "throw an error when deleting a book that doesn't exist" in {
    when(mockDataService.deleteMovie(anyString())) thenThrow new Exception("Movie not found")
    val controller = new MoviesController(stubControllerComponents(), mockDataService)
    val exceptionCaught = intercept[Exception] {
      controller.deleteMovie("10").apply(FakeRequest(DELETE, "/movies/10"))
    }

    exceptionCaught.getMessage mustBe "Movie not found"
  }

}
