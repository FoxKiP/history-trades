package service;

import base.AbstractServiceTest;
import model.Security;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import repository.SecurityRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static utils.SecurityTestData.*;

class SecurityServiceTest extends AbstractServiceTest {

    @Autowired
    private SecurityService service;

    @Autowired
    private SecurityRepository repository;

    @Test
    void create() {
        Security expected = getNew();
        Security actual = service.create(expected);
        Integer createdId = actual.getId();
        expected.setId(createdId);
        MATCHER.assertMatch(actual, expected);
    }

    @Test
    void getAll() {
        List<Security> actual = service.getAll();
        MATCHER.assertMatch(actual, getSecurities());
    }

    @Test
    void get() {
        Security actual = service.get(SECURITY_1.getId());
        MATCHER.assertMatch(actual, SECURITY_1);
    }

    @Test
    void update() {
        Security expected = getUpdated(SECURITY_1);
        service.update(expected);
        MATCHER.assertMatch(repository.get(SECURITY_1.getId()), expected);
    }

    @Test
    void delete() {
        service.delete(SECURITY_1.id());
        assertNull(repository.get(SECURITY_1.getId()));
    }

    @Test
    void upload() throws Exception {
        service.upload(getTestMultipartFile());
        Security actual = repository.get(SECURITY_UPLOADED.getId());
        MATCHER.assertMatch(actual, SECURITY_UPLOADED);
    }
}