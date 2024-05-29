import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.LinkedList;
import java.util.List;

public class EthctyHandler extends DefaultHandler {

    private final List<String> ethcList;
//    private boolean isEthcty=false;
    private String curTag="someTag";

    public EthctyHandler(){
        this.ethcList=new LinkedList<>();
    }


    public LinkedList<String> getEthcList(){return (LinkedList<String>) ethcList;}




    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }




    @Override
    public void startElement(String uri, String localeName, String qName, Attributes attributes) {
        if(qName.equalsIgnoreCase("ethcty"))
            curTag="ethcty";
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        curTag="someTag";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if(curTag.equalsIgnoreCase("ethcty") && !ethcList.contains(str)){
            ethcList.add(str);
        }
    }


}
