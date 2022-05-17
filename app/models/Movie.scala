package models

import play.api.libs.json.Json

/**
 * We use a Scala case class to define our model object
 * This gives the added benefit of preventing duplicates because
 * in Scala case classes are said to be equal if all their member variables are equal
 * See more on here under the title "Comparison"
 * https://docs.scala-lang.org/tour/case-classes.html
 *
 * Movie Class data members collection
 *  id: Long
 *    follow to IMDb Movie Data Element ID, can extend the function of import data from IMDb API request
 *  ResultType: String
 *    data type of record, ie Title
 *  Image: String
 *    store URL of the Image
 *  Title: String
 *    store Title Name
 *  Description: String
 *    store Description of the Movie
 *  Certification: String
 *    seekable for children
 *  Rating: Int
 *    store the rating No from 1 to 5, for the update function of the data.
 *
 */

case class Movie(id: Long, ResultType: String, Image: String, Title: String, Description: String, Certification: String, Rating: Int)

object Movie {
  implicit val format = Json.format[Movie]
}
