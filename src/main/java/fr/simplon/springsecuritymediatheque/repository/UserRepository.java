package fr.simplon.springsecuritymediatheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.springsecuritymediatheque.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
