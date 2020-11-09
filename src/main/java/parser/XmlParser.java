package parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

public abstract class XmlParser<E> {
    private static final Logger log = LoggerFactory.getLogger(XmlParser.class);
    private final String xPathExpression;
    private final XPath xPath;
    private DocumentBuilder docBuilder;

    public XmlParser(String xPathExpression) {
        this.xPathExpression = xPathExpression;
        this.xPath = XPathFactory.newInstance().newXPath();
        try {
            this.docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.info(e.getMessage());
        }
    }

    public E parse(MultipartFile xmlFile) {
        return getElements(getNodeList(xmlFile));
    }

    protected abstract E getElements(NodeList nodeList);

    private NodeList getNodeList(MultipartFile xmlFile) {
        try (InputStream inputStream = xmlFile.getInputStream()) {
            Document document = docBuilder.parse(inputStream);
            return (NodeList) xPath.evaluate(xPathExpression, document, XPathConstants.NODESET);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
}
