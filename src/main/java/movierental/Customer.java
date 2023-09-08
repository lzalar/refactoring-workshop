package movierental;

import java.util.ArrayList;
import java.util.List;

/**
 * A customer who rents movies.
 * The customer object holds information about the
 * movies rented for the current billing period,
 * and can print a statement of his rentals.
 */
public class Customer {
	/** Customer's name. */
	private String name;
	/** Customer's rentals for current billing period. */
	private List<Rental> rentals;
	
	/** Initialize a new customer. */
	public Customer(String name) {
		this.name = name;
		this.rentals = new ArrayList<Rental>();
	}
	
	public void addRental(Rental rental) {
		if (! rentals.contains(rental)) rentals.add(rental);
	}
	
	public String getName() {
		return name;
	}
	
	/** Print all the rentals in current period, 
	 * along with total charges and reward points.
	 * @return the statement as a String
	 */
	public String statement() {
		StringBuilder stmt = new StringBuilder("Rental Report for "+getName()).append("\n\n");
		stmt.append(String.format("%-40.40s %4s %-8s\n", "Movie Title", "Days", "Price"));
		
		for(Rental rental: rentals) {
			stmt.append(String.format("%-40.40s %3d %8.2f\n", rental.getMovie().getTitle(), rental.getDaysRented(), rental.amountFor()));
		}
		stmt.append( String.format("%-44.44s %8.2f\n", "Total Charges", getTotalCharge()));
		stmt.append( String.format("%-44.44s %5d\n","Frequent Renter Points earned", getTotalFrequentRenterPoints()) );
		
		return stmt.toString();
	}

	public double getTotalCharge(){
		return this.rentals.stream().mapToDouble(Rental::amountFor).sum();
	}

	public int getTotalFrequentRenterPoints(){
		return this.rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
	}

	public String htmlStatement() {
		StringBuilder result = new StringBuilder("<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n");
		for(Rental rental: rentals){
			result.append(rental.getMovie().getTitle()).append(": ").append(rental.amountFor()).append("<BR>\n");
		}
		result.append("<P>You owe <EM>").append(getTotalCharge()).append("</EM><P>\n");
		result.append("<P>On this rental you earned <EM>").append(getTotalFrequentRenterPoints()).append("</EM> frequent renter points</P>");

		return result.toString();
	}

	}

