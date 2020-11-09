package repository;

import model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface JpaHistoryRepository extends JpaRepository<History, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM History h WHERE h.id=:id")
    int delete(@Param("id") int id);

    List<History> getAllBySecurityIdAndTradeDate(int securityId, LocalDate tradeDate);

    List<History> getAllBySecurityId(int securityId);

    List<History> getAllByTradeDate(LocalDate tradeDate);

    History getByIdAndSecurityId(int id, int securityId);
}
