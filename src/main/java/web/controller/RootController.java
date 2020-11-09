package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.HistoryService;
import util.HistoryUtil;


@Controller
@RequestMapping("/")
public class RootController {

    private final HistoryService service;

    public RootController(HistoryService service) {
        this.service = service;
    }

    @GetMapping
    public String root(Model model) {
        model.addAttribute("to", HistoryUtil.getTos(service.getAll()));
        return "index";
    }
}
