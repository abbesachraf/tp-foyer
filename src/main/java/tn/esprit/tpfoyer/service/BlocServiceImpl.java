package tn.esprit.tpfoyer.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j  // Simple Loggining Façade For Java
public class BlocServiceImpl  implements IBlocService {


    BlocRepository blocRepository;

    public List<Bloc> retrieveAllBlocs() {

        List<Bloc> listB = blocRepository.findAll();
        return listB;
    }

    @Transactional
    public Bloc retrieveBloc(Long blocId) {

        return blocRepository.findById(blocId).get();
    }

    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }


    public void save(Bloc bloc) {
        blocRepository.save(bloc);
    }
}
