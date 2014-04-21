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

/**
 * A class that is used to store the tool parameters for each tool in the
 * tool-box. The parameters are read from the game XML file. Tool parameters are
 * used for storing the parameters to generate random tools for the puzzle.
 * Tool parameters are stored in {@link ToolboxParameters}.
 * 
 * @see ToolboxParameters
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class ToolParameters {

	/**
	 * Minimum number of tools of this type in the tool-box.
	 */
	private int minNumber;

	/**
	 * Maximum number of tools of this type in the tool-box.
	 */
	private int maxNumber;

	/**
	 * Number of equivalent tools of this type among all tools in the tool-box.
	 */
	private int repeating;

	/**
	 * The class name of this tool.
	 */
	private String type;

	/**
	 * Minimum length of this tool in terms of its units.
	 */
	private int minLength;

	/**
	 * Maximum length of this tool in terms of its units.
	 */
	private int maxLength;

	/**
	 * Number of actions that will be applied to this tool for random generation
	 * of puzzle.
	 */
	private int numberOfActions;

	/**
	 * Constructor for tool parameters.
	 */
	public ToolParameters() {

	}

	/**
	 * Getter method for the minimum number of tools of this type in the
	 * tool-box.
	 * 
	 * @return the minimum number of tools of this type in the tool-box.
	 */
	public int getMinNumber() {
		return minNumber;
	}

	/**
	 * Setter method for the minimum number of tools of this type in the
	 * tool-box.
	 * 
	 * @param minNumber
	 *            the minimum number of tools of this type in the tool-box.
	 */
	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	/**
	 * Getter method for the maximum number of tools of this type in the
	 * tool-box.
	 * 
	 * @return the maximum number of tools of this type in the tool-box.
	 */
	public int getMaxNumber() {
		return maxNumber;
	}

	/**
	 * Setter method for the maximum number of tools of this type in the
	 * tool-box.
	 * 
	 * @param maxNumber
	 *            the maximum number of tools of this type in the tool-box.
	 */
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	/**
	 * Getter method for the number of equivalent tools of this type among all
	 * tools in the tool-box.
	 * 
	 * @return the number of equivalent tools of this type among all tools in
	 *         the tool-box.
	 */
	public int getRepeating() {
		return repeating;
	}

	/**
	 * Setter method for the number of equivalent tools of this type among all
	 * tools in the tool-box.
	 * 
	 * @param repeating
	 *            the number of equivalent tools of this type among all tools in
	 *            the tool-box.
	 */
	public void setRepeating(int repeating) {
		this.repeating = repeating;
	}

	/**
	 * Getter method for the class name of this tool.
	 * 
	 * @return the class name of this tool.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter method for the class name of this tool.
	 * 
	 * @param type
	 *            the class name of this tool.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter method for the minimum length of this tool in terms of its units.
	 * 
	 * @return the minimum length of this tool in terms of its units.
	 */
	public int getMinLength() {
		return minLength;
	}

	/**
	 * Setter method for the minimum length of this tool in terms of its units.
	 * 
	 * @param minLength
	 *            the minimum length of this tool in terms of its units.
	 */
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	/**
	 * Getter method for the maximum length of this tool in terms of its units.
	 * 
	 * @return the maximum length of this tool in terms of its units.
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * Setter method for the maximum length of this tool in terms of its units.
	 * 
	 * @param maxLength
	 *            the maximum length of this tool in terms of its units.
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * Getter method for the number of actions that will be applied to this tool
	 * for random generation of puzzle.
	 * 
	 * @return the number of actions that will be applied to this tool for
	 *         random generation of puzzle.
	 */
	public int getNumberOfActions() {
		return numberOfActions;
	}

	/**
	 * Setter method for the number of actions that will be applied to this tool
	 * for random generation of puzzle.
	 * 
	 * @param numberOfActions
	 *            the number of actions that will be applied to this tool for
	 *            random generation of puzzle.
	 */
	public void setNumberOfActions(int numberOfActions) {
		this.numberOfActions = numberOfActions;
	}
}
