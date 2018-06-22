package hoang_yen_cuisine.payment;

import org.apache.commons.lang3.StringUtils;

public class CardValidator extends AddressValidator {

    @Override
    public boolean validate() {
        return super.validate() && validateCardInfo();
    }

    public boolean validateCardInfo() {
        String cardNumber = StringUtils.EMPTY;
        int trytime = 0;
        boolean isValid = false;
        do {
            System.out.println("Enter your card number again (must >7 digits):");
            cardNumber = reader.nextLine();
            isValid = cardNumber.length() >= PaymentConstants.MINIMUM_CARD_DIGITS ? true : false;
            trytime++;
        } while (!isValid && trytime < PaymentConstants.MAXIMUM_ATTEMPTS);
        if (!isValid)
            throw new InvalidCardInformationException();
        return isValid;
    }
}
