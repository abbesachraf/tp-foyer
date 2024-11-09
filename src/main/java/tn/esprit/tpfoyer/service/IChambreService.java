package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

public interface IChambreService {

    public List<Chambre> listAll();

    public Chambre retrieveChambre(Long idChambre);
    public Chambre addChambre(Chambre c);
    public void removeChambre(Long chambreId);
    public Chambre modifyChambre(Chambre chambre);

    // Here we will add later methods calling keywords and methods calling JPQL
    public Chambre trouverchambreSelonEtudiant(long Cin);

    public List<Chambre> recupererChambresSelonTyp(TypeChambre tc);

    public void save(Chambre chambre);
}
