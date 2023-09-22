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
        // Mock data
        List<Card> cards = new ArrayList<>();
        when(cardRepository.findAll()).thenReturn(cards);

        // Call the service method
        List<Card> result = cardService.getAllCards();

        // Verify method calls and assertions
        verify(cardRepository, times(1)).findAll();
        assertSame(cards, result);
    }

    @Test
    public void testGetCardById() {
        // Mock data
        long cardId = 1L;
        Card card = new Card();
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));

        // Call the service method
        Optional<Card> result = cardService.getCardById(cardId);

        // Verify method calls and assertions
        verify(cardRepository, times(1)).findById(cardId);
        assertTrue(result.isPresent());
        assertSame(card, result.get());
    }

    @Test
    public void testGetCardByOwner() {
        // Mock data
        long ownerId = 1L;
        Card card = new Card();
        when(cardRepository.findCardByOwnerId(ownerId)).thenReturn(Optional.of(card));

        // Call the service method
        Optional<Card> result = cardService.getCardByOwner(ownerId);

        // Verify method calls and assertions
        verify(cardRepository, times(1)).findCardByOwnerId(ownerId);
        assertTrue(result.isPresent());
        assertSame(card, result.get());
    }

    @Test
    public void testAddCard() {
        // Mock data
        Card cardToAdd = new Card();
        when(cardRepository.save(cardToAdd)).thenReturn(cardToAdd);

        // Call the service method
        Card result = cardService.addCard(cardToAdd);

        // Verify method calls and assertions
        verify(cardRepository, times(1)).save(cardToAdd);
        assertSame(cardToAdd, result);
    }

    @Test
    public void testUpdateCard() {
        // Mock data
        long cardId = 1L;
        Card updatedCard = new Card();
        updatedCard.setId(cardId);
        when(cardRepository.existsById(cardId)).thenReturn(true);
        when(cardRepository.save(updatedCard)).thenReturn(updatedCard);

        // Call the service method
        Card result = cardService.updateCard(updatedCard, cardId);

        // Verify method calls and assertions
        verify(cardRepository, times(1)).existsById(cardId);
        verify(cardRepository, times(1)).save(updatedCard);
        assertSame(updatedCard, result);
    }

    @Test
    public void testUpdateCardNonExistent() {
        // Mock data
        long cardId = 1L;
        Card updatedCard = new Card();
        updatedCard.setId(cardId);
        when(cardRepository.existsById(cardId)).thenReturn(false);

        // Call the service method
        Card result = cardService.updateCard(updatedCard, cardId);

        // Verify method calls and assertions
        verify(cardRepository, times(1)).existsById(cardId);
        verify(cardRepository, never()).save(updatedCard);
        assertNull(result);
    }

    @Test
    public void testDeleteCard() {
        // Mock data
        long cardId = 1L;
        doNothing().when(cardRepository).deleteById(cardId);

        // Call the service method
        cardService.deleteCard(cardId);

        // Verify method calls
        verify(cardRepository, times(1)).deleteById(cardId);
    }
}