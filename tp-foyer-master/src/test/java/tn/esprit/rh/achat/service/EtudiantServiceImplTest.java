package tn.esprit.rh.achat.service;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEtudiant("John Doe");
        etudiant.setCinEtudiant(123456);
    }

    @Test
    void retrieveAllEtudiants() {
        // Given
        List<Etudiant> etudiants = List.of(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // When
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNomEtudiant());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void retrieveEtudiant() {
        // Given
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // When
        Etudiant result = etudiantService.retrieveEtudiant(1L);

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void addEtudiant() {
        // Given
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // When
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void modifyEtudiant() {
        // Given
        etudiant.setNomEtudiant("Jane Doe");
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // When
        Etudiant result = etudiantService.modifyEtudiant(etudiant);

        // Then
        assertNotNull(result);
        assertEquals("Jane Doe", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void removeEtudiant() {
        // Given
        doNothing().when(etudiantRepository).deleteById(1L);

        // When
        etudiantService.removeEtudiant(1L);

        // Then
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    void recupererEtudiantParCin() {
        // Given
        when(etudiantRepository.findEtudiantByCinEtudiant(123456)).thenReturn(etudiant);

        // When
        Etudiant result = etudiantService.recupererEtudiantParCin(123456);

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(123456);
    }
}
