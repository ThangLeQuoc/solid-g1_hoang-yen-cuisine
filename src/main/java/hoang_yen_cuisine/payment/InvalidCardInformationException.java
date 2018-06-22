package hoang_yen_cuisine.payment;

public class InvalidCardInformationException extends PaymentException {
    @Override
    public String getMessage() {
        return "Invalid card info";
    }
}
