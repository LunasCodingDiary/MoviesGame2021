import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import file.MovieDB;


class MovieTriviaTest {
	
	//instance of movie trivia object to test
	MovieTrivia mt;
	//instance of movieDB object
	MovieDB movieDB = new MovieDB();
	
	@BeforeEach
	void setUp() throws Exception {
		//initialize movie trivia object
		mt = new MovieTrivia ();
		
		//set up movie trivia object
		mt.setUp("moviedata.txt", "movieratings.csv");
		
		//set up movieDB object
		movieDB.setUp("moviedata.txt", "movieratings.csv");
	}

	@Test
	void testSetUp() { 
		assertEquals(6, movieDB.getActorsInfo().size());
		assertEquals(7, movieDB.getMoviesInfo().size());
		
		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName());
		assertEquals(3, movieDB.getActorsInfo().get(0).getMoviesCast().size());
		assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0));
		
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating());
		assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating());
	}
	
	@Test
	void testInsertActor () {
		mt.insertActor("test1", new String [] {"testmovie1", "testmovie2"}, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size());	
		assertEquals("test1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName());
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size());
		assertEquals("testmovie1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0));
		
		// TODO add additional test case scenarios
        mt.insertActor("meryl streep", new String [] {"the bridge"}, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size());	
		assertEquals("the bridge", movieDB.getActorsInfo().get(0).getMoviesCast().get(3));
		
        mt.insertActor("  RobiN Williams", new String [] {"DeaD poet society", "the chorus"}, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size());	
		assertEquals("dead poet society", movieDB.getActorsInfo().get(4).getMoviesCast().get(1));
		
        mt.insertActor("Brad PITT", new String [] {"   Inglourious Basterds ","ONCE Upon A Time in Hollywood     "}, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size());	
		assertEquals("brad pitt", movieDB.getActorsInfo().get(5).getName());
		assertEquals(4, movieDB.getActorsInfo().get(5).getMoviesCast().size());
		assertEquals("inglourious basterds", movieDB.getActorsInfo().get(5).getMoviesCast().get(2));
		
		
	}
	
	@Test
	void testInsertRating () {
		mt.insertRating("testmovie", new int [] {79, 80}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());	
		assertEquals("testmovie", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName());
		assertEquals(79, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
		assertEquals(80, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
		
// 		TODO add additional test case scenarios
        mt.insertRating(" Arrival", new int [] {80, 95}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());	
		assertEquals(80, movieDB.getMoviesInfo().get(1).getCriticRating());
		assertEquals(95, movieDB.getMoviesInfo().get(1).getAudienceRating());
		
        mt.insertRating(" jawS ", new int[] {90, 90}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());	
		assertEquals(90, movieDB.getMoviesInfo().get(2).getCriticRating());
		assertEquals(90, movieDB.getMoviesInfo().get(2).getAudienceRating());
		
        mt.insertRating("doubt", new int [] {}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());	
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating());
		assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating());
		
        mt.insertRating(" doubt", new int [] {101,80}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());	
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating());
		assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating());
		
	}
	
	@Test
	void testSelectWhereActorIs () {
		assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size());
		assertEquals("doubt", mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).get(0));
		
		// TODO add additional test case scenarios
        assertEquals(3, mt.selectWhereActorIs("Meryl stREep", movieDB.getActorsInfo()).size());
		assertEquals("the post", mt.selectWhereActorIs("Meryl stREep", movieDB.getActorsInfo()).get(2));
		
        assertEquals(4, mt.selectWhereActorIs("Amy Adams", movieDB.getActorsInfo()).size());
		assertEquals("leap year", mt.selectWhereActorIs("Amy Adams", movieDB.getActorsInfo()).get(1));
		
        assertEquals(0, mt.selectWhereActorIs("brandon krakowsky", movieDB.getActorsInfo()).size());
        
		assertEquals(0, mt.selectWhereActorIs("luna", movieDB.getActorsInfo()).size());
		
	}
	
	@Test
	void testSelectWhereMovieIs () {
		assertEquals(2, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).size());
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("meryl streep"));
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("amy adams"));
		
		// TODO add additional test case scenarios
        assertEquals(2, mt.selectWhereMovieIs("  Doubt", movieDB.getActorsInfo()).size());
		assertEquals(true, mt.selectWhereMovieIs("  Doubt", movieDB.getActorsInfo()).contains("meryl streep"));
		assertEquals(true, mt.selectWhereMovieIs("  Doubt", movieDB.getActorsInfo()).contains("amy adams"));
		
        assertEquals(2, mt.selectWhereMovieIs("The Post", movieDB.getActorsInfo()).size());
		assertEquals(true, mt.selectWhereMovieIs("The Post", movieDB.getActorsInfo()).contains("meryl streep"));
		assertEquals(true, mt.selectWhereMovieIs("The Post", movieDB.getActorsInfo()).contains("tom hanks"));
		
        assertEquals(0, mt.selectWhereMovieIs("titanic", movieDB.getActorsInfo()).size());
		
	}
	
	@Test
	void testSelectWhereRatingIs () {
		assertEquals(6, mt.selectWhereRatingIs('>', 0, true, movieDB.getMoviesInfo()).size());
		assertEquals(0, mt.selectWhereRatingIs('=', 65, false, movieDB.getMoviesInfo()).size());
		assertEquals(2, mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).size());
		
		// TODO add additional test case scenarios
        assertEquals(1, mt.selectWhereRatingIs('=', 29, true, movieDB.getMoviesInfo()).size());
		assertEquals(5, mt.selectWhereRatingIs('>', 70, false, movieDB.getMoviesInfo()).size());
		assertEquals(4, mt.selectWhereRatingIs('<', 90, true, movieDB.getMoviesInfo()).size());
        assertEquals(0, mt.selectWhereRatingIs('?', 29, true, movieDB.getMoviesInfo()).size());
		
	}
	
	@Test
	void testGetCoActors () {
		assertEquals(2, mt.getCoActors("meryl streep", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("tom hanks"));
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("amy adams"));
		
		// TODO add additional test case scenarios
        assertEquals(2, mt.getCoActors("  Meryl streeP", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCoActors("  Meryl streeP", movieDB.getActorsInfo()).contains("tom hanks"));
		assertTrue(mt.getCoActors("  Meryl streeP", movieDB.getActorsInfo()).contains("amy adams"));

        assertEquals(1, mt.getCoActors("Amy Adams", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCoActors("Amy Adams", movieDB.getActorsInfo()).contains("meryl streep"));

        assertEquals(0, mt.getCoActors("robin williams", movieDB.getActorsInfo()).size());
        assertEquals(0, mt.getCoActors("kate winslate", movieDB.getActorsInfo()).size());

	}
	
	@Test
	void testGetCommonMovie () {
		assertEquals(1, mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"));
		
		// TODO add additional test case scenarios
        
        assertEquals(1, mt.getCommonMovie("   MeryL streep", "tom Hanks  ", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("   MeryL streep", "tom Hanks  ", movieDB.getActorsInfo()).contains("the post"));
		
        assertEquals(1, mt.getCommonMovie("meryl streep", "AMY ADAms", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("meryl streep", "AMY ADAms", movieDB.getActorsInfo()).contains("doubt"));
		
        assertEquals(1, mt.getCommonMovie("robin williams", "robin williams", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("robin williams", "robin williams", movieDB.getActorsInfo()).contains("popeye"));
        
        assertEquals(0, mt.getCommonMovie("robin williams", "tom hanks", movieDB.getActorsInfo()).size());
        assertEquals(0, mt.getCommonMovie("leonardo", "tom hanks", movieDB.getActorsInfo()).size());

	}
	
	@Test
	void testGoodMovies () {
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size());
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"));
		
		// TODO add additional test case scenarios
        assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("rocky ii"));
        assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("et"));
        assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("Popeye"));
        assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("arrival"));
        assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("seven"));
	}
	
	@Test
	void testGetCommonActors () {
		assertEquals(1, mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).contains("meryl streep"));
		
		// TODO add additional test case scenarios
        assertEquals(1, mt.getCommonActors("The Post", "  catch Me if You Can", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonActors("The Post", "catch Me if You Can", movieDB.getActorsInfo()).contains("tom hanks"));

        assertEquals(0, mt.getCommonActors("doubt", "popeye", movieDB.getActorsInfo()).size());
        
        assertEquals(0, mt.getCommonActors("titanic", "popeye", movieDB.getActorsInfo()).size());
        
        assertEquals(2, mt.getCommonActors("the post", "the post", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonActors("the post", "the post", movieDB.getActorsInfo()).contains("meryl streep"));
        assertTrue(mt.getCommonActors("the post", "the post", movieDB.getActorsInfo()).contains("tom hanks"));

	}
	
	@Test
	void testGetMean () {
		//fail();
		// TODO add ALL test case scenarios!
        assertEquals(2, mt.getMean(movieDB.getMoviesInfo()).length);
        assertEquals(67.85714285714286, mt.getMean(movieDB.getMoviesInfo())[0]);
        assertEquals(65.71428571428571, mt.getMean(movieDB.getMoviesInfo())[1]);
	}
}
