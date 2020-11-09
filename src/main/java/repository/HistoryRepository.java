package repository;


import model.History;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository {
    History save(History history, Integer securityId);

    boolean delete(int id);

    History get(int id);

    List<History> getAll();

    List<History> getAllBySecurityIdAndTradeDate(int securityId, LocalDate tradeDate);

    List<History> getAllBySecurityId(int securityId);

    List<History> getAllByTradeDate(LocalDate tradeDate);

    History saveUpload(History history);
}
