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

    var sampleUpdatedMovie: Option[Movie] = Option(Movie(
      "2",
      "Title",
      "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6800_AL_.jpg",
      "Inception",
      "(2012)",
      "R",
      4
    ))
    val controller = new MoviesController(stubControllerComponents(), mockDataService)

    "return 200 OK for Update single Movie " in {

      // Here we utilise Mockito for stubbing the request to getBook
      when(mockDataService.rateMovie("2", sampleUpdatedMovie.get)) thenReturn sampleUpdatedMovie

      var updateMovie = controller.rateMovie("2").apply(
        FakeRequest(PUT, "/Movies").withJsonBody(Json.toJson(sampleUpdatedMovie)))

      status(updateMovie) mustBe CREATED
      contentType(updateMovie) mustBe Some("application/json")
      contentAsString(updateMovie) contains  ("\"Rating\":4")//(Json.toJson(sampleUpdatedMovie))
    }

}