package hoang_yen_cuisine.payment;

public interface IPayProcessor {
	boolean validateCardInfo();
	boolean validateAddress();
	boolean processPayment(boolean card, boolean address);
}
