import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EthctyHandler handler = new EthctyHandler();


        try {
            SAXFinder search = new SAXFinder("Popular_Baby_Names_NY.xml");

            search.process(handler);

            List<String> res = handler.getEthcList();
            for(int i=0; i<res.size(); i++){
                System.out.println(res.get(i));
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}