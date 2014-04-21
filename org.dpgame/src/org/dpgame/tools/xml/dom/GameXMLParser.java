/*
 * Copyright (C) 2014 Hande Özaygen
 *
 * This file is part of dpgame.
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>
 */
package org.dpgame.tools.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dpgame.tools.parameters.PuzzleType;
import org.dpgame.tools.parameters.PuzzleTypeList;
import org.dpgame.tools.xml.IXMLParser;
import org.dpgame.tools.xml.XmlSections;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * An XML parser that implements {@link IXMLParser}. It uses DOM parser to parse
 * the specified xml document.
 * 
 * @see DocumentBuilderFactory
 * @see DocumentBuilder
 * @see Document
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class GameXMLParser implements IXMLParser {

	/**
	 * The document formed by parsing the specified XML file.
	 */
	private Document document;

	/**
	 * The node that has the data of selected puzzle type and level.
	 */
	private Node currentLevelNode;

	/**
	 * Constructor for the {@link GameXMLParser} class.
	 * 
	 * @param filePath
	 *            the path of the <em>game.xml</em> file to be parsed.
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public GameXMLParser(String filePath) throws IllegalArgumentException,
			NullPointerException, ParserConfigurationException, SAXException,
			IOException {
		if (filePath == null)
			throw new NullPointerException("File path cannot be null.");
		if (filePath.equals(""))
			throw new IllegalArgumentException(
					"Please specify a valid file name. \nFile name:" + filePath);

		initParser(filePath);
	}

	/**
	 * Reads the specified XML file and initializes the {@link Document} object
	 * parsing the XML file.
	 * 
	 * @param filePath
	 *            the path of the XML file to be parsed.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	private void initParser(String filePath)
			throws ParserConfigurationException, SAXException, IOException {
		File file = new File(filePath);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
		document = documentBuilder.parse(file);
	}

	@Override
	public PuzzleTypeList getTypes() {
		NodeList types = document.getElementsByTagName(XmlSections.TYPES);
		PuzzleTypeList list = new PuzzleTypeList();
		NodeList typeList = types.item(0).getChildNodes();
		Node node;
		for (int i = 0; i < typeList.getLength(); i++) {
			node = typeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
				list.add(getType((Element) node));
		}
		return list;
	}

	/**
	 * This method reads the puzzle type data, ie name of the puzzle and builder
	 * class file name.
	 * 
	 * @param element
	 *            the element object that contains puzzle type data.
	 * @return the puzzle an object of type {@link PuzzleType} which holds the
	 *         name of the puzzle and builder class file name.
	 */
	private PuzzleType getType(Element element) {
		PuzzleType type = new PuzzleType();
		type.setPuzzleName(getAttributeByName(element, XmlSections.NAME));
		type.setPuzzleType(getAttributeByName(element, XmlSections.CLASS));
		type.setNumberOfLevels(Integer.parseInt(getAttributeByName(element,
				XmlSections.LEVELS)));
		return type;
	}

	@Override
	public String getValueByTagName(Element element, String tagName) {
		// System.out.println(tagName
		// + " "
		// + element.getElementsByTagName(tagName).item(0)
		// .getTextContent());
		return element.getElementsByTagName(tagName).item(0).getTextContent();
	}

	@Override
	public String getAttributeByName(Element element, String attributeName) {
		// System.out.println(attributeName + " "
		// + element.getAttribute(attributeName));
		return element.getAttribute(attributeName);
	}

	@Override
	public Node getChildByAttribute(NodeList list, String attributeName,
			String attributeValue) {
		Node node = null;
		for (int i = 0; i < list.getLength(); i++) {
			node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
				if (getAttributeByName((Element) node, attributeName).equals(
						attributeValue))
					break;
		}
		return node;
	}

	/**
	 * Getter method for the child of the specified {@link Element} which has
	 * specified <code>tagName</code>.
	 * 
	 * @param element
	 *            the {@link Element} whose child {@link Node} with specified
	 *            tag name is requested.
	 * @param tagName
	 *            the tag name of the child {@link Node}.
	 * @return the child of the specified {@link Element} which has specified
	 *         <code>tagName</code> as {@link Node}.
	 */
	@Override
	public Node getChildByTagName(Element element, String tagName) {
		return element.getElementsByTagName(tagName).item(0);
	}

	@Override
	public Node getComponentXML(String componentName) {
		Node node = getChildByTagName((Element) currentLevelNode,
				XmlSections.COMPONENTS);
		return getChildByAttribute(node.getChildNodes(), XmlSections.NAME,
				componentName);
	}

	@Override
	public void setSelectedPuzzle(String puzzleType, int level) {

		// gets the puzzles tag
		NodeList puzzles = document.getElementsByTagName(XmlSections.PUZZLES);
		// gets all the tags under puzzles which have tagname puzzle
		NodeList puzzleList = puzzles.item(0).getChildNodes();

		// from puzzlelist selects the one with the specified puzzle name
		Node puzzleNode = getChildByAttribute(puzzleList, XmlSections.NAME,
				puzzleType);
		// selects the level tags under puzzle tag
		NodeList levelList = puzzleNode.getChildNodes();
		// selects the level tag which has specified level
		currentLevelNode = getChildByAttribute(levelList, XmlSections.NAME,
				level + "");
	}

}
