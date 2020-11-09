package repository;

import model.History;
import model.Security;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CrudHistoryRepository implements HistoryRepository {

    private final JpaHistoryRepository historyRepository;
    private final JpaSecurityRepository securityRepository;

    public CrudHistoryRepository(JpaHistoryRepository historyRepository, JpaSecurityRepository securityRepository) {
        this.historyRepository = historyRepository;
        this.securityRepository = securityRepository;
    }

    @Override
    @Transactional
    public History save(History history, Integer securityId) {
        if(!history.isNew() && historyRepository.getByIdAndSecurityId(history.id(), securityId) == null) {
            return null;
        } else {
            history.setSecurity(securityRepository.getOne(securityId));
            return historyRepository.save(history);
        }
    }

    @Override
    @Transactional
    public History saveUpload(History history) {
        Security security = securityRepository.findByIdOnExchange(history.getSecId());
        if(security == null) {
            return null;
        } else {
            history.setSecurity(security);
            return historyRepository.save(history);
        }
    }

    @Override
    public List<History> getAll() {
        return historyRepository.findAll();
    }

    @Override
    public boolean delete(int id) {
        return historyRepository.delete(id) != 0;
    }

    @Override
    public History get(int id) {
        return historyRepository.findById(id).orElse(null);
    }

    @Override
    public List<History> getAllBySecurityIdAndTradeDate(int securityId, LocalDate tradeDate) {
        return historyRepository.getAllBySecurityIdAndTradeDate(securityId, tradeDate);
    }

    @Override
    public List<History> getAllBySecurityId(int securityId) {
        return historyRepository.getAllBySecurityId(securityId);
    }

    @Override
    public List<History> getAllByTradeDate(LocalDate tradeDate) {
        return historyRepository.getAllByTradeDate(tradeDate);
    }
}
