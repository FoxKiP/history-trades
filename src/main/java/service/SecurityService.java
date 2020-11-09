package service;

import model.Security;
import parser.SecurityXmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.SecurityRepository;

import java.util.List;

import static util.ValidationUtil.*;

@Service
public class SecurityService {
    private static final Logger log = LoggerFactory.getLogger(SecurityService.class);

    private final SecurityXmlParser xmlParser;
    private final SecurityRepository securityRepository;

    public SecurityService(SecurityRepository securityRepository, SecurityXmlParser historyXmlParser) {
        this.xmlParser = historyXmlParser;
        this.securityRepository = securityRepository;
    }

    public Security create(Security security) {
        checkNew(security);
        return securityRepository.save(security);
    }

    public List<Security> getAll() {
        return securityRepository.getAll();
    }

    public Security get(int id) {
        return checkNotFoundWithId(securityRepository.get(id), id);
    }

    public void update(Security security) {
        checkNotFoundWithId(securityRepository.save(security), security.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(securityRepository.delete(id), id);
    }

    public void upload (MultipartFile file) {
        for(Security security : xmlParser.parse(file)) {
            try {
                create(security);
            } catch (DataIntegrityViolationException e) {
                log.info(e.getMessage());
            }
        }
    }
}
