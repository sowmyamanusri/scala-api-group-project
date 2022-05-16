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
 *  id: String
 *    follow to IMDb Movie Data Element ID, can extend the function of import data from IMDb API request
 *  resultType: String
 *    data type of record, i.e. Title
 *  image: String
 *    store URL of the Image
 *  title: String
 *    store Title Name
 *  description: String
 *    store Description of the Movie
 *  imDbRating: String
 *    store the rating No from 1 to 10 (decimal), for the update function of the data.
 *  certificate: String
 *    stores the certificate of the Movie to determine whether it is suitable for certain age groups e.g. PG, PG-13, R, etc.
 */

case class Movie(id: String, resultType: String, image: String, title: String, description: String, imDbRating: String, certificate: String)

object Movie {
  implicit val format = Json.format[Movie]
}
