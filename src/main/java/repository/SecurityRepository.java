package repository;

import model.Security;

import java.util.List;

public interface SecurityRepository {
    Security save(Security security);

    boolean delete(int id);

    Security get(int id);

    List<Security> getAll();
}
