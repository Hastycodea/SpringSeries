package com.hastycode.thymeproject.repo;

import com.hastycode.thymeproject.model.Mate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateRepo extends JpaRepository<Mate, String> {
}
