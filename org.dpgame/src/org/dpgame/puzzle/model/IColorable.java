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
package org.dpgame.puzzle.model;

import java.awt.Color;

/**
 * An interface for an object which has color.
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public interface IColorable {

	/**
	 * Getter method for the color of the object.
	 * 
	 * @return the color of the object.
	 */
	public Color getColor();

	/**
	 * Setter method for the color of the object.
	 * 
	 * @param color
	 *            the color of the object.
	 * @throws IllegalArgumentException
	 *             if the specified color is null.
	 */
	public void setColor(Color color) throws IllegalArgumentException;
}
