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
        when(cardRepository.findCardByUserId(userId)).thenReturn(Optional.of(card));

        Optional<Card> result = cardService.getCardByUserId(userId);

        assertEquals(Optional.of(card), result);
    }

    @Test
    public void testGetCardByUserIdNonExistent() {
        long userId = 1L;
        when(cardRepository.findCardByUserId(userId)).thenReturn(Optional.empty());

        Optional<Card> result = cardService.getCardByUserId(userId);

        assertEquals(Optional.empty(), result);
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
        long cardId = 1L;
        Card updatedCard = new Card(1L, "John Doe", "1234567890123456",  "2023-09-22");
        updatedCard.setId(cardId);
        when(cardRepository.existsById(cardId)).thenReturn(true);
        when(cardRepository.save(updatedCard)).thenReturn(updatedCard);

        Card result = cardService.updateCard(updatedCard, cardId);

        verify(cardRepository, times(1)).existsById(cardId);
        verify(cardRepository, times(1)).save(updatedCard);
        assertSame(updatedCard, result);
    }

    @Test
    public void testUpdateCardNonExistent() {
        long cardId = 1L;
        Card updatedCard = new Card(1L, "John Doe", "1234567890123456",  "2023-09-22");
        updatedCard.setId(cardId);
        when(cardRepository.existsById(cardId)).thenReturn(false);

        Card result = cardService.updateCard(updatedCard, cardId);

        verify(cardRepository, times(1)).existsById(cardId);
        verify(cardRepository, never()).save(updatedCard);
        assertNull(result);
    }

    @Test
    public void testDeleteCard() {
        long cardId = 1L;
        when(cardRepository.existsById(cardId)).thenReturn(true);
        doNothing().when(cardRepository).deleteById(cardId);

        cardService.deleteCard(cardId);

        verify(cardRepository, times(1)).existsById(cardId);
        verify(cardRepository, times(1)).deleteById(cardId);
    }

    @Test
    public void testDeleteCardNonExistent() {
        long cardId = 1L;
        when(cardRepository.existsById(cardId)).thenReturn(false);

        cardService.deleteCard(cardId);

        verify(cardRepository, times(1)).existsById(cardId);
        verify(cardRepository, never()).deleteById(cardId);
    }
}