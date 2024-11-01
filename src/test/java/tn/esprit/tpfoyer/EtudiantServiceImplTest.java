package tn.esprit.tpfoyer;

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
public class EtudiantServiceImplTest {

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
    public void testRetrieveEtudiant() {
        // Mock the repository method
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
    public void testRetrieveAllEtudiants() {
        // Mock the repository method
        List<Etudiant> etudiantList = Arrays.asList(etudiant);
        Mockito.when(etudiantRepository.findAll()).thenReturn(etudiantList);

        // Call the service method
        List<Etudiant> retrievedList = etudiantService.retrieveAllEtudiants();

        // Assert the size and content of the list
        assertNotNull(retrievedList);
        assertEquals(1, retrievedList.size());
        assertEquals("John", retrievedList.get(0).getNomEtudiant());
    }

    @Test
    public void testAddEtudiant() {
        // Mock the repository method
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Call the service method
        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        // Assert that the Etudiant was added successfully
        assertNotNull(addedEtudiant);
        assertEquals("John", addedEtudiant.getNomEtudiant());
    }

    @Test
    public void testModifyEtudiant() {
        // Mock the repository method
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Call the service method
        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant);

        // Assert that the Etudiant was modified successfully
        assertNotNull(modifiedEtudiant);
        assertEquals("John", modifiedEtudiant.getNomEtudiant());
    }

    @Test
    public void testRemoveEtudiant() {
        // No need to mock the delete method, just call it and ensure no exceptions
        assertDoesNotThrow(() -> etudiantService.removeEtudiant(1L));

        // Optionally, verify that the repository's deleteById method was called
        Mockito.verify(etudiantRepository).deleteById(1L);
    }

    @Test
    public void testRetrieveEtudiantByCin() {
        // Mock the repository method
        Mockito.when(etudiantRepository.findEtudiantByCinEtudiant(Mockito.anyLong())).thenReturn(etudiant);

        // Call the service method
        Etudiant retrievedEtudiant = etudiantService.recupererEtudiantParCin(12345678L);

        // Assert that the Etudiant is not null and values are correct
        assertNotNull(retrievedEtudiant);
        assertEquals(12345678L, retrievedEtudiant.getCinEtudiant());
    }
}
