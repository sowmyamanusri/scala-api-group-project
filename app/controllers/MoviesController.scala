package controllers

import javax.inject._
import play.api.mvc._
import repositories.BookRepository

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * Movie's home page.
 */
@Singleton
class MovieController @Inject()(val controllerComponents: ControllerComponents, dataRepository: BookRepository) extends BaseController {

}
