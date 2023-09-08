package movierental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

	/** A customer for use in tests. */
	Customer c;
	Movie [] movies;
	
	@BeforeEach
	public void setUp() {
		c = new Customer("Movie Mogul");
		movies = new Movie[] {
				new Movie("CitizenFour", Movie.REGULAR),
				new Movie("Frozen", Movie.CHILDRENS),
				new Movie("Ex Machina", Movie.NEW_RELEASE),
				new Movie("Bridge of Spies", Movie.NEW_RELEASE),
				new Movie("Particle Fever", Movie.REGULAR)
		};
	}
	
	@Test
	public void givenNoMovies_generateStatement_correctEmptyStatement() {
		String stmt = c.statement();
		System.out.println(stmt);
		String[] tokens = stmt.split("\n");
		String[] lines = ("""
				Rental Report for Movie Mogul

				Movie Title                              Days Price  \s
				Total Charges                                    0.00
				Frequent Renter Points earned                    0""").split("\n");

		assertEquals(tokens.length,lines.length);
		for (int i = 0; i < lines.length; i++) {
			assertEquals(tokens[i],lines[i]);
		}
	}

	@Test
	public void givenManyMovies_generateStatement_correctFullStatement() {
		int days = 11;
		for(Movie m: movies) {
			c.addRental(new Rental(m, days));
		}
		String stmt = c.statement();
		System.out.println(stmt);
		String[] tokens = stmt.split("\n");
		String[] lines = ("""
				Rental Report for Movie Mogul

				Movie Title                              Days Price  \s
				CitizenFour                               11    15.50
				Frozen                                    11    13.50
				Ex Machina                                11    33.00
				Bridge of Spies                           11    33.00
				Particle Fever                            11    15.50
				Total Charges                                  110.50
				Frequent Renter Points earned                   25""").split("\n");

		assertEquals(tokens.length,lines.length);
		for (int i = 0; i < lines.length; i++) {
			assertEquals(tokens[i],lines[i]);
		}
	}
	
	

}
