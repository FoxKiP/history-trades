package repository;

import model.Security;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CrudSecurityRepository implements SecurityRepository {

    private final JpaSecurityRepository securityRepository;

    public CrudSecurityRepository(JpaSecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    @Transactional
    public Security save(Security security) {
        return securityRepository.save(security);
    }

    @Override
    public boolean delete(int id) {
        return securityRepository.delete(id) != 0;
    }

    @Override
    public Security get(int id) {
        return securityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Security> getAll() {
        return securityRepository.findAll();
    }
}
