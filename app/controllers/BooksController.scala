package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import scala.collection.mutable.ListBuffer
import models.Book
import play.api.libs.json._

import javax.inject.{Inject, Singleton}

@Singleton
class BooksController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  val bookList = new ListBuffer[Book]()
  bookList += Book(1, "Title", "James", "Some book description", "Horror")

  def getAll: Action[AnyContent] = Action {
    if (bookList.isEmpty) NoContent else Ok(Json.toJson(bookList))
  }
}
