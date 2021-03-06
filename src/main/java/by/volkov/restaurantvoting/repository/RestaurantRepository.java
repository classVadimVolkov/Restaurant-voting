package by.volkov.restaurantvoting.repository;

import by.volkov.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.user.id=:userId AND r.id=:id")
    int delete(int id, int userId);

    @RestResource(rel = "by-name", path = "by-name")
    Optional<Restaurant> getByName(String name);
}
