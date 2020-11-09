package parser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public abstract class XmlParserToList<E> extends XmlParser<List<E>> {

    public XmlParserToList(String path) {
        super(path);
    }

    @Override
    protected List<E> getElements(NodeList nodeList) {
        List<E> elements = new ArrayList<>();
        for(int i = 0; i < nodeList.getLength(); i++) {
            elements.add(getElement(nodeList.item(i)));
        }
        return elements;
    }

    protected abstract E getElement(Node node);
}
