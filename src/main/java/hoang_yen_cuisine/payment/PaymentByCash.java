package hoang_yen_cuisine.payment;

public class PaymentByCash implements IPayProcessor{
    
    private PaymentValidator paymentValidator;
    
    public PaymentByCash() {
        paymentValidator = new AddressValidator();
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
