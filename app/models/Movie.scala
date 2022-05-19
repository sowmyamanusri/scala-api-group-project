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
 *  title: String
 *    store Title Name
 *  image: String
 *    store URL of the Image
 *  plot: String
 *    store Description of the Movie
 *  certificate: String
 *    stores the certificate of the Movie to determine whether it is suitable for certain age groups e.g. PG, PG-13, R, etc.
 *  imDbRating: String
 *    store the rating No from 1 to 10 (decimal), for the update function of the data.
 */

case class Movie(id: String, title: String, image: String, plot: String, certificate: String, imDbRating: String )

object Movie {
  implicit val format = Json.format[Movie]
}
