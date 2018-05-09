package pe.tuna.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.tuna.models.ColaboradorBean;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorBean, Integer> {
}
