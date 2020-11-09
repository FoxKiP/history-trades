package web.controller;

import base.AbstractControllerTest;
import model.History;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.HistoryService;
import util.HistoryUtil;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static utils.HistoryTestData.*;


class HistoryControllerTest extends AbstractControllerTest {
    private static final String URL = "/ajax/history/";

    @Autowired
    private HistoryService service;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + HISTORY_1.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.contentJson(HISTORY_1);
    }

    @Test
    void getAll() throws Exception{
        perform(MockMvcRequestBuilders.get(URL))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER_TO.contentJson(HistoryUtil.getTos(getHistoriesWithSecurities()));
    }

    @Test
    void delete() throws Exception{
        perform(MockMvcRequestBuilders.post(URL + "delete/" + HISTORY_1.id()))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThrows(IllegalArgumentException.class, () -> service.get(HISTORY_1.id()));
    }

    @Test
    void upload() throws Exception {
        perform(MockMvcRequestBuilders.multipart(URL + "upload").file(getTestMultipartFile()))
                .andDo(print())
                .andExpect(status().isNoContent());

        History actual = service.get(HISTORY_UPLOADED.id());
        MATCHER.assertMatch(actual, HISTORY_UPLOADED);
    }

    @Test
    void create() throws Exception {
        History expected = getNew();
        createOrUpdateAction(expected, SECURITY_ID);
        expected.setId(NEW_ID);

        MATCHER.assertMatch(service.get(NEW_ID), expected);
    }

    @Test
    void update() throws Exception {
        History expected = getUpdated(HISTORY_2);
        createOrUpdateAction(expected, SECURITY_ID);

        MATCHER.assertMatch(service.get(HISTORY_2.getId()), expected);
    }

    private ResultActions createOrUpdateAction(History history, int securityId) throws Exception {
        return perform(MockMvcRequestBuilders.post(URL)
                .param("securityId", String.valueOf(securityId))
                .param("id", history.isNew() ? null : history.getId().toString())
                .param("boardId", history.getBoardId())
                .param("tradeDate", history.getTradeDate().toString())
                .param("numTrades", doubleToString(history.getNumTrades()))
                .param("value", doubleToString(history.getValue()))
                .param("low", doubleToString(history.getLow()))
                .param("high", doubleToString(history.getHigh()))
                .param("open", doubleToString(history.getOpen()))
                .param("close", doubleToString(history.getClose()))
                .param("volume", doubleToString(history.getVolume())))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private String doubleToString(double value) {
        return String.valueOf(value);
    }

    @Test
    void filterWithNullArgs() throws Exception {
        filter(null, null)
                .andExpect(MATCHER_TO.contentJson(HistoryUtil.getTos(getHistoriesWithSecurities())));
    }

    @Test
    void filterWithSecurityId() throws Exception {
        filter(SECURITY_ID, null)
                .andExpect(MATCHER_TO.contentJson(HistoryUtil.getTos(List.of(getHistoryWithSecurity()))));
    }

    @Test
    void filterWithTradeDate() throws Exception {
        filter(null, HISTORY_2.getTradeDate())
                .andExpect(MATCHER_TO.contentJson(HistoryUtil.getTos(getHistoriesWithSecurities())));
    }

    @Test
    void filterWithSecurityIdAndTradeDate() throws Exception {
        filter(SECURITY_ID, HISTORY_2.getTradeDate())
                .andExpect(MATCHER_TO.contentJson(HistoryUtil.getTos(List.of(getHistoryWithSecurity()))));
    }

    private ResultActions filter(Integer securityId, LocalDate tradeDate) throws Exception {
        return perform(MockMvcRequestBuilders.get(URL + "filter")
                .param("securityId", securityId == null ? null : securityId.toString())
                .param("tradeDate", tradeDate == null ? null : tradeDate.toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}