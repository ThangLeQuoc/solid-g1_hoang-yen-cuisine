package hoang_yen_cuisine.payment;

public interface IPayProcessor {

	public boolean validateCardInfo();
	public boolean validateAddress();
	public boolean paySuccess(boolean card, boolean address);
}
