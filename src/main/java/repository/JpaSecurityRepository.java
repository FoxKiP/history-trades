package repository;

import model.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface JpaSecurityRepository extends JpaRepository<Security, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Security s WHERE s.id=:id")
    int delete(@Param("id") int id);

    Security findByIdOnExchange(String idOnExchange);
}
