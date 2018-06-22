package hoang_yen_cuisine.payment;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class AddressValidator implements PaymentValidator {

    protected Scanner reader = new Scanner(System.in);

    @Override
    public boolean validate() {
        String address = StringUtils.EMPTY;
        int trytime = 0;
        boolean isValid = false;
        do {
            System.out.println("Please enter your address:");
            address = reader.nextLine();
            isValid = address.length() > 0 ? true : false;
            trytime++;
        } while (!isValid && trytime < PaymentConstants.MAXIMUM_ATTEMPTS);

        if (!isValid)
            throw new InvalidAddressException();
        return isValid;
    }
}
