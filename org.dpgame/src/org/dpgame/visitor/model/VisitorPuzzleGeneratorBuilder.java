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
package org.dpgame.visitor.model;

import org.dpgame.puzzle.model.IPuzzleGenerator;
import org.dpgame.puzzle.model.IPuzzleGeneratorBuilder;
import org.dpgame.tools.parameters.Action;
import org.dpgame.tools.parameters.ActionList;
import org.dpgame.tools.parameters.ActionObject;
import org.dpgame.tools.parameters.BoardParameters;
import org.dpgame.tools.parameters.ToolParameters;
import org.dpgame.tools.parameters.ToolboxParameters;
import org.dpgame.tools.xml.IXMLParser;
import org.dpgame.tools.xml.XmlSections;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A puzzle generator builder for visitor pattern puzzle. The puzzle builder
 * implements {@link IPuzzleGeneratorBuilder}.
 * 
 * 
 * @see IPuzzleGeneratorBuilder
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class VisitorPuzzleGeneratorBuilder implements IPuzzleGeneratorBuilder {

	/**
	 * The XML parser that parses the game.xml file.
	 */
	private IXMLParser parser;

	/**
	 * Board generation parameters read from game.xml file.
	 */
	private BoardParameters boardParam;

	/**
	 * The list of applicable actions read from game.xml file.
	 */
	private ActionList actionList;

	/**
	 * Tool-box generation parameters read from game.xml file.
	 */
	private ToolboxParameters toolboxParameters;

	/**
	 * Constructor for visitor puzzle builder.
	 * 
	 * @param the
	 *            XML parser that parses the game.xml file.
	 */
	public VisitorPuzzleGeneratorBuilder(IXMLParser parser)
			throws NullPointerException {
		if (parser == null)
			throw new NullPointerException("XML parser cannot be null.");
		this.parser = parser;
	}

	@Override
	public void buildBoardParameters() {
		// returns the node for the tag <board>
		Node node = parser.getComponentXML(XmlSections.BOARD);

		int numberOfRows = Integer.parseInt(parser.getAttributeByName(
				(Element) node, XmlSections.NUMBEROFROWS));
		int numberOfColumns = Integer.parseInt(parser.getAttributeByName(
				(Element) node, XmlSections.NUMBEROFCOLUMNS));

		boardParam = new BoardParameters(numberOfRows, numberOfColumns);
	}

	@Override
	public void buildActionList() {
		Node node = parser.getComponentXML(XmlSections.SOLUTIONCOMPILER);

		Node nodeActions = parser.getChildByTagName((Element) node,
				XmlSections.ACTIONS);

		NodeList listOfActions = nodeActions.getChildNodes();

		actionList = new ActionList();
		Action action = null;

		Node nodeAction;
		for (int i = 0; i < listOfActions.getLength(); i++) {
			nodeAction = listOfActions.item(i);
			if (nodeAction.getNodeType() == Node.ELEMENT_NODE) {
				action = new Action();
				action.setName(parser.getAttributeByName((Element) nodeAction,
						XmlSections.NAME));
				action.setIncrementOffset(Integer.parseInt(parser
						.getAttributeByName((Element) nodeAction,
								XmlSections.INCREMENT)));
				action.setMaxNumberOfApplication(Integer.parseInt(parser
						.getAttributeByName((Element) nodeAction,
								XmlSections.MAX)));
				actionList.add(action);
				Node nodeObjects = parser.getChildByTagName(
						(Element) nodeAction, XmlSections.OBJECTS);
				if (nodeObjects != null) {
					NodeList objectList = nodeObjects.getChildNodes();
					Node nodeObject = null;
					ActionObject actionObject = null;
					for (int j = 0; j < objectList.getLength(); j++) {
						nodeObject = objectList.item(j);
						if (nodeObject.getNodeType() == Node.ELEMENT_NODE) {
							actionObject = new ActionObject();
							actionObject.setType(parser.getAttributeByName(
									(Element) nodeObject, XmlSections.NAME));
							actionObject.setPreconditionAction(parser
									.getAttributeByName((Element) nodeObject,
											XmlSections.PRECONDITION));
							actionObject.setPreconditionType(parser
									.getAttributeByName((Element) nodeObject,
											XmlSections.PRECONDITIONTYPE));
						}
					}
				}
			}
		}
	}

	@Override
	public void buildToolboxParameters() {
		toolboxParameters = new ToolboxParameters();
		// returns the node for the tag <toolbox>
		Node node = parser.getComponentXML(XmlSections.TOOLBOX);

		// gets the node for tag <tools>
		Node nodeTools = parser.getChildByTagName((Element) node,
				XmlSections.TOOLS);

		// gets the list of nodes for tag <tool> which are under tag <tools>
		NodeList toolList = nodeTools.getChildNodes();

		ToolParameters tool = null;
		Node nodeTool = null;

		// iterates over all the tool nodes
		for (int i = 0; i < toolList.getLength(); i++) {
			nodeTool = toolList.item(i);
			if (nodeTool.getNodeType() == Node.ELEMENT_NODE) {
				tool = new ToolParameters();
				// gets the type of the tool
				tool.setType(parser.getAttributeByName((Element) nodeTool,
						XmlSections.TYPE));
				tool.setMinNumber(Integer.parseInt(parser.getAttributeByName(
						(Element) nodeTool, XmlSections.MINNUMBER)));
				tool.setMaxNumber(Integer.parseInt(parser.getAttributeByName(
						(Element) nodeTool, XmlSections.MAXNUMBER)));
				tool.setRepeating(Integer.parseInt(parser.getAttributeByName(
						(Element) nodeTool, XmlSections.REPEATING)));
				tool.setMinLength(Integer.parseInt(parser.getAttributeByName(
						(Element) nodeTool, XmlSections.MINLENGTH)));
				tool.setMaxLength(Integer.parseInt(parser.getAttributeByName(
						(Element) nodeTool, XmlSections.MAXLENGTH)));
				tool.setNumberOfActions(Integer.parseInt(parser
						.getAttributeByName((Element) nodeTool,
								XmlSections.NUMBEROFACTIONS)));
				toolboxParameters.add(tool);
			}
		}
	}

	@Override
	public void buildBoardObjects() {

	}

	@Override
	public IPuzzleGenerator getPuzzleGenerator() {
		return new VisitorPuzzleGenerator(boardParam, actionList,
				toolboxParameters);
	}

}
