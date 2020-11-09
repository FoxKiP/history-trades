package web.controller;

import model.History;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.HistoryService;
import to.HistoryTo;
import util.HistoryUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/history", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryController {
    private static final Logger log = LoggerFactory.getLogger(HistoryController.class);

    private final HistoryService service;

    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam int securityId,
                               @RequestParam @Nullable Integer id,
                               @RequestParam String boardId,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate,
                               @RequestParam double numTrades,
                               @RequestParam double value,
                               @RequestParam double low,
                               @RequestParam double high,
                               @RequestParam double open,
                               @RequestParam double close,
                               @RequestParam double volume
                               ) {
        History history = new History(null, id, boardId, tradeDate, numTrades, value, low, high, open, close, volume);
        if(history.isNew()) {
            service.create(history, securityId);
        } else {
            service.update(history, securityId);
        }
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upload(@RequestParam("file") MultipartFile file) {
        service.upload(file);
    }

    @GetMapping
    public List<HistoryTo> getAll() {
        return HistoryUtil.getTos(service.getAll());
    }

    @GetMapping("/{id}")
    public History get(@PathVariable("id") Integer id) {
        return service.get(id);
    }

    @GetMapping("/filter")
    public List<HistoryTo> filter(
            @Nullable
            @RequestParam
            Integer securityId,

            @Nullable
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate tradeDate) {

        List<History> histories;
        if (securityId != null && tradeDate != null) {
            histories = service.getAllBySecurityIdAndTradeDate(securityId, tradeDate);
        } else if (securityId != null) {
            histories = service.getAllBySecurityId(securityId);
        } else if (tradeDate != null) {
            histories = service.getAllByTradeDate(tradeDate);
        } else {
            histories = service.getAll();
        }

        return HistoryUtil.getTos(histories);
    }
}
