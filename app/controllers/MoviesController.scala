package controllers

import models.Movie
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.api.libs.json._
import repositories.MovieRepository

import javax.inject.{Inject, Singleton}

@Singleton
class MoviesController @Inject()(val controllerComponents: ControllerComponents, dataRepository: MovieRepository) extends BaseController {

  def getMovieById(movieId: Long): Action[AnyContent] = Action {
    var movieToReturn: Movie = null
    dataRepository.getMovie(movieId) foreach { movie =>
      movieToReturn = movie
    }
    Ok(Json.toJson(movieToReturn))
  }
}
