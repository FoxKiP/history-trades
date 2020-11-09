package parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.time.LocalDate;

public class ParserUtil {
    private static final Logger log = LoggerFactory.getLogger(ParserUtil.class);

    public static String getAttribute(NamedNodeMap attributes, String attrName) {
        String attribute = "";
        Node node = attributes.getNamedItem(attrName);
        if(node != null) {
            attribute = node.getNodeValue();
        } else {
            log.info("Failed to retrieve attribute - {}, node is null", attrName);
        }
        return attribute;
    }

    public static double parseStringToDouble(String value) {
        return value.isEmpty() ? 0 : Double.parseDouble(value);
    }

    public static LocalDate parseStringToLocalDate(String value) {
        if(value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return LocalDate.parse(value);
    }
}
