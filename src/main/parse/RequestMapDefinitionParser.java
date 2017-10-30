package main.parse;

import main.definitation.RequestMapDefinition;
import main.parse.exception.DuplicateDefinitionException;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RequestMapDefinitionParser {

    private static final Logger LOGGER = Logger.getLogger(RequestMapDefinitionParser.class);

    private String fileName;

    private Map<String, RequestMapDefinition> requestMap = new HashMap<String, RequestMapDefinition>();

    public RequestMapDefinitionParser(String xmlFileName) {
        this.fileName = xmlFileName;
    }

    protected abstract Document getDocument(InputStream inputStream);

    final public void parseXml() {
        try {
            parseDocument(getDocument(getClass().getResourceAsStream(this.fileName)));
        } catch (DuplicateDefinitionException e) {
            LOGGER.error(e);
        }
    }

    private void parseDocument(Document document) throws DuplicateDefinitionException {
        Element root = document.getRootElement();

        for (Element handler : (List<Element>) root.elements()) {
            RequestMapDefinition definitation = new RequestMapDefinition();

            String urlPattern = handler.attributeValue("urlPattern");

            if (requestMap.containsKey(urlPattern)) {
                throw new DuplicateDefinitionException(urlPattern+" handler is exist. Handler class = "+requestMap.get(urlPattern).getClassName());
            }

            definitation.setClassName(handler.attributeValue("class"));

            definitation.setUrlPattern(urlPattern);

            definitation.setMethod(handler.attributeValue("method"));

            definitation.setRedirect(Boolean.valueOf(handler.attributeValue("redirect")));

            requestMap.put(urlPattern, definitation);

        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, RequestMapDefinition> getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(Map<String, RequestMapDefinition> requestMap) {
        this.requestMap = requestMap;
    }
}
