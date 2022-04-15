package models

import play.api.libs.json.Json

case class Book(id: Long, title: String, author: String, description: String, genre: String)

object Book {
  implicit val format = Json.format[Book]
}
