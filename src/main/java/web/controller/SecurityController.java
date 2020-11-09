package web.controller;

import model.Security;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.SecurityService;
import util.SecurityUtil;
import util.ValidationUtil;

import javax.xml.bind.ValidationException;
import java.util.Map;

@RestController
@RequestMapping(value = "/ajax/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecurityController {


    SecurityService service;

    public SecurityController(SecurityService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, Integer> getDropdownList() {
        return SecurityUtil.getDropdownList(service.getAll());
    }

    @GetMapping("/{id}")
    public Security get(@PathVariable("id") Integer id) {
        return service.get(id);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void upload(@RequestParam("file") MultipartFile file) {
        service.upload(file);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam @Nullable Integer id,
                               @RequestParam String idOnExchange,
                               @RequestParam String regNumber,
                               @RequestParam String name,
                               @RequestParam String isin,
                               @RequestParam String emitentTitle) throws ValidationException {

        Security security = new Security(id, idOnExchange, regNumber, name, isin, emitentTitle);

        if (security.isNew()) {
            ValidationUtil.nameValidation(name);
            service.create(security);
        } else {
            service.update(security);
        }
    }
}
