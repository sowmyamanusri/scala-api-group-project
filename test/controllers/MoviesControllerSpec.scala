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
  val sampleMovie: mutable.Set[Movie] = mutable.Set(Movie("tt0110413",
    "Léon: The Professional",
    "https://imdb-api.com/images/original/MV5BODllNWE0MmEtYjUwZi00ZjY3LThmNmQtZjZlMjI2YTZjYmQ0XkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_Ratio0.6751_AL_.jpg",
    "Léon: The Professional",
    "After her father, step-mother, step-sister and little brother are killed by her father's employers, the 12-year-old daughter of an abject drug dealer manages to take refuge in the apartment of a professional hitman who at her request teaches her the methods of his job so she can take her revenge on the corrupt DEA agent who ruined her life by killing her beloved brother.",
    "R",
    4
  ))



  "MoviesController GET movieByTitle" should {

    "return 200 OK for single movie request" in {

      // Here we utilise Mockito for stubbing the request to getMovieByTitle
      when(mockDataService.getMovieByTitle("pro")) thenReturn sampleMovie

      val controller = new MoviesController(stubControllerComponents(), mockDataService)
      val movie = controller.getMovieByTitle("pro").apply(FakeRequest(GET, "/movies/title/pro"))

      status(movie) mustBe OK
      contentType(movie) mustBe Some("application/json")
    }

    "return all the results that matches movie title request" in {

      sampleMovie += Movie("tt0111413",
        "Thin Ice",
        "https://imdb-api.com/images/original/MV5BMTM2NjQyMTY0M15BMl5BanBnXkFtZTcwMjI4MTIyMQ@@._V1_Ratio0.6751_AL_.jpg",
        "Thin Ice",
        "Steffi, a black photographer, and her journalist friend Greg persuade a magazine editor to commission an article on New York's upcoming Gay Games. Only weeks before the event, however, Steffi is dumped by her ice skating partner and lover, thus jeopardizing the article. But she soon meets Natalie.",
        "",
        3
      )

      // Here we utilise Mockito for stubbing the request to getMovieByTitle
      when(mockDataService.getMovieByTitle("th")) thenReturn sampleMovie

      val controller = new MoviesController(stubControllerComponents(), mockDataService)
      val movies = controller.getMovieByTitle("th").apply(FakeRequest(GET, "/movies/title/th"))

      status(movies) mustBe OK
      contentAsString(movies) mustEqual Json.toJson(sampleMovie).toString()

      contentType(movies) mustBe Some("application/json")
    }

    "return Exception for unavailable movie Id request" in {

      //Here we utilise Mockito for stubbing the request to getMovieByTitle
      when(mockDataService.getMovieByTitle("st"))  thenReturn mutable.Set[Movie]()

      val controller = new MoviesController(stubControllerComponents(), mockDataService)
      val exceptionCaught = intercept[Exception] {
        controller.getMovieByTitle("st").apply(FakeRequest(GET, "/movies/title/st"))
      }
      exceptionCaught.getMessage mustBe "No Movies found"
    }
  }
}
