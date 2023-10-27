package com.example.electric.service;

import com.example.electric.model.Card;
import com.example.electric.respository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;


    @Test
    public void testGetAllCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(1L, "John Doe", "1234567890123456",  "2023-09-22"));
        cards.add(new Card(2L,  "Jane Smith", "2345678901234567",  "2023-09-22"));
        when(cardRepository.findAll()).thenReturn(cards);

        List<Card> result = cardService.getAllCards();

        assertEquals(cards, result);
    }

    @Test
    public void testGetCardById() {
        long id = 1L;
        Card card = new Card(1L, "John Doe", "1234567890123456",  "2023-09-22");
        when(cardRepository.findById(id)).thenReturn(Optional.of(card));

        Optional<Card> result = cardService.getCardById(id);

        assertEquals(Optional.of(card), result);
    }

    @Test
    public void testGetCardByIdNonExistent() {
        long id = 1L;
        when(cardRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Card> result = cardService.getCardById(id);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testGetCardByUserId() {
        long userId = 1L;
        Card card = new Card(1L, "John Doe", "1234567890123456",  "2023-09-22");
        List<Card> listcard = new ArrayList<>();
        listcard.add(card);
        when(cardRepository.findCardByUserId(userId)).thenReturn(listcard);

        List<Card> result = cardService.getCardByUserId(userId);

        assertEquals(listcard, result);
    }

    @Test
    public void testGetCardByUserIdNonExistent() {
        long userId = 1L;
        when(cardRepository.findCardByUserId(userId)).thenReturn(null);

        List<Card> result = cardService.getCardByUserId(userId);

        assertEquals(null, result);

    }

    @Test
    public void testAddCard() {
        Card cardToAdd = new Card(1L, "John Doe", "1234567890123456",  "2023-09-22");
        when(cardRepository.save(cardToAdd)).thenReturn(cardToAdd);

        Card result = cardService.addCard(cardToAdd);

        verify(cardRepository, times(1)).save(cardToAdd);
        assertNotNull(result.getId());
    }

    @Test
    public void testUpdateCard() {
        // Create a sample card for testing
        long cardId = 1L;
        Card existingCard = new Card(1L, "John Doe", "1234567890123456",  "2023-09-22");

        // Mock the repository's findById method
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(existingCard));

        // Call the updateCard method
        existingCard.setName("Updated Name");

        // Invoke the updateCard method
        Card result = cardService.updateCard(existingCard, cardId);

        // Verify that the save method of the repository was called once with the updated card
        verify(cardRepository, times(1)).save(existingCard);

        // Check if the result matches the updated card
        assertSame(existingCard, result);
    }

    @Test
    public void testUpdateCardNonExistent() {
        // Create a sample card for testing
        long nonExistentCardId = 999L;

        // Mock the repository's findById method for a non-existent card
        when(cardRepository.findById(nonExistentCardId)).thenReturn(Optional.empty());

        // Call the updateCard method with a card that doesn't exist
        Card updatedCard = new Card();
        updatedCard.setName("Updated Name");

        // Invoke the updateCard method
        Card result = cardService.updateCard(updatedCard, nonExistentCardId);

        // Verify that the repository's save method is not called
        verify(cardRepository, never()).save(updatedCard);

        // Check if the result is null as the card doesn't exist
        assertNull(result);
    }

    @Test
    public void testDeleteCard() {
        // Define a card ID to be deleted
        long cardIdToDelete = 1L;

        // Mock the repository's findById method to return a card with the specified ID
        Card cardToDelete = new Card(cardIdToDelete, "John Doe", "1234567890123456", "2023-12-12");

        // Call the deleteCard method
        cardService.deleteCard(cardIdToDelete);

        // Verify that the deleteById method of the repository was called once with the specified card ID
        verify(cardRepository, times(1)).deleteById(cardIdToDelete);
    }


    @Test
    public void testDeleteCardNonExistent() {
        long cardId = 9999L;
        when(cardRepository.existsById(cardId)).thenReturn(false);

        cardService.deleteCard(cardId);

        verify(cardRepository, times(1)).existsById(cardId);
        verify(cardRepository, never()).deleteById(cardId);

    }
}