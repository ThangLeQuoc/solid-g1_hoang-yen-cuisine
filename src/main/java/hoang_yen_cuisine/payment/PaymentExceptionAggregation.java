package hoang_yen_cuisine.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentExceptionAggregation extends RuntimeException {
    
    List<PaymentException> paymentExceptions;
    
    public PaymentExceptionAggregation() {
        paymentExceptions = new ArrayList<>();
    }
    
    public void addPaymentExceptions(PaymentException paymentException) {
        paymentExceptions.add(paymentException);
    }
    
    @Override
    public String getMessage() {
        List<String> messages = paymentExceptions.stream()
                .map(PaymentException::getMessage)
                .collect(Collectors.toList());
        return String.join("\n", messages);
    }
    
}
