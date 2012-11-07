package org.textanalyzer.documentimporter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class OpenDocumentParser {
    
    StringBuffer TextBuffer;
    
    // Creates a new instance of OpenOfficeParser \*/
    
    public OpenDocumentParser() {}
    
    //Process text elements recursively
    public void processElement(Object o) {
         
        if (o instanceof Element) {
            
            Element e = (Element) o;
            String elementName = e.getQualifiedName();
            
            if (elementName.startsWith("text")) {
                
                if (elementName.equals("text:tab")) // add tab for text:tab
                    TextBuffer.append("\t");
                else if (elementName.equals("text:s"))  // add space for text:s
                    TextBuffer.append(" ");
                else {
                    List<?> children = e.getContent();
                    Iterator<?> iterator = children.iterator();
                    
                    while (iterator.hasNext()) {
                        
                        Object child = iterator.next();
                        //If Child is a Text Node, then append the text
                        if (child instanceof Text) {  
                            Text t = (Text) child;
                            TextBuffer.append(t.getValue());
                        }
                        else
                        processElement(child); // Recursively process the child element                    
                    }                    
                }
                if (elementName.equals("text:p"))
                    TextBuffer.append("\n");                    
            }
            else {
                List<?> non_text_list = e.getContent();
                Iterator<?> it = non_text_list.iterator();
                while (it.hasNext()) {
                    Object non_text_child = it.next();
                    processElement(non_text_child);                    
                }
            }                
        }
    } 
    
    public String getText(String fileName) throws Exception {
        TextBuffer = new StringBuffer();
        
        //Unzip the openOffice Document
        ZipFile zipFile = new ZipFile(fileName);
        Enumeration<?> entries = zipFile.entries();
        ZipEntry entry;
        
        while(entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
                                   
            if (entry.getName().equals("content.xml")) {
                
                TextBuffer = new StringBuffer();                
                SAXBuilder sax = new SAXBuilder();
                Document doc = sax.build(zipFile.getInputStream(entry));
                Element rootElement = doc.getRootElement();
                processElement(rootElement);
                break;
            }
        }                 
        return TextBuffer.toString();        
    }
}
