package pl.antos.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.antos.shop.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
