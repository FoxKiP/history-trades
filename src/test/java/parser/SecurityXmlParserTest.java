package parser;

import base.AbstractBaseTest;
import model.Security;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static utils.SecurityTestData.*;
import static utils.SecurityTestData.SECURITY_UPLOADED;

class SecurityXmlParserTest extends AbstractBaseTest {

    @Autowired
    private SecurityXmlParser parser;

    @Test
    void parse() {
        List<Security> actual = parser.parse(getTestMultipartFile());
        MATCHER.assertMatch(actual, List.of(SECURITY_PARSED));
    }
}