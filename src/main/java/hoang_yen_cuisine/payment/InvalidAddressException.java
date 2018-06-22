package hoang_yen_cuisine.payment;

public class InvalidAddressException extends PaymentException {
    @Override
    public String getMessage() {
        return "Invalid address";
    }
}
