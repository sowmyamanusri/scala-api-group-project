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

    def rateMovie(movieId: String): Action[AnyContent] = Action {
    implicit request =>
        val requestBody = request.body
        val movieJsonObject = requestBody.asJson

        // This type of JSON un-marshalling will only work
        // if ALL fields are POSTed in the request body
        val movieItem: Option[Movie] =
        movieJsonObject.flatMap(
          Json.fromJson[Movie](_).asOpt
        )
        val editMovie = dataRepository.rateMovie(movieId, movieItem.get)
        if (editMovie.isEmpty) throw new Exception("Movie is not exists.")
        Created(Json.toJson(editMovie))
      //catch {case ex: Exception => InternalServerError(Json.obj("code" -> INTERNAL_SERVER_ERROR, "message" -> s"Rate movie error : ${ex.getMessage}"))}
  }

  def addMovie() : Action[AnyContent] = Action {
    implicit request =>
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
        Created(Json.toJson(savedMovie))
      //catch {case ex: Exception => InternalServerError(Json.obj("code" -> INTERNAL_SERVER_ERROR, "message" -> s"Add Movie error : ${ex.getMessage}"))}
  }
}

