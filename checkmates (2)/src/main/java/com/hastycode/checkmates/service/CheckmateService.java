package com.hastycode.checkmates.service;

import com.hastycode.checkmates.model.Checkmate;
import com.hastycode.checkmates.repository.CheckmateRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckmateService {

    private CheckmateRepo repo;

    public CheckmateService(CheckmateRepo repo) {
        this.repo = repo;
    }

    public List<Checkmate> getAllCheckmates() {
        return repo.findAll();
    }

    public Checkmate getCheckmateById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Checkmate addCheckmate(Checkmate checkmate) {
        return repo.save(checkmate);
    }

    public Checkmate updatedCheckmate(long id, Checkmate checkmate) {
        Checkmate updatedCheckmate = repo.findById(id).orElse(null);
        if(updatedCheckmate != null) {
            return repo.save(checkmate);
        } else {
            return null;
        }
    }

    public void deleteCheckmate(long id) {
        repo.deleteById(id);
    }
}
