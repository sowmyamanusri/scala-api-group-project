package controllers

import models.Movie
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.api.libs.json._
import repositories.MovieRepository

import javax.inject.{Inject, Singleton}

@Singleton
class MoviesController @Inject()(val controllerComponents: ControllerComponents, dataRepository: MovieRepository) extends BaseController {

  def getMovieById(movieId: String): Action[AnyContent] = Action {
    val searchedMovies = dataRepository.getMovieById(movieId)
    if (searchedMovies.isEmpty || searchedMovies == None) throw new Exception("No Movies found")
    Ok(Json.toJson(searchedMovies))
  }
}
