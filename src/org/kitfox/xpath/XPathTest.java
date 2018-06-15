package org.kitfox.xpath;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

public class XPathTest {

    public static void main(String args[]) throws Exception {
        InputStream inputStream = (new XPathTest()).getClass().getResourceAsStream("./xml.xml");

        String s = IOUtils.toString(inputStream);

        String outgoingPattern = "(?s)<MyXml[^\\>]*>(.*)</MyXml[^\\>]*>";
        Pattern pattern = Pattern.compile(outgoingPattern, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(s);

        matcher.find();
        String xml = "<MyXml>" + matcher.group(1) + "</MyXml>";
        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

        Document doc = docBuilder.parse(bais);

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        String roleGuid = getXPathValue(xpath, "/MyXml/MyRole[1]/ClientGuid", doc);
        String clientGuid = getXPathValue(xpath, "/MyXml/MyRole[2]/ClientGuid", doc);

        System.out.println(getXPathValue(xpath,
                "/MyXml/MyClientField[FieldName='IdentificationNumber'][ClientGuid='" + clientGuid + "']/TextValue", doc));
    }

    protected static String getXPathValue(XPath xpath, String expr, Document doc) throws XPathExpressionException {
        XPathExpression expression = xpath.compile(expr);
        return  (String)expression.evaluate(doc, XPathConstants.STRING);
    }

}
