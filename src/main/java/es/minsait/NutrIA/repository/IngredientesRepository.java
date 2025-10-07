package es.minsait.NutrIA.repository;

import es.minsait.NutrIA.model.Ingredientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientesRepository extends JpaRepository<Ingredientes, Long> {
    Optional<Ingredientes> findByNomeContainingIgnoreCase(String nome); //pesquisar por nome
}
