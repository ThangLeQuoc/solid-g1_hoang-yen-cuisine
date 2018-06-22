package hoang_yen_cuisine.payment;

public class PaymentByCard implements IPayProcessor{
    
    private PaymentValidator paymentValidator;
    
    public PaymentByCard() {
        paymentValidator = new CardValidator();
    }

	@Override
	public boolean processPayment() {
	    try {
            return paymentValidator.validate();
        } catch (PaymentExceptionAggregation e) {
            System.out.println(e.getMessage());
            return false;
        }
	}

}
