package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FoyerServiceImplTest {

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @Mock
    private FoyerRepository foyerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    public void testRetrieveAllFoyers() {
        // Arrange
        List<Foyer> foyers = new ArrayList<>();
        foyers.add(new Foyer(1L, "Foyer A", 100, null, null)); // Ajout d'un foyer pour le test
        when(foyerRepository.findAll()).thenReturn(foyers); // Simule le comportement du repository

        // Act
        List<Foyer> result = foyerService.retrieveAllFoyers(); // Appel de la méthode à tester

        // Assert
        assertEquals(1, result.size()); // Vérifie que la taille de la liste retournée est correcte
        verify(foyerRepository, times(1)).findAll(); // Vérifie que le repository a été appelé une fois
    }

    @Test
    public void testRetrieveFoyer() {
        // Arrange
        Long foyerId = 1L;
        Foyer foyer = new Foyer(foyerId, "Foyer A", 100, null, null);
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.of(foyer)); // Simule le comportement du repository

        // Act
        Foyer result = foyerService.retrieveFoyer(foyerId); // Appel de la méthode à tester

        // Assert
        assertNotNull(result); // Vérifie que le résultat n'est pas null
        assertEquals("Foyer A", result.getNomFoyer()); // Vérifie que le nom du foyer est correct
        verify(foyerRepository, times(1)).findById(foyerId); // Vérifie que le repository a été appelé une fois
    }

    @Test
    public void testAddFoyer() {
        // Arrange
        Foyer foyer = new Foyer(null, "Foyer A", 100, null, null); // Foyer à ajouter
        when(foyerRepository.save(foyer)).thenReturn(new Foyer(1L, "Foyer A", 100, null, null)); // Simule le comportement du repository

        // Act
        Foyer result = foyerService.addFoyer(foyer); // Appel de la méthode à tester

        // Assert
        assertNotNull(result); // Vérifie que le résultat n'est pas null
        assertEquals("Foyer A", result.getNomFoyer()); // Vérifie que le nom du foyer est correct
        verify(foyerRepository, times(1)).save(foyer); // Vérifie que le repository a été appelé une fois
    }

    @Test
    public void testModifyFoyer() {
        // Arrange
        Foyer foyer = new Foyer(1L, "Foyer A", 100, null, null); // Foyer à modifier
        when(foyerRepository.save(foyer)).thenReturn(foyer); // Simule le comportement du repository

        // Act
        Foyer result = foyerService.modifyFoyer(foyer); // Appel de la méthode à tester

        // Assert
        assertNotNull(result); // Vérifie que le résultat n'est pas null
        assertEquals("Foyer A", result.getNomFoyer()); // Vérifie que le nom du foyer est correct
        verify(foyerRepository, times(1)).save(foyer); // Vérifie que le repository a été appelé une fois
    }

    @Test
    public void testRemoveFoyer() {
        // Arrange
        Long foyerId = 1L; // ID du foyer à supprimer

        // Act
        foyerService.removeFoyer(foyerId); // Appel de la méthode à tester

        // Assert
        verify(foyerRepository, times(1)).deleteById(foyerId); // Vérifie que le repository a été appelé une fois pour supprimer
    }
}
