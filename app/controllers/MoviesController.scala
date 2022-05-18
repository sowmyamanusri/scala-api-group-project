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

  def rateMovie(movieId: String): Action[AnyContent] = Action {
    implicit request =>
      try {
        val requestBody = request.body
        val movieJsonObject = requestBody.asJson

        // This type of JSON un-marshalling will only work
        // if ALL fields are POSTed in the request body
        val movieItem: Option[Movie] =
        movieJsonObject.flatMap(
          Json.fromJson[Movie](_).asOpt
        )
        val editMovie = dataRepository.rateMovie(movieId, movieItem.get)
        if (editMovie.isEmpty || editMovie == None) throw new Exception("Movie is not exists.")
        Created(Json.toJson(editMovie))}
      catch {case ex: Exception => InternalServerError(Json.obj("code" -> INTERNAL_SERVER_ERROR, "message" -> s"Rate movie error : ${ex.getMessage}"))}
  }
}

