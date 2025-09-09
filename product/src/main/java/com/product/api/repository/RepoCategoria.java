package com.product.api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.product.api.entity.Categoria;
import java.util.List;


@Repository
public interface RepoCategoria extends JpaRepository<Categoria, Integer>{

    @Query(value="SELECT * FROM categoria ORDER BY categoria", nativeQuery=true)
    List<Categoria> getCategorias();

}