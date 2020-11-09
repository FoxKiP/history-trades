package service;

import base.AbstractServiceTest;
import model.History;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import repository.HistoryRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static utils.HistoryTestData.*;

class HistoryServiceTest extends AbstractServiceTest {

    @Autowired
    private HistoryService service;

    @Autowired
    private HistoryRepository repository;

    @Test
    void getAll() {
        List<History> histories = service.getAll();
        MATCHER.assertMatch(histories, getHistories());
    }

    @Test
    void get() {
        History actual = service.get(HISTORY_1.id());
        MATCHER.assertMatch(actual, HISTORY_1);
    }

    @Test
    void create() {
        History expected = getNew();
        History actual = service.create(expected, SECURITY_ID);
        expected.setId(actual.getId());
        MATCHER.assertMatch(actual, expected);
    }

    @Test
    void update() {
        History expected = getUpdated(HISTORY_2);
        service.update(expected, SECURITY_ID);
        MATCHER.assertMatch(repository.get(HISTORY_2.id()), expected);
    }

    @Test
    void delete() {
        service.delete(HISTORY_1.id());
        assertNull(repository.get(HISTORY_1.id()));
    }

    @Test
    void getAllBySecurityIdAndTradeDate() {
        List<History> actual = service.getAllBySecurityIdAndTradeDate(SECURITY_ID, TRADE_DATE);
        MATCHER.assertMatch(actual, HISTORY_2);
    }

    @Test
    void getAllBySecurityId() {
        List<History> actual = service.getAllBySecurityId(SECURITY_ID);
        MATCHER.assertMatch(actual, HISTORY_2);
    }

    @Test
    void getAllByTradeDate() {
        List<History> actual = service.getAllByTradeDate(TRADE_DATE);
        MATCHER.assertMatch(actual, getHistories());
    }

    @Test
    void upload() {
        service.upload(getTestMultipartFile());
        History actual = repository.get(HISTORY_UPLOADED.id());
        MATCHER.assertMatch(actual, HISTORY_UPLOADED);
    }
}