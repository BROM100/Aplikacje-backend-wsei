package pl.antos.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.antos.shop.model.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
