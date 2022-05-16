package controllers

import models.Movie
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.api.libs.json._
import repositories.MovieRepository

import javax.inject.{Inject, Singleton}

@Singleton
class MoviesController @Inject()(val controllerComponents: ControllerComponents, dataRepository: BookRepository) extends BaseController {
    
    def getAll: Action[AnyContent] = Action {
    Ok(Json.toJson(dataRepository.getAllMovies))
  }
}