package service;

import model.History;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import parser.HistoryXmlParser;
import repository.HistoryRepository;

import java.time.LocalDate;
import java.util.List;

import static util.ValidationUtil.checkNew;
import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class HistoryService {
    private static final Logger log = LoggerFactory.getLogger(HistoryService.class);

    private final HistoryXmlParser xmlParser;
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository, HistoryXmlParser historyXmlParser) {
        this.xmlParser = historyXmlParser;
        this.historyRepository = historyRepository;
    }

    public List<History> getAll() {
        return historyRepository.getAll();
    }

    public History get(int id) {
        return checkNotFoundWithId(historyRepository.get(id), id);
    }

    public History create(History history, Integer securityId) {
        checkNew(history);
        return historyRepository.save(history, securityId);
    }

    public void update(History history, int securityId) {
        checkNotFoundWithId(historyRepository.save(history, securityId), history.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(historyRepository.delete(id), id);
    }

    public void upload (MultipartFile file) {
        for(History history : xmlParser.parse(file)) {
            try {
                historyRepository.saveUpload(history);
            } catch (DataIntegrityViolationException e) {
                log.info(e.getMessage());
            }
        }
    }

    public List<History> getAllBySecurityIdAndTradeDate(int securityId, LocalDate tradeDate) {
        return historyRepository.getAllBySecurityIdAndTradeDate(securityId, tradeDate);
    }

    public List<History> getAllBySecurityId(int securityId) {
        return historyRepository.getAllBySecurityId(securityId);
    };

    public List<History> getAllByTradeDate(LocalDate tradeDate){
        return historyRepository.getAllByTradeDate(tradeDate);
    };
}
