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
package org.dpgame.tools.xml;

import org.dpgame.tools.parameters.PuzzleTypeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * An interface for an XML parser that parses <em>game.xml</em> file which
 * contains random puzzle generation and program menu parameters.
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public interface IXMLParser {


	/**
	 * Getter method for the puzzle types listed in the game.xml file.
	 * 
	 * @return the list of puzzle types.
	 */
	public PuzzleTypeList getTypes();

	/**
	 * Getter method for the value of the child element of the given
	 * <code>element</code> with the <code>tagName</code>.
	 * 
	 * @param element
	 *            the element node whose children will be searched.
	 * @param tagName
	 *            the required element's tag name.
	 * @return the value of the specified child element
	 */
	public String getValueByTagName(Element element, String tagName);

	public Node getChildByAttribute(NodeList list, String attributeName,
			String attributeValue);

	public String getAttributeByName(Element element, String attributeName);

	public Node getChildByTagName(Element element, String tagName);

	/**
	 * Sets the selected puzzle type and level so that the parser can find the
	 * necessary node in the document.
	 * 
	 * @param puzzleType
	 *            the current puzzle type.
	 * @param level
	 *            the current level.
	 */
	public void setSelectedPuzzle(String puzzleType, int level);

	/**
	 * Getter method for the {@link Node} that has the
	 * <code>componentName</code> that has the specified
	 * <code>componentName</code> as attribute value for XMLSections.NAME
	 * attribute.
	 * 
	 * @param componentName
	 *            the searched attribute value for XMLSections.NAME attribute.
	 * @return the {@link Node} that has the <code>componentName</code> that has
	 *         the specified <code>componentName</code> as attribute value for
	 *         XMLSections.NAME attribute.
	 */
	public Node getComponentXML(String componentName);
}
