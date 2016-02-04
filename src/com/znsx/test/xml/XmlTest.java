package com.znsx.test.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.znsx.test.bo.VideoDeviceProperty;
import com.znsx.util.xml.ElementUtil;
import com.znsx.util.xml.MyXMLOutputter;

/**
 * XmlTest
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013-9-17 下午4:18:45
 */
public class XmlTest {

	/**
	 * main方法说明
	 * 
	 * @param args
	 * @author huangbuji
	 *         <p />
	 *         Create at 2013-9-17 下午4:18:45
	 */
	public static void main(String[] args) throws Exception {
//		InputStream in = null;
//		SAXBuilder builder = new SAXBuilder();
//		Document doc = builder.build(in);
//		System.out.println(doc);
		
//		Object o = null;
//		Document d = (Document)o;
//		System.out.println(d);
		
//		Date d = new Date(1379421000026l);
//		System.out.println(d);
//		if ("1343".equals(null)) {
//			System.out.println(true);
//		} else {
//			System.out.println(false);
//		}
		
//		List<String> list = null;
//		for (String s : list) {
//			System.out.println(s);
//		}
//		VideoDeviceProperty property = new VideoDeviceProperty();
//		property.setId("1212");
//		property.setExpand("<Extend><Attr Name=\"中文\" Channel=\"4\" /></Extend>");
////		property.setExpand("lsdfdsf");
////		
//		List<String> childProperties = new ArrayList<String>();
//		childProperties.add("expand");
//		Element e = ElementUtil.createElement("Prop", property, childProperties, null);
////		
//		Document doc = new Document();
//		doc.setRootElement(e);
//		
//		Format format = Format.getRawFormat();
//		format.setEncoding("UTF-8");
//		format.setIndent("  ");
//		MyXMLOutputter out = new MyXMLOutputter(format);
//		String body = out.outputString(doc);
////		System.out.println(out.escapeAttributeEntities(property.getExpand()));
//		
//		System.out.println(body);
		
//		Document doc = new Document();
//		Element root = new Element("Response");
//		root.setAttribute("Method", "List_Device_Info");
//		root.setAttribute("Code", "200");
//		doc.setRootElement(root);
//		
//		Element device1 = new Element("Device");
//		device1.setAttribute("StandardNumber", "122211");
//		device1.setAttribute("Type", "2");
//		Element d1c1 = new Element("Speed");
//		d1c1.setText("20m/s");
//		device1.addContent(d1c1);
//		Element d1c2 = new Element("Direction");
//		d1c2.setText("上行");
//		device1.addContent(d1c2);
//		root.addContent(device1);
//		
//		Element device2 = new Element("Device");
//		device2.setAttribute("StandardNumber", "122211");
//		device2.setAttribute("Type", "10");
//		Element d2c1 = new Element("UupFluxB");
//		d2c1.setText("30");
//		device2.addContent(d2c1);
//		Element d2c2 = new Element("UupFluxS");
//		d2c2.setText("50");
//		device2.addContent(d2c2);
//		Element d2c3 = new Element("DwFluxB");
//		d2c3.setText("15");
//		device2.addContent(d2c3);
//		root.addContent(device2);
//		
//		Format format = Format.getRawFormat();
//		format.setEncoding("UTF-8");
//		format.setIndent("  ");
//		MyXMLOutputter out = new MyXMLOutputter(format);
//		String body = out.outputString(doc);
//		System.out.println(body);
//		
//		List<Element> devices = root.getChildren("Device");
//		System.out.println(devices);
//		for (Element device : devices) {
//			if (device.getAttributeValue("Type").equals("2")) {
//				System.out.println(device.getChildText("Speed"));
//			} else if (device.getAttributeValue("Type").equals("10")) {
//				System.out.println(device.getChildText("UupFluxS"));
//			}
//		}
		
//		Document doc = new Document();
//		Element root = new Element("WMS");
//		root.setText("http://192.168.1.2:8080/gis/map");
//		doc.setRootElement(root);
//		
//		Format format = Format.getRawFormat();
//		format.setEncoding("UTF-8");
//		format.setIndent("  ");
//		MyXMLOutputter out = new MyXMLOutputter(format);
//		String body = out.outputString(doc);
//		System.out.println(body);
		
//		System.out.println(new Date(1394776612996l));
//		System.out.println(new Date(1394778240017l));
		
//		Element e = new Element("Test");
//		Element c = new Element("Child");
//		e.addContent(c);
//		System.out.println(e.getChildren().size());
		
//		BigDecimal b = new BigDecimal(12);
//		System.out.println(b.toString());
		
//		String xml = "<Step Seq=\"1\" Content=\"通知附近{{路政}}[[RoadAdmin(1000)]]单位\" />";
//		SAXBuilder builder = new SAXBuilder();
//		Document doc = builder.build(new ByteArrayInputStream(xml.getBytes("utf8")));
//		String content = doc.getRootElement().getAttributeValue("Content");
//		String key = content.substring(content.indexOf("[[") + 2, content.indexOf("]]"));
//		System.out.println(key);
//		String clazz = key.substring(0, key.indexOf('('));
//		int range = Integer.parseInt(key.substring(key.indexOf('(') + 1, key.length() - 1));
//		System.out.println(clazz);
//		System.out.println(range);
//		
//		Namespace ns = Namespace.getNamespace("http://www.opengis.net/sld");
//		Namespace ogc = Namespace.getNamespace("ogc", "http://www.opengis.net/ogc");
//		Namespace xlink = Namespace.getNamespace("xlink", "http://www.w3.org/1999/xlink");
//		Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
//		
//		Document doc = new Document();
//		Element root = new Element("StyledLayerDescriptor");
//		doc.setRootElement(root);
//		root.setAttribute("version", "1.0.0");
//		root.setAttribute("schemaLocation", "http://www.opengis.net/sld StyledLayerDescriptor.xsd", xsi);
//		root.addNamespaceDeclaration(ogc);
//		root.addNamespaceDeclaration(xlink);
//		root.addNamespaceDeclaration(xsi);
//		
//		Element namedLayer = new Element("NamedLayer");
//		root.addContent(namedLayer);
//		
//		Element name = new Element("Name");
//		name.setText("hn_postgis:db_motorway_line");
//		namedLayer.addContent(name);
//		
//		Element userStyle = new Element("UserStyle");
//		namedLayer.addContent(userStyle);
//		
//		Element title = new Element("Title");
//		userStyle.addContent(title);
//		
//		Element featureTypeStyle = new Element("FeatureTypeStyle");
//		userStyle.addContent(featureTypeStyle);
//		
//		Element featureTypeName = new Element("FeatureTypeName");
//		featureTypeName.setText("Feature");
//		featureTypeStyle.addContent(featureTypeName);
//		
//		Element semanticTypeIdentifier1 = new Element("SemanticTypeIdentifier");
//		semanticTypeIdentifier1.setText("generic:geometry");
//		featureTypeStyle.addContent(semanticTypeIdentifier1);
//		
//		Element semanticTypeIdentifier2 = new Element("SemanticTypeIdentifier");
//		semanticTypeIdentifier2.setText("simple");
//		featureTypeStyle.addContent(semanticTypeIdentifier2);
//		
//		Element rule1 = new Element("Rule");
//		featureTypeStyle.addContent(rule1);
//		
//		Element rule1Name = new Element("Name");
//		rule1Name.setText("ruleg1");
//		rule1.addContent(rule1Name);
//		
//		Element rule1MinScaleDenominator = new Element("MinScaleDenominator");
//		rule1MinScaleDenominator.setText("1000000.0");
//		rule1.addContent(rule1MinScaleDenominator);
//		
//		Element rule1LineSymbolizer = new Element("LineSymbolizer");
//		rule1.addContent(rule1LineSymbolizer);
//		
//		Element rule1Stroke = new Element("Stroke");
//		rule1LineSymbolizer.addContent(rule1Stroke);
//		
//		Element rule1CssParameter1 = new Element("CssParameter");
//		rule1Stroke.addContent(rule1CssParameter1);
//		rule1CssParameter1.setAttribute("name", "stroke");
//		rule1CssParameter1.setText("#00FF00");
//		
//		Element rule1CssParameter2 = new Element("CssParameter");
//		rule1Stroke.addContent(rule1CssParameter2);
//		rule1CssParameter2.setAttribute("name", "stroke-width");
//		rule1CssParameter2.setText("5.0");		
//		
//		Element rule2 = new Element("Rule");
//		featureTypeStyle.addContent(rule2);
//		
//		Element rule2Name = new Element("Name");
//		rule2Name.setText("ruleg2");
//		rule2.addContent(rule2Name);
//		
//		Element rule2MaxScaleDenominator = new Element("MaxScaleDenominator");
//		rule2MaxScaleDenominator.setText("1000000.0");
//		rule2.addContent(rule2MaxScaleDenominator);
//		
//		Element rule2LineSymbolizer = new Element("LineSymbolizer");
//		rule2.addContent(rule2LineSymbolizer);
//		
//		Element rule2Stroke = new Element("Stroke");
//		rule2LineSymbolizer.addContent(rule2Stroke);
//		
//		Element rule2CssParameter1 = new Element("CssParameter");
//		rule2Stroke.addContent(rule2CssParameter1);
//		rule2CssParameter1.setAttribute("name", "stroke");
//		rule2CssParameter1.setText("#00FF00");
//		
//		Element rule2CssParameter2 = new Element("CssParameter");
//		rule2Stroke.addContent(rule2CssParameter2);
//		rule2CssParameter2.setAttribute("name", "stroke-width");
//		rule2CssParameter2.setText("5.0");
//		
//		Element rule2TextSymbolizer = new Element("TextSymbolizer");
//		rule2.addContent(rule2TextSymbolizer);
//		
//		Element rule2Label = new Element("Label");
//		rule2TextSymbolizer.addContent(rule2Label);
//		
//		Element rule2PropertyName = new Element("PropertyName",ogc);
//		rule2Label.addContent(rule2PropertyName);
//		rule2PropertyName.setText("name");
//		
//		Element rule2Font = new Element("Font");
//		rule2TextSymbolizer.addContent(rule2Font);
//		
//		Element rule2CssParameter3 = new Element("CssParameter");
//		rule2Font.addContent(rule2CssParameter3);
//		rule2CssParameter3.setAttribute("name", "font-family");
//		rule2CssParameter3.setText("SimSun");
//		
//		Element rule2CssParameter4 = new Element("CssParameter");
//		rule2Font.addContent(rule2CssParameter4);
//		rule2CssParameter4.setAttribute("name", "font-size");
//		rule2CssParameter4.setText("12.0");
//		
//		Element rule2CssParameter5 = new Element("CssParameter");
//		rule2Font.addContent(rule2CssParameter5);
//		rule2CssParameter5.setAttribute("name", "font-style");
//		rule2CssParameter5.setText("normal");
//		
//		Element rule2CssParameter6 = new Element("CssParameter");
//		rule2Font.addContent(rule2CssParameter6);
//		rule2CssParameter6.setAttribute("name", "font-weight");
//		rule2CssParameter6.setText("normal");
//		
//		Element rule2LabelPlacement = new Element("LabelPlacement");
//		rule2TextSymbolizer.addContent(rule2LabelPlacement);
//		
//		Element rule2LinePlacement = new Element("LinePlacement");
//		rule2LabelPlacement.addContent(rule2LinePlacement);
//		
//		Element rule2PerpendicularOffset = new Element("PerpendicularOffset");
//		rule2LinePlacement.addContent(rule2PerpendicularOffset);
//		rule2PerpendicularOffset.setText("10.0");
//		
//		Element rule2Fill = new Element("Fill");
//		rule2TextSymbolizer.addContent(rule2Fill);
//		
//		Element rule2CssParameter7 = new Element("CssParameter");
//		rule2Fill.addContent(rule2CssParameter7);
//		rule2CssParameter7.setAttribute("name", "fill");
//		rule2CssParameter7.setText("#000000");
//		
//		Element rule3 = new Element("Rule");
//		featureTypeStyle.addContent(rule3);
//		
//		Element rule3Name = new Element("Name");
//		rule3Name.setText("ruler1");
//		rule3.addContent(rule3Name);
//		
//		Element rule3Filter = new Element("Filter",ogc);
//		rule3.addContent(rule3Filter);
//		
//		Element rule3And = new Element("And",ogc);
//		rule3Filter.addContent(rule3And);
//		
//		Element rule3PropertyIsGreaterThan = new Element("PropertyIsGreaterThan",ogc);
//		rule3And.addContent(rule3PropertyIsGreaterThan);
//		
//		Element rule3PropertyName1 = new Element("PropertyName",ogc);
//		rule3PropertyIsGreaterThan.addContent(rule3PropertyName1);
//		rule3PropertyName1.setText("length");
//		
//		Element rule3Literal1 = new Element("Literal",ogc);
//		rule3PropertyIsGreaterThan.addContent(rule3Literal1);
//		rule3Literal1.setText("0.8");
//		
//		Element rule3PropertyIsLessThan = new Element("PropertyIsLessThan",ogc);
//		rule3And.addContent(rule3PropertyIsLessThan);
//		
//		Element rule3PropertyName2 = new Element("PropertyName",ogc);
//		rule3PropertyIsLessThan.addContent(rule3PropertyName2);
//		rule3PropertyName2.setText("length");
//		
//		Element rule3Literal2 = new Element("Literal",ogc);
//		rule3PropertyIsLessThan.addContent(rule3Literal2);
//		rule3Literal2.setText("1");
//		
//		Element rule3LineSymbolizer = new Element("LineSymbolizer");
//		rule3.addContent(rule3LineSymbolizer);
//		
//		Element rule3Stroke = new Element("Stroke");
//		rule3LineSymbolizer.addContent(rule3Stroke);
//		
//		Element rule3CssParameter1 = new Element("CssParameter");
//		rule3Stroke.addContent(rule3CssParameter1);
//		rule3CssParameter1.setAttribute("name", "stroke");
//		rule3CssParameter1.setText("#FF0000");
//		
//		Element rule3CssParameter2 = new Element("CssParameter");
//		rule3Stroke.addContent(rule3CssParameter2);
//		rule3CssParameter2.setAttribute("name", "stroke-width");
//		rule3CssParameter2.setText("5.0");
//		
//		Element rule4 = new Element("Rule");
//		featureTypeStyle.addContent(rule4);
//		
//		Element rule4Name = new Element("Name");
//		rule4.addContent(rule4Name);
//		rule4Name.setText("ruley1");
//		
//		Element rule4Filter = new Element("Filter",ogc);
//		rule4.addContent(rule4Filter);
//		
//		Element rule4And = new Element("And",ogc);
//		rule4Filter.addContent(rule4And);
//		
//		Element rule4PropertyIsGreaterThan = new Element("PropertyIsGreaterThan",ogc);
//		rule4And.addContent(rule4PropertyIsGreaterThan);
//		
//		Element rule4PropertyName1 = new Element("PropertyName",ogc);
//		rule4PropertyIsGreaterThan.addContent(rule4PropertyName1);
//		rule4PropertyName1.setText("length");
//		
//		Element rule4Literal1 = new Element("Literal",ogc);
//		rule4PropertyIsGreaterThan.addContent(rule4Literal1);
//		rule4Literal1.setText("1.5");
//		
//		Element rule4PropertyIsLessThan = new Element("PropertyIsLessThan",ogc);
//		rule4And.addContent(rule4PropertyIsLessThan);
//		
//		Element rule4PropertyName2 = new Element("PropertyName",ogc);
//		rule4PropertyIsLessThan.addContent(rule4PropertyName2);
//		rule4PropertyName2.setText("length");
//		
//		Element rule4Literal2 = new Element("Literal",ogc);
//		rule4PropertyIsLessThan.addContent(rule4Literal2);
//		rule4Literal2.setText("2");
//		
//		Element rule4LineSymbolizer = new Element("LineSymbolizer");
//		rule4.addContent(rule4LineSymbolizer);
//		
//		Element rule4Stroke = new Element("Stroke");
//		rule4LineSymbolizer.addContent(rule4Stroke);
//		
//		Element rule4CssParameter1 = new Element("CssParameter");
//		rule4Stroke.addContent(rule4CssParameter1);
//		rule4CssParameter1.setAttribute("name", "stroke");
//		rule4CssParameter1.setText("#EDB903");
//		
//		Element rule4CssParameter2 = new Element("CssParameter");
//		rule4Stroke.addContent(rule4CssParameter2);
//		rule4CssParameter2.setAttribute("name", "stroke-width");
//		rule4CssParameter2.setText("5.0");
//		
//		Format format = Format.getRawFormat();
//		format.setEncoding("UTF-8");
//		format.setIndent("  ");
//		MyXMLOutputter out = new MyXMLOutputter(format);
//		String body = out.outputString(doc);
//		System.out.println(body);
		
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		// 采用DTD验证
////		dbf.setValidating(true);
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		org.w3c.dom.Document d = db.parse(new File("D:/response.xml"));
//		System.out.println(d.toString());
		
		
//		long lastDate = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30;
//		System.out.println(System.currentTimeMillis() + "sys");
//		System.out.println(lastDate);
		
//		Element parent = new Element("Parent");
//		Element e1 = new Element("Item");
//		Element e2 = new Element("Item");
//		Element e3 = new Element("Item");
//		parent.addContent(e1);
//		parent.addContent(e2);
//		parent.addContent(e3);
//		
//		List<Element> items = parent.getChildren();
//		System.out.println(items.size());
//		e1.detach();
//		System.out.println(items.size());
		
		VideoDeviceProperty property = new VideoDeviceProperty();
		property.setId("1212");
		property.setExpand("<Extend><Attr Name=\"中文\" Channel=\"4\" /></Extend>");

		List<String> excludeProperties = new ArrayList<String>();
		excludeProperties.add("expand");
		Element e = ElementUtil.createElement("Prop", property, null, excludeProperties);
		
		SAXBuilder builder = new SAXBuilder();
		Document de = builder.build(new ByteArrayInputStream(property.getExpand().getBytes()));
		Content ex = de.getRootElement().detach();
		e.addContent(ex);
//		
		Document doc = new Document();
		doc.setRootElement(e);
		
		Format format = Format.getRawFormat();
		format.setEncoding("UTF-8");
		format.setIndent("  ");
		XMLOutputter out = new XMLOutputter(format);
//		MyXMLOutputter out = new MyXMLOutputter(format);
		String body = out.outputString(doc);
//		System.out.println(out.escapeAttributeEntities(property.getExpand()));
		
		System.out.println(body);
	}

}
