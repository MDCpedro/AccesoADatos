
package u1ex02b;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class App {
    public static void main(String[] args) {
        try {
            // instanciamos el parseador y el handler
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parseadorSAX = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            // parseamos el archivo xml indicado
            parseadorSAX.parse("app\\src\\main\\resources\\llibres.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
