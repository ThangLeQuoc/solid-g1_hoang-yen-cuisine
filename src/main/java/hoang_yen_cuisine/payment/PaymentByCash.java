package hoang_yen_cuisine.payment;

import java.util.Random;
import java.util.Scanner;

public class PaymentByCash implements IPayProcessor{

	Scanner reader = new Scanner(System.in);
	@Override
	public boolean validateCardInfo() {
		System.out.println("No need to validate card info");
		return true;
	}

	@Override
	public boolean validateAddress() {
		System.out.println("Please Enter Your Address:");
		String address = "";
		int trytime = 0;
		while ((address.length() <= 0)&&(trytime < 3)) {
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
	public boolean processPayment(boolean card, boolean address) {
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
			System.out.println("Wrong Card Info And Address");
		}
		return false;
	}

}
