import java.util.ArrayList;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	MovieDB movieDB = new MovieDB();
	
	
	public static void main(String[] args) {
		
		//create instance of movie trivia class
		MovieTrivia mt = new MovieTrivia();
		
		//setup movie trivia class
		mt.setUp("moviedata.txt", "movieratings.csv");
	}
	
	/**
	 * Sets up the Movie Trivia class
	 * @param movieData .txt file
	 * @param movieRatings .csv file
	 */
	public void setUp(String movieData, String movieRatings) {
		//load movie database files
		movieDB.setUp(movieData, movieRatings);
		
		//print all actors and movies
		this.printAllActors();
		this.printAllMovies();		
	}
	
	/**
	 * Prints a list of all actors and the movies they acted in.
	 */
	public void printAllActors () {
		System.out.println(movieDB.getActorsInfo());
	}
	
	/**
	 * Prints a list of all movies and their ratings.
	 */
	public void printAllMovies () {
		System.out.println(movieDB.getMoviesInfo());
	}
	
	
	// TODO add additional methods as specified in the instructions PDF
	
    /**
     * Insert given actor and his/her movies into database. 
     * If the given actor is not already present in the given actorsInfo arraylist, 
     * this method should append the given actor along with their given movies to the end of actorsInfo.
     * If the given actor is already present in the given actorsInfo arraylist, it should append
     * the given movies to the end of the actor object's movieCasted list.
     * Note: Actor names should not be case sensitive, but spelling must be correct.
     */
	public void insertActor (String actor, String [] movies, ArrayList <Actor> actorsInfo){
        
        //insert or find the actor 
        String actorName = actor.trim().toLowerCase();   // make sure the name is not case-sensitivie + trim white space       
        Boolean exsit = false;  
        
        for (Actor targetActor:actorsInfo){
            if ( targetActor.getName().toLowerCase().trim().equals(actorName)){ // == is wrong
                for (String movie : movies){
                     targetActor.getMoviesCast().add(movie.trim().toLowerCase()); 
        //question about editing ArrayList by reference: 
        //am I updating the private ArrayList <String> moviesCasted ?? or am I editing what is shown by actor.getMoviesCast()?  
        //actor.setMoviesCast(actorMovies)    
                }
                return; //case 1
            }
        }
    
        Actor targetActor = new Actor(actorName); 
        //ArrayList<String> actorMovies = new ArrayList<String>(); //case2
        //targetActor.getMoviesCast() = actorMovies; //shouldn't we be editing .moviesCasted?
        for (String movie : movies){
                targetActor.getMoviesCast().add(movie.trim().toLowerCase()); 
                }
        actorsInfo.add(targetActor); //insert the actor if not already there
        return ;
      }
    
    /**
     * Insert given ratings for given movie into database.
     *  Ratings is an int array with 2 elements: the critics’ rating at index 0 and the audience rating at index 1.
     */
    public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo){
        String newMovieName = movie.trim().toLowerCase();
        //find or insert the movie in moviesInfo
        //Boolean exsit = false;  
        
        if (ratings.length !=2 || ratings == null) {
            return ;
        }
        if (ratings[0]>100 || ratings[0]<0 || ratings[1]>100 || ratings[1]<0) {
            return;
        }
        
        for (Movie singleMovie : moviesInfo){
            if (singleMovie.getName().equals(newMovieName)){
                
                singleMovie.setCriticRating(ratings[0]);
                singleMovie.setAudienceRating(ratings[1]);   
                //exsit = true;  
                return;
            }
        }
        Movie targetMovie = new Movie(newMovieName, ratings[0], ratings[1]); 
        moviesInfo.add(targetMovie);       
    }
    
    /**
     * Given an actor, return the list of all movies. 
     * If the actor is non-existent in the database, this method should return an empty list.
     */
    public ArrayList <String> selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo){
        String actorName = actor.toLowerCase().trim();   // make sure the name is not case-sensitivie     
        ArrayList<String> actorMovies = new ArrayList<String>();
        for (Actor targetActor:actorsInfo){
            if (targetActor.getName().equals(actorName)){ // == is wrong
                actorMovies = targetActor.getMoviesCast();
            }
        }
//         Actor findActor = actorsInfo.stream().filter(actor -> actor.getName().toLowerCase().trim() == actorName)
//             .findFirst()
//             .orElse(null); // try to find the actor in db, .stream() makes it an array
        //if (findActor == null) return new ArrayList<String>();
        return actorMovies;
    }
    
    /**
     * Given a movie, return the list of all actors in that movie.
     * If the movie is non-existent, this method should return an empty list
     */
    public ArrayList <String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo){
        String movieName = movie.toLowerCase().trim();
        ArrayList<String> actorsList = new ArrayList<String>();
        for (Actor actor:actorsInfo){
            if (actor.getMoviesCast().contains(movieName)){
                actorsList.add(actor.getName());  
            }
        }
        return actorsList;

    }
    
    /**
     * Based on the comparison argument and the targeted rating argument, 
     * return a list of movies that satisfy an inequality or equality.
     */
    public ArrayList <String> selectWhereRatingIs (char comparison, int targetRating, boolean isCritic, ArrayList <Movie> moviesInfo){
        ArrayList<String> movies = new ArrayList<String>(); //default is an empty list
        double rating ;
 
        if (isCritic){
            if (comparison == '>'){
                for (Movie movie: moviesInfo){
                    if (movie.getCriticRating() > targetRating) movies.add(movie.getName());
                }  
            }
            if (comparison == '<'){
                for (Movie movie: moviesInfo){
                    if (movie.getCriticRating() < targetRating) movies.add(movie.getName());
                }     
            }
            if (comparison == '='){
                for (Movie movie: moviesInfo){
                    if (movie.getCriticRating() == targetRating) movies.add(movie.getName());
                }  
                
            } 
        } else {
            if (comparison == '>'){
                for (Movie movie: moviesInfo){
                    if (movie.getAudienceRating() > targetRating) movies.add(movie.getName());
                }  
            }
            if (comparison == '<'){
                for (Movie movie: moviesInfo){
                    if (movie.getAudienceRating() < targetRating) movies.add(movie.getName());
                }     
            }
            if (comparison == '='){
                for (Movie movie: moviesInfo){
                    if (movie.getAudienceRating() == targetRating) movies.add(movie.getName());
                } 
            }
        }
     return movies  ;     
    }
    
    /**
     * Return a list of all actors that the given actor has ever worked with in any movie except the actor herself/himself.
     */
    public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo){
        ArrayList <String> actorMovies = selectWhereActorIs(actor, actorsInfo);
        ArrayList<String> coActors = new ArrayList <String>();
        for (String movie : actorMovies){
            ArrayList <String> actors = selectWhereMovieIs(movie, actorsInfo);
            for (String coactor : actors){
                if(!coactor.equals(actor.toLowerCase().trim())){  //modified after testing
                    coActors.add(coactor);
                }
            }
        }
        return coActors;   
    }
    
    
    /**
     * Return a list of movie names where both actors were cast. 
     * In cases where the two actors have never worked together, this method returns an empty list.
     */
    public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor> actorsInfo){
        ArrayList<String> commonMovies = new ArrayList <String>();
        ArrayList <String> actor1Movies = selectWhereActorIs (actor1, actorsInfo);
        ArrayList <String> actor2Movies = selectWhereActorIs (actor2, actorsInfo);
        if (actor1Movies == null || actor2Movies == null) return commonMovies;
        
        for (String movie : actor1Movies){
            if (actor2Movies.contains(movie)){
                commonMovies.add(movie);
            }
        }
        return commonMovies;
    }
    
    
    /**
     * Returnsa list of movie names that both critics and the audience have rated above 85 (>= 85).
     */
    public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo){
        ArrayList<String> goodMovies = new ArrayList <String>();
        for (Movie movie : moviesInfo){
            if (movie.getCriticRating() >= 85 && movie.getAudienceRating() >= 85){
                goodMovies.add(movie.getName());
            }
        }
        return goodMovies;
    }
    
    /**
     * Given a pair of movies, this method returns a list of actors that acted in both movies. 
     * In cases where the movies have no actors in common, it returns an empty list.
     */
    public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor> actorsInfo){
        ArrayList<String> commonActors = new ArrayList <String>(); //initialize as empty list
        ArrayList <String> movie1Actors= selectWhereMovieIs (movie1, actorsInfo); //already know their datatypes 
        ArrayList <String> movie2Actors= selectWhereMovieIs (movie2, actorsInfo);
        if (movie1Actors==null || movie2Actors==null) return  commonActors;
        for (String actor : movie1Actors){
            if (movie2Actors.contains(actor)) {
                commonActors.add(actor);
            }
        }
        return  commonActors;
    }
    
    
    /**
     * Given the moviesInfo DB, this static method returns the mean value of the critics’ ratings and the audience ratings.
     */
    public static double [] getMean (ArrayList <Movie> moviesInfo){
        double[] mean = new double[2] ;
        double criticRatings = 0 ;
        double audienceRatings = 0;
        int count = moviesInfo.size();
        for (Movie movie : moviesInfo){
            criticRatings += movie.getCriticRating();
            audienceRatings += movie.getAudienceRating();
            // count += 1 , since we used .size(), no need to do this here.
        }
        mean[0]=  criticRatings / count ;
        mean[1]= audienceRatings / count ;    
        return mean;
    }
    
}
