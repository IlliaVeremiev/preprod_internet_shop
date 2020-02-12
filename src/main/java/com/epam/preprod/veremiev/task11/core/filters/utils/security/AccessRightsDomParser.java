package com.epam.preprod.veremiev.task11.core.filters.utils.security;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epam.preprod.veremiev.task11.core.filters.utils.security.entities.AccessRights;
import com.epam.preprod.veremiev.task11.core.filters.utils.security.entities.Constraint;

/**
 * {@link AccessRights} DOM parser
 * 
 * @author Illia_Veremiev
 *
 */
public class AccessRightsDomParser {

	private String fileName;

	/**
	 * Constructor with parameter
	 * 
	 * @param fileName - path to xml file
	 */
	public AccessRightsDomParser(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Parses XML document
	 * 
	 * @return AccessRights instance
	 * @throws Exception if errors occurs
	 */
	public AccessRights parse() throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);

		dbf.setFeature("http://xml.org/sax/features/validation", true);
		dbf.setFeature("http://apache.org/xml/features/validation/schema", true);

		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document document = builder.parse(fileName);

		Element root = document.getDocumentElement();

		AccessRights accessRights = new AccessRights();

		NodeList constraintsNodes = root.getElementsByTagName("constraint");
		for (int i = 0; i < constraintsNodes.getLength(); i++) {
			Constraint constraint = getConstraint(constraintsNodes.item(i));
			accessRights.addConstraint(constraint);
		}
		return accessRights;
	}

	private Constraint getConstraint(Node node) {
		Constraint constraint = new Constraint();
		Element element = (Element) node;

		Node nUrlPattern = element.getElementsByTagName("url-pattern").item(0);
		constraint.setUrlPattern(nUrlPattern.getTextContent());

		NodeList nRole = element.getElementsByTagName("role");
		constraint.setRoles(getRoles(nRole));

		return constraint;
	}

	private List<String> getRoles(NodeList nodeList) {
		List<String> roles = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			roles.add(nodeList.item(i).getTextContent());
		}
		return roles;
	}
}
