package movierental;

import java.util.logging.Logger;

/**
 * A rental of a movie by customer.
 * From Fowler's refactoring example.
 * 
 * Rental should have fields and methods for the dates
 * that the movie was rented and returned, from which the
 * rental period is calculated.
 * But for simplicity of the example only a daysRented
 * field is shown.
 */
public class Rental {
	private Movie movie;
	private int daysRented;

	
	/** Initialize a new movie rental object for
	 * a movie with known rental period (daysRented).
	 */
	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}


	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}


	/**
	 * @return the daysRented
	 */
	public int getDaysRented() {
		return daysRented;
	}

	public int getFrequentRenterPoints(){
		return getMovie().getPriceCode() == Movie.NEW_RELEASE ? getDaysRented() : 1;
	}
    public double amountFor() {
        double thisAmount = 0;
        // compute rental change
		switch (getMovie().getPriceCode()) {
			case Movie.REGULAR -> {
				thisAmount += 2;
				if (getDaysRented() > 2) thisAmount += 1.5 * (getDaysRented() - 2);
			}
			case Movie.CHILDRENS -> {
				thisAmount = 1.5;
				if (getDaysRented() > 3) thisAmount += 1.5 * (getDaysRented() - 3);
			}
			case Movie.NEW_RELEASE -> thisAmount = 3 * getDaysRented();
			default ->
					getLogger().warning("Movie " + getMovie() + " has unrecognized priceCode " + getMovie().getPriceCode());
		}
        return thisAmount;
    }

	private static Logger getLogger() {
		return Logger.getLogger(Rental.class.getName());
	}
}
