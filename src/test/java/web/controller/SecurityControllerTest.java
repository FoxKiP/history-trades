package web.controller;

import base.AbstractControllerTest;
import model.Security;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.SecurityService;
import util.SecurityUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static utils.SecurityTestData.*;


class SecurityControllerTest extends AbstractControllerTest {
    private static final String URL = "/ajax/security/";

    @Autowired
    private SecurityService service;

    @Test
    void getDropdownList() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_TO.contentJson(SecurityUtil.getDropdownList(service.getAll())));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + SECURITY_1.id()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentJson(SECURITY_1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.post(URL + "delete/" + SECURITY_1.id()))
                .andDo(print())
                .andExpect(status().isNoContent());


        assertThrows(IllegalArgumentException.class, () -> service.get(SECURITY_1.id()));
    }

    @Test
    void upload() throws Exception {
        perform(MockMvcRequestBuilders.multipart(URL + "upload").file(getTestMultipartFile()))
                .andDo(print())
                .andExpect(status().isNoContent());

        Security actual = service.get(SECURITY_UPLOADED.id());
        MATCHER.assertMatch(actual, SECURITY_UPLOADED);
    }

    @Test
    void create() throws Exception  {
        Security expected = getNew();
        createOrUpdateAction(expected);
        expected.setId(NEW_ID);

        MATCHER.assertMatch(service.get(NEW_ID), expected);
    }

    @Test
    void update() throws Exception  {
        Security expected = getUpdated(SECURITY_1);

        createOrUpdateAction(expected);

        MATCHER.assertMatch(service.get(SECURITY_1.getId()), expected);
    }

    private void createOrUpdateAction(Security security) throws Exception {
        perform(MockMvcRequestBuilders.post(URL)
                .param("id", security.isNew() ? null : security.getId().toString())
                .param("idOnExchange", security.getIdOnExchange())
                .param("regNumber", security.getRegNumber())
                .param("name", security.getName())
                .param("isin", security.getIsin())
                .param("emitentTitle", security.getEmitentTitle()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}