package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import scala.collection.mutable.ListBuffer
import model.Book
import play.api.libs.json._

@Singleton
class BooksController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  val bookList = new ListBuffer[Book]()
  bookList += Book(1, "Title", "James", "Some book description", "Horror")

  implicit val bookListJson = Json.format[Book]

  def getAll: Action[AnyContent] = Action {
    if (bookList.isEmpty) NoContent else Ok(Json.toJson(bookList))
  }
}
