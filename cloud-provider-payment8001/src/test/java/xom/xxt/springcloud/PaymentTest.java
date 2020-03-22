package xom.xxt.springcloud;

import com.xxt.springcloud.entities.Payment;
import com.xxt.springcloud.PaymentApp;
import com.xxt.springcloud.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes ={PaymentApp.class} )
public class PaymentTest {

    @Autowired
    PaymentService paymentService;

    @Test
    public void testInsert(){
        Payment payment=new Payment();
        payment.setSerial("999999999");
        int i = paymentService.create(payment);
        System.out.println(i);
        System.out.println(payment);
    }

    @Test
    public void testSelect(){
        Payment paymentById = paymentService.getPaymentById(3L);
        System.out.println(paymentById);
    }

}
