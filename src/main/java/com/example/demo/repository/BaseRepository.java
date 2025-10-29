package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.transaction.Transactional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    @Query("SELECT e FROM #{entityName} e WHERE e.ativo = true")
    List<T> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE #{entityName} e SET e.ativo = false WHERE e.id = :id")
    void softDeleteById(ID id);

}
