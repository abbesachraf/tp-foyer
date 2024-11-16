package tn.esprit.tpfoyer.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EtudiantRestControllerTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEtudiant("John");
        etudiant.setPrenomEtudiant("Doe");
        etudiant.setCinEtudiant(12345678L);
        etudiant.setDateNaissance(new Date());
    }

    @Test
    void retrieveEtudiantParCin() {

        // Mock the repository method
        Mockito.when(etudiantRepository.findEtudiantByCinEtudiant(Mockito.anyLong())).thenReturn(etudiant);

        // Call the service method
        Etudiant retrievedEtudiant = etudiantService.recupererEtudiantParCin(12345678L);

        // Assert that the Etudiant is not null and values are correct
        assertNotNull(retrievedEtudiant);
        assertEquals(12345678L, retrievedEtudiant.getCinEtudiant());
    }

    @Test
    void retrieveEtudiant() {

        Mockito.when(etudiantRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(etudiant));

        // Call the service method
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1L);

        // Assert that the Etudiant is not null and values are correct
        assertNotNull(retrievedEtudiant);
        assertEquals("John", retrievedEtudiant.getNomEtudiant());
        assertEquals("Doe", retrievedEtudiant.getPrenomEtudiant());
        assertEquals(12345678L, retrievedEtudiant.getCinEtudiant());
    }

    @Test
    void addEtudiant() {

        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Call the service method
        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        // Assert that the Etudiant was added successfully
        assertNotNull(addedEtudiant);
        assertEquals("John", addedEtudiant.getNomEtudiant());
    }

    @Test
    void removeEtudiant() {

        assertDoesNotThrow(() -> etudiantService.removeEtudiant(1L));

        // Optionally, verify that the repository's deleteById method was called
        Mockito.verify(etudiantRepository).deleteById(1L);
    }

    @Test
    void modifyEtudiant() {

        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Call the service method
        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant);

        // Assert that the Etudiant was modified successfully
        assertNotNull(modifiedEtudiant);
        assertEquals("John", modifiedEtudiant.getNomEtudiant());
    }
}