package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Telefone;
import repository.TelefoneRepository;
import service.ITelefoneService;

public class TelefoneService implements ITelefoneService {

    private TelefoneRepository telefoneRepository;
    public TelefoneService(){
        telefoneRepository = new TelefoneRepository();
    }

    @Override
    public void insert(Telefone telefone) throws Exception {
        this.telefoneRepository.insert(telefone);
    }

    @Override
    public void update(Telefone telefone) throws Exception {
        this.telefoneRepository.update(telefone);
    }

    @Override
    public List<Telefone> findAllById(Integer usuarioId) throws Exception {
        return this.telefoneRepository.findAllById(usuarioId);
    }

    @Override
    public void remove(Telefone telefone) throws Exception {

    }
}
