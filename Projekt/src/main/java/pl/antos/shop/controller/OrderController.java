package pl.antos.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.antos.shop.model.Order;
import pl.antos.shop.repository.OrderRepository;

import javax.annotation.PostConstruct;
import javax.jms.TextMessage;
import java.time.Instant;
import java.util.UUID;

@Log
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final JmsTemplate jmsTemplate;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order _order = orderRepository.save(order.toBuilder()
                    .id(UUID.randomUUID())
                    .createdAt(Instant.now())
                    .build());
            String jsonOrder = "{}";
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                jsonOrder = mapper.writeValueAsString(_order);
            } catch (Exception e) {
                log.info("Cannot serialize an order object to json.");
            }
            String finalJsonOrder = jsonOrder;
            jmsTemplate.send(session -> {
                TextMessage message = session.createTextMessage();
                message.setText(finalJsonOrder);
                return message;
            });
            return new ResponseEntity<>(_order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostConstruct
    public void sendTestOrder() {
        createOrder(Order.builder()
                .items("Mac Book Pro 2022, Magic Mouse, Magic Keyboard")
                .status("NEW")
                .address("Jan Kowalski Street 1 City 12-123 PL")
                .invoiceAddress("")
                .specialNotes("deliver after 16:00")
                .reference("DPD123456")
                .createdAt(Instant.now())
                .build());
    }
}