package com.example.electric.respository;

import com.example.electric.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    /**
     * Retrieve Cards by User ID
     *
     * This method retrieves a list of cards associated with a specific user identified by their unique ID.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Card objects related to the specified user.
     */
    public List<Card> findCardByUserId(long userId);

}
