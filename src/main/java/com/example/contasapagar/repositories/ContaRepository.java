package com.example.contasapagar.repositories;

import com.example.contasapagar.models.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, UUID> {
}
