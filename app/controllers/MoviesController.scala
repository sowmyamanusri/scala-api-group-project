package controllers

import models.Movie
import play.api.libs.json._
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import repositories.MovieRepository

import javax.inject.{Inject, Singleton}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * Movie's home page.
 */
@Singleton
class MoviesController @Inject()(val controllerComponents: ControllerComponents, dataRepository: MovieRepository) extends BaseController {

  def addMovie() : Action[AnyContent] = Action {
    implicit request =>
      try {
        val requestBody = request.body
        val bookJsonObject = requestBody.asJson

        // This type of JSON un-marshalling will only work
        // if ALL fields are POSTed in the request body
        val movieItem: Option[Movie] =
        bookJsonObject.flatMap(
          Json.fromJson[Movie](_).asOpt
        )
        val savedMovie: Option[Movie] = dataRepository.addMovie(movieItem.get)
        if (savedMovie.isEmpty) throw new Exception("Movie already exists.")
        Created(Json.toJson(savedMovie))}
      catch {case ex: Exception => InternalServerError(Json.obj("code" -> INTERNAL_SERVER_ERROR, "message" -> s"Add Movie error : ${ex.getMessage}"))}
  }
}
