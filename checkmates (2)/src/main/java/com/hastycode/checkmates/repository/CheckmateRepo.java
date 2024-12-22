package com.hastycode.checkmates.repository;

import com.hastycode.checkmates.model.Checkmate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckmateRepo extends JpaRepository<Checkmate, Long> {
}
