package controllers

import models.Book
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import play.api.libs.json._
import repositories.BookRepository

import javax.inject.{Inject, Singleton}

@Singleton
class BooksController @Inject()(val controllerComponents: ControllerComponents, dataRepository: BookRepository) extends BaseController {

  def getAll: Action[AnyContent] = Action {
    Ok(Json.toJson(dataRepository.getAllBooks))
  }

  def getBook(bookId: Long): Action[AnyContent] = Action {
    var bookToReturn: Book = null
    dataRepository.getBook(bookId) foreach { book =>
      bookToReturn = book
    }
    Ok(Json.toJson(bookToReturn))
  }

  def addBook() : Action[AnyContent] = Action {
    implicit request => {
      val requestBody = request.body
      val bookJsonObject = requestBody.asJson

      // This type of JSON un-marshalling will only work
      // if ALL fields are POSTed in the request body
      val bookItem: Option[Book] =
        bookJsonObject.flatMap(
          Json.fromJson[Book](_).asOpt
        )

      val savedBook: Option[Book] = dataRepository.addBook(bookItem.get)
      Created(Json.toJson(savedBook))
    }
  }
}
