package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Bloc;

import java.util.List;

public interface IBlocService {

    public List<Bloc> retrieveAllBlocs();
    public Bloc retrieveBloc(Long blocId);

    public void removeBloc(Long blocId);

    public void save(Bloc bloc);

    }
