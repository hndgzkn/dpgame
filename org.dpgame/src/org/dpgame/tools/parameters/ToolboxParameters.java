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
package org.dpgame.tools.parameters;

import java.util.HashMap;
import java.util.Iterator;

import org.dpgame.puzzle.model.components.IToolbox;

/**
 * A class that is used to store the tool-box parameters that are read from the
 * game XML file. Tool-box parameters are used for storing the parameters to
 * generate a random tool-box for the puzzle.
 * 
 * @see IToolbox
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class ToolboxParameters implements Iterable<ToolParameters> {

	/**
	 * The hashmap that stores the tools in the toolbox. It uses the class name
	 * of the tool as key.
	 */
	private HashMap<String, ToolParameters> tools;

	/**
	 * Constructor for <code>ToolboxParameters</code>.
	 * 
	 */
	public ToolboxParameters() {
		tools = new HashMap<String, ToolParameters>();
	}

	/**
	 * Adds the specified tool <code>toolParameter</code> to to the toolbox
	 * parameters.
	 * 
	 * @param toolParameter
	 *            the object that holds the parameters related to a specified
	 *            tool in the toolbox.
	 */
	public void add(ToolParameters toolParameter) {
		tools.put(toolParameter.getType(), toolParameter);
	}

	/**
	 * Getter method for the object that holds the tool parameters of the
	 * specified <code>toolName</code>.
	 * 
	 * @param toolName
	 *            the name of the tool that the object that holds its parameters
	 *            is required.
	 * @return the object that holds the tool parameters of the specified
	 *         <code>toolName</code>.
	 */
	public ToolParameters get(String toolName) {
		return tools.get(toolName);
	}

	@Override
	public Iterator<ToolParameters> iterator() {
		return new ToolParameterIterator(tools);
	}
}
