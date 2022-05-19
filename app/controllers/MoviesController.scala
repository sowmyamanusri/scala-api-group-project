package controllers

import models.Movie
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.api.libs.json._
import repositories.MovieRepository

import javax.inject.{Inject, Singleton}

@Singleton
class MoviesController @Inject()(val controllerComponents: ControllerComponents, dataRepository: MovieRepository) extends BaseController {

  def getAll: Action[AnyContent] = Action {
    Ok(Json.toJson(dataRepository.getAllMovies))
  }

  @throws(classOf[Exception])
  def getMovieById(movieId: String): Action[AnyContent] = Action {
    val searchedMovies = dataRepository.getMovieById(movieId)
    if (searchedMovies.isEmpty || searchedMovies == None) throw new Exception("No Movies found")
    Ok(Json.toJson(searchedMovies))
  }

  @throws(classOf[Exception])
  def getMovieByTitle(title: String): Action[AnyContent] = Action {
    val searchedMovies = dataRepository.getMovieByTitle(title)
    if (searchedMovies.isEmpty || searchedMovies == None) throw new Exception("No Movies found")
    Ok(Json.toJson(searchedMovies))
  }

  def deleteMovie(movieId: String): Action[AnyContent] = Action {
    dataRepository.deleteMovie(movieId)
    Ok(Json.toJson(s"Successfully deleted movie of Id $movieId"))
  }
}
