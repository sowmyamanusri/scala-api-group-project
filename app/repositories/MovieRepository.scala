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
    "After her father, step-mother, step-sister and little brother are killed by her father's employers, the 12-year-old daughter of an abject drug dealer manages to take refuge in the apartment of a professional hitman who at her request teaches her the methods of his job so she can take her revenge on the corrupt DEA agent who ruined her life by killing her beloved brother.",
    "R",
    "4"
  )
  movieList += Movie(
    "tt1375666",
    "Inception",
    "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6837_AL_.jpg",
    "Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction, stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable. Cobb's rare ability has made him a coveted player in this treacherous new world of corporate espionage, but it has also made him an international fugitive and cost him everything he has ever loved. Now Cobb is being offered a chance at redemption. One last job could give him his life back but only if he can accomplish the impossible, inception. Instead of the perfect heist, Cobb and his team of specialists have to pull off the reverse: their task is not to steal an idea, but to plant one. If they succeed, it could be the perfect crime. But no amount of careful planning or expertise can prepare the team for the dangerous enemy that seems to predict their every move. An enemy that only Cobb could have seen coming.",
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

  /**
   * @param movietitle The title of the movie to find
   * @return Option[Movie] An option means this method can return a Movie or optionally a "None"
   */
  def getMovieByTitle(title: String): mutable.Set[Movie] = movieList.collect {
      case movie if movie.title.toLowerCase().contains(title.toLowerCase()) => movie
  }

  @throws(classOf[Exception])
  def deleteMovie(movieId: String): Unit = {
    if(!movieList.exists(_.id == movieId)) {
      throw new Exception("Movie not found")
    }
    movieList--= movieList.filter( _.id == movieId)
    
    def rateMovie(movieId: String, rateMovie: Movie): Option[Movie] = {
    // If book already exists then return none
    // revise specification on 2022/5/19
    println(s"movieId:$movieId")
    var matchMovie = MovieList.find(_.id == movieId)
    var updateMovie = Movie(
      matchMovie.get.id,
      matchMovie.get.Image,
      matchMovie.get.Title,
      matchMovie.get.plot,
      matchMovie.get.Certification,
      rateMovie.IMDbRating)
    println(s"movieId:$movieId is found: ${matchMovie.toString}")
    matchMovie match {
      case Some(movie) => {
        MovieList.remove(movie)
        MovieList += updateMovie
        return Option(updateMovie)
      }
      case None => throw new Exception("Movie not found")
    }
  }
}
