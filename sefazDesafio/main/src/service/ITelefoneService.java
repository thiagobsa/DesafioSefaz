package service;

import models.Telefone;

import java.util.List;

public interface ITelefoneService {
    void insert(Telefone telefone) throws Exception;
    void update(Telefone telefone) throws Exception;
    List<Telefone> findAllById(Integer usuarioId) throws Exception;
    void remove(Telefone telefone) throws Exception;
}
