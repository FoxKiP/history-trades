package parser;

import model.History;
import org.springframework.stereotype.Component;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import static parser.ParserUtil.*;

@Component
public class HistoryXmlParser extends XmlParserToList<History>{
    private static final String PATH = "//data[@id = 'history']/rows/row";

    public HistoryXmlParser() {
        super(PATH);
    }

    @Override
    protected History getElement(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        return new History(
                getAttribute(attributes, "SECID"),
                getAttribute(attributes, "BOARDID"),
                parseStringToLocalDate(getAttribute(attributes, "TRADEDATE")),
                parseStringToDouble(getAttribute(attributes, "NUMTRADES")),
                parseStringToDouble(getAttribute(attributes, "VALUE")),
                parseStringToDouble(getAttribute(attributes, "LOW")),
                parseStringToDouble(getAttribute(attributes, "HIGH")),
                parseStringToDouble(getAttribute(attributes, "OPEN")),
                parseStringToDouble(getAttribute(attributes, "CLOSE")),
                parseStringToDouble(getAttribute(attributes, "VOLUME")));
    }
}
