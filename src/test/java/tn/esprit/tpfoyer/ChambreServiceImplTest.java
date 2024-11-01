package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    private Chambre chambre;

    @BeforeEach
    void setUp() {
        chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101L);
        chambre.setTypeC(TypeChambre.SINGLE); // Assuming TypeChambre is an enum
    }

    @Test
    public void testRetrieveChambre() {
        // Mock the repository method
        Mockito.when(chambreRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(chambre));

        // Call the service method
        Chambre retrievedChambre = chambreService.retrieveChambre(1L);

        // Assert that the Chambre is not null and values are correct
        assertNotNull(retrievedChambre);
        assertEquals(101L, retrievedChambre.getNumeroChambre());
        assertEquals(TypeChambre.SINGLE, retrievedChambre.getTypeC());
    }

    @Test
    public void testRetrieveAllChambres() {
        // Mock the repository method
        List<Chambre> chambreList = Arrays.asList(chambre);
        Mockito.when(chambreRepository.findAll()).thenReturn(chambreList);

        // Call the service method
        List<Chambre> retrievedList = chambreService.retrieveAllChambres();

        // Assert the size and content of the list
        assertNotNull(retrievedList);
        assertEquals(1, retrievedList.size());
        assertEquals(101L, retrievedList.get(0).getNumeroChambre());
    }

    @Test
    public void testAddChambre() {
        // Mock the repository method
        Mockito.when(chambreRepository.save(Mockito.any(Chambre.class))).thenReturn(chambre);

        // Call the service method
        Chambre addedChambre = chambreService.addChambre(chambre);

        // Assert that the Chambre was added successfully
        assertNotNull(addedChambre);
        assertEquals(101L, addedChambre.getNumeroChambre());
    }

    @Test
    public void testModifyChambre() {
        // Mock the repository method
        Mockito.when(chambreRepository.save(Mockito.any(Chambre.class))).thenReturn(chambre);

        // Call the service method
        Chambre modifiedChambre = chambreService.modifyChambre(chambre);

        // Assert that the Chambre was modified successfully
        assertNotNull(modifiedChambre);
        assertEquals(101L, modifiedChambre.getNumeroChambre());
    }

    @Test
    public void testRemoveChambre() {
        // No need to mock the delete method, just call it and ensure no exceptions
        assertDoesNotThrow(() -> chambreService.removeChambre(1L));

        // Optionally, verify that the repository's deleteById method was called
        Mockito.verify(chambreRepository).deleteById(1L);
    }
}
