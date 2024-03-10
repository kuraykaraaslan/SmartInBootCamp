package org.example;


import java.util.*;
import java.io.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class Main {
    /****** START SET/GET METHOD, DO NOT MODIFY *****/
    protected String in_var_1 = "";
    protected String out_var_1 = "";

    public String getin_var_1() {
        return in_var_1;
    }

    public void setin_var_1(String val) {
        in_var_1 = val;
    }

    public String getout_var_1() {
        return out_var_1;
    }

    public void setout_var_1(String val) {
        out_var_1 = val;
    }

    /****** END SET/GET METHOD, DO NOT MODIFY *****/
    public Main() {
    }

    public void invoke() throws Exception {
        /*
         * Available Variables: DO NOT MODIFY
         * In : String in_var_1
         * Out : String out_var_1
         * Available Variables: DO NOT MODIFY
         *****/

        try {
            String xmlInput = getin_var_1();
            SAXBuilder builder = new SAXBuilder();
            Reader input = new StringReader(xmlInput);
            Document document = builder.build(input);
            Element root = document.getRootElement();
            List<Element> orgList = root.getChildren("dynamicquota");

            List<Element> newList = new ArrayList<Element>();

            for (Element org : orgList) {
                Element newElement = new Element(org.getName());
                newElement.addContent(org.getChild("InstanceId").detach());
                newElement.addContent(org.getChild("Type").detach());
                newElement.addContent(org.getChild("activationdatetime").detach());
                newElement.addContent(org.getChild("expirationdatetime").detach());
                newElement.addContent(org.getChild("InitialTotalVolume").detach());
                newList.add(newElement);
            }

            root.removeChildren("dynamicquota");
            root.addContent(newList);

            XMLOutputter xmlOutput = new XMLOutputter();
            StringWriter stringWriter = new StringWriter();
            xmlOutput.output(document, stringWriter);
            setout_var_1(stringWriter.toString());
        } catch (Exception e) {
            setout_var_1(null);
        }
    }
}
