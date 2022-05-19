package repositories

import models.Movie

import java.util.regex.Pattern
import javax.inject.Singleton
import scala.collection.mutable
import scala.util.matching.Regex

@Singleton
class MovieRepository {

  private val movieList = mutable.Set[Movie]()
  movieList += Movie("tt0110413",
    "Léon: The Professional",
    "https://imdb-api.com/images/original/MV5BODllNWE0MmEtYjUwZi00ZjY3LThmNmQtZjZlMjI2YTZjYmQ0XkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_Ratio0.6751_AL_.jpg",
    "Léon: The Professional",
    "After her father, step-mother, step-sister and little brother are killed by her father's employers, the 12-year-old daughter of an abject drug dealer manages to take refuge in the apartment of a professional hitman who at her request teaches her the methods of his job so she can take her revenge on the corrupt DEA agent who ruined her life by killing her beloved brother.",
    "R",
    4
  )

  movieList += Movie("tt0111413",
    "Thin Ice",
    "https://imdb-api.com/images/original/MV5BMTM2NjQyMTY0M15BMl5BanBnXkFtZTcwMjI4MTIyMQ@@._V1_Ratio0.6751_AL_.jpg",
    "Thin Ice",
    "Steffi, a black photographer, and her journalist friend Greg persuade a magazine editor to commission an article on New York's upcoming Gay Games. Only weeks before the event, however, Steffi is dumped by her ice skating partner and lover, thus jeopardizing the article. But she soon meets Natalie.",
    "",
    3
  )

  /**
   * @param movietitle The title of the movie to find
   * @return Option[Movie] An option means this method can return a Movie or optionally a "None"
   */
  def getMovieByTitle(title: String): mutable.Set[Movie] = {
    movieList.collect {
      case movie if movie.Title.toLowerCase().contains(title.toLowerCase()) => movie
    }
  }
}
