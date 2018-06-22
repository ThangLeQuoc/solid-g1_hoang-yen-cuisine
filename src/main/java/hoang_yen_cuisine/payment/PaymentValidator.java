package hoang_yen_cuisine.payment;

public interface PaymentValidator  {
    boolean validate() throws PaymentExceptionAggregation;
}
