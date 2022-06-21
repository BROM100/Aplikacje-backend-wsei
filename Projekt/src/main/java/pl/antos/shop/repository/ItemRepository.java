package pl.antos.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.antos.shop.model.Item;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
