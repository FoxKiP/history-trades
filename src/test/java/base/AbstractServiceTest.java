package base;

import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:db/populateDB.sql")
public abstract class AbstractServiceTest extends AbstractBaseTest{

}
