package hoang_yen_cuisine.payment;

import java.util.Random;
import java.util.Scanner;

public class PaymentByCard implements IPayProcessor{

	Scanner reader = new Scanner(System.in);
	@Override
	public boolean validateCardInfo() {
		String cardNumber = "";
		while ((cardNumber.length() < 7)) {
			System.out.println("Enter Your Card Number Again (must >7 digits):");
			cardNumber = reader.nextLine();
		}
		Random random = new Random();
		return random.nextBoolean(); // if you spend too many time to try this, please return true
	}

	@Override
	public boolean validateAddress() {
		String address = "";
		int trytime = 0;
		while ((address.length() <= 0)&&(trytime < 3)) {
			System.out.println("Please Enter Your Address:");
			address = reader.nextLine();
			trytime++;
		}
		if (trytime >= 3) {
			return false;
		}
		Random random = new Random();
		return random.nextBoolean();
	}

	@Override
	public boolean paySuccess(boolean card, boolean address) {
		if (card && address) {
			return true;
		}
		else if (card && !address) {
			System.out.println("Wrong Address");	
		}
		else if (!card && address) {
			System.out.println("Wrong Card Info");	
		}
		else {
			System.out.println("Wrong Card Info Or Address");
		}
		return false;
	}

}
