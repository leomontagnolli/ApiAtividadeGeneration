package com.generation.apibiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.apibiblioteca.model.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

}
