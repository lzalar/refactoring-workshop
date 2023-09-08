package movierental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RentalTest {
	Movie [] movies;
	
	@BeforeEach
	public void setUp() {
		movies = new Movie[] {
				new Movie("CitizenFour", Movie.REGULAR),
				new Movie("Frozen", Movie.CHILDRENS),
				new Movie("Ex Machina", Movie.NEW_RELEASE),
				new Movie("Bridge of Spies", Movie.NEW_RELEASE)
		};
	}
	
	@Test
	public void testMovieAttributes() {
		Movie m = movies[0];
		assertEquals("CitizenFour", m.getTitle());
		assertEquals(Movie.REGULAR, m.getPriceCode());
	}

	@Test
	public void testAttributes() {
		int days = 1;
		for(Movie movie: movies) {
			Rental rental = new Rental(movie, days);
			assertEquals(rental.getDaysRented(), days);
			assertEquals(movie, rental.getMovie());
		}
	}
}
