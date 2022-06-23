package pl.antos.shop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.jms.core.JmsTemplate;
import pl.antos.shop.model.Order;
import pl.antos.shop.repository.OrderRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderControllerTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private JmsTemplate jmsTemplate = mock(JmsTemplate.class);
    private OrderController orderController = new OrderController(orderRepository, jmsTemplate);

    @Test
    void shouldCreateOrderAndSendOrderToQueue() {
        // given
        Order order = Order.builder().build();

        // when
        orderController.createOrder(order);

        // then
        verify(orderRepository).save(any());
        verify(jmsTemplate).send(any());
    }

}