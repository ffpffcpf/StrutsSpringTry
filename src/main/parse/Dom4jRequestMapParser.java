package main.parse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;

public class Dom4jRequestMapParser extends RequestMapDefinitionParser {

    private static final Logger LOGGER = Logger.getLogger(Dom4jRequestMapParser.class);

    public Dom4jRequestMapParser(String xmlFileName) {
        super(xmlFileName);
    }

    @Override
    protected Document getDocument(InputStream in) {
        SAXReader reader = new SAXReader();
        try {
            return reader.read(in);
        } catch (Exception e) {
//            LOGGER.error("parser error, fileName = " + fileName, e);
        }
        return null;
    }


}
