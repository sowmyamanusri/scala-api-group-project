package repositories

import models.Movie

import javax.inject.Singleton
import scala.collection.mutable

@Singleton
class MovieRepository {

  private val movieList = mutable.Set[Movie]()
  movieList += Movie("tt0110413",
    "Léon: The Professional",
    "https://imdb-api.com/images/original/MV5BODllNWE0MmEtYjUwZi00ZjY3LThmNmQtZjZlMjI2YTZjYmQ0XkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_Ratio0.6751_AL_.jpg",
    "Léon: The Professional",
    "After her father, step-mother, step-sister and little brother are killed by her father's employers, the 12-year-old daughter of an abject drug dealer manages to take refuge in the apartment of a professional hitman who at her request teaches her the methods of his job so she can take her revenge on the corrupt DEA agent who ruined her life by killing her beloved brother.",
    "R",
    "4"
  )
  movieList += Movie(
    "tt1375666",
    "Advanced Search",
    "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6837_AL_.jpg",
    "Inception",
    "(2010)",
    "PG-13",
    "4"
  )

  def getAllMovies: mutable.Set[Movie] = movieList

  /**
   * @param movieId The ID of the movie to find
   * @return Option[Movie] An option means this method can return a Movie or optionally a "None"
   */
  def getMovieById(movieId: String): mutable.Set[Movie] = movieList.collect {
    case movie if movie.id.toLowerCase().contains(movieId.toLowerCase()) => movie
  }
}
