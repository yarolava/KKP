import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class Task2 {
    public static void main(String[] args){
        check("Popular_Baby_Names_NY.xml", "Part.xsd");
    }




    public static void check(String xmlFileName, String xsdFileName) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        Schema schema = null;

        try {
            schema=factory.newSchema(new File(xsdFileName));
            Validator validator = schema.newValidator();
            SAXSource source = new SAXSource(new InputSource(new BufferedReader(new FileReader(xmlFileName))));

            System.out.println("Document "+xmlFileName+" validation in according with "+xsdFileName+" started");
            validator.validate(source);

            System.out.println("The result of validating: APPROVE");
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}