package movierental;

import java.util.logging.Logger;

/**
 * A movie available for rent.
 */
public class Movie {
	/** The classes of movies. */
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;
	
	/** movie price code based on classification */
	private int priceCode;
	/** the title, of course */
	private String title;
	
	/** Initialize a new movie. */
	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	/**
	 * @return the priceCode
	 */
	public int getPriceCode() {
		return priceCode;
	}

	/**
	 * @param priceCode the priceCode to set
	 */
	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}
	
	/** Return the movie title */
	public String getTitle() {
		return this.title;
	}
	
	public String toString() {
		return this.title;
	}

	public double getCharge(int daysRented) {
		double thisAmount = 0;
		// compute rental change
		switch (getPriceCode()) {
			case Movie.REGULAR -> {
				thisAmount += 2;
				if (daysRented > 2) thisAmount += 1.5 * (daysRented - 2);
			}
			case Movie.CHILDRENS -> {
				thisAmount = 1.5;
				if (daysRented > 3) thisAmount += 1.5 * (daysRented - 3);
			}
			case Movie.NEW_RELEASE -> thisAmount = 3 * daysRented;
			default ->
					getLogger().warning("Movie " + getTitle() + " has unrecognized priceCode " + getPriceCode());
		}
		return thisAmount;
	}
	private static Logger getLogger() {
		return Logger.getLogger(Movie.class.getName());
	}

	public int getFrequentRenterPoints(int daysRented) {
		return getPriceCode() == Movie.NEW_RELEASE ? daysRented : 1;
	}
}
