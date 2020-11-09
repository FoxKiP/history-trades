package parser;

import model.Security;
import org.springframework.stereotype.Component;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import static parser.ParserUtil.getAttribute;

@Component
public class SecurityXmlParser extends XmlParserToList<Security> {
    private static final String PATH = "//data[@id = 'securities']/rows/row";

    public SecurityXmlParser() {
        super(PATH);
    }

    @Override
    protected Security getElement(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        return new Security(
                getAttribute(attributes, "secid"),
                getAttribute(attributes, "regnumber"),
                getAttribute(attributes, "name"),
                getAttribute(attributes, "isin"),
                getAttribute(attributes, "emitent_title"));
    }
}
