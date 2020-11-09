package parser;

import base.AbstractBaseTest;
import model.History;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static utils.HistoryTestData.*;

class HistoryXmlParserTest extends AbstractBaseTest {

    @Autowired
    private HistoryXmlParser parser;

    @Test
    void parse() {
        List<History> actual = parser.parse(getTestMultipartFile());
        MATCHER.assertMatch(actual, List.of(HISTORY_PARSED));
    }
}