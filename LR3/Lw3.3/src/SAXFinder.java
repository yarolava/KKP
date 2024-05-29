import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class SAXFinder {

    private final SAXParser parser;
    private final FileInputStream stream;


    public SAXFinder(String fileName) throws ParserConfigurationException, SAXException, FileNotFoundException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        this.parser=factory.newSAXParser();
        this.stream=new FileInputStream(fileName);

    }


    public void process(DefaultHandler handler) throws IOException, SAXException {
        this.parser.parse(this.stream, handler);
    }
}

