package com.mallfoundry.payment;

import com.mallfoundry.keygen.PrimaryKeyHolder;
import org.springframework.data.util.CastUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InternalPaymentService implements PaymentService {

    private static final String PAYMENT_ID_VALUE_NAME = "payment.id";

    private final PaymentRepository paymentRepository;

    private final PaymentClientFactory paymentClientFactory;

    public InternalPaymentService(PaymentRepository paymentRepository,
                                  PaymentClientFactory paymentClientFactory) {
        this.paymentRepository = paymentRepository;
        this.paymentClientFactory = paymentClientFactory;
    }

    @Override
    public Payment createPayment() {
        var payment = new InternalPayment(PrimaryKeyHolder.next(PAYMENT_ID_VALUE_NAME));
        payment.pending();
        return payment;
    }

    @Override
    public PaymentSource createPaymentSource(String type) {
        return new InternalPaymentSource(type);
    }

    @Transactional
    @Override
    public Payment savePayment(Payment payment) {
        return this.paymentRepository.save(InternalPayment.of(payment));
    }

    @Override
    public void capturePayment(String id) {

    }

    @Override
    public void voidPayment(String id) {

    }

    @Override
    public Optional<Payment> getPayment(String id) {
        return CastUtils.cast(this.paymentRepository.findById(id));
    }

    @Override
    public Optional<String> getPaymentRedirectUrl(String id) {
        var payment = this.paymentRepository.findById(id).orElseThrow();
        return this.paymentClientFactory.getClient(payment).map(client -> client.createPaymentRedirectUrl(payment));
    }
}
