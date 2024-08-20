package com.hastycode.thymeproject.service;

import com.hastycode.thymeproject.model.Mate;
import com.hastycode.thymeproject.repo.MateRepo;
import org.springframework.stereotype.Service;

@Service
public class MateService {
    private MateRepo repo;

    public MateService(MateRepo repo) {
        this.repo = repo;
    }

    public void registerMate(Mate mate) {
        repo.save(mate);
    }
}
