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
package org.dpgame.puzzle.model.objects;

import java.awt.Image;

import org.dpgame.puzzle.model.IColorable;
import org.dpgame.puzzle.model.components.IBoard;

/**
 * A board object is an object that can be located on a board (see
 * {@link IBoard}).
 * 
 * <p>
 * A board object knows its coordinates on the board. It is represented by a
 * character on the board. It has color to show its position on the board.
 * 
 * @see IBoard
 * @see IColorable
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public interface IBoardObject extends IColorable {

	/**
	 * Getter method for the image of this board object.
	 * 
	 * @return the image of this board object.
	 */
	public Image getImage();

	/**
	 * Checks if the specified board object can communicate with this board
	 * object and if true makes them communicate.
	 * 
	 * @param object
	 *            the board object to be checked for communication with this
	 *            object.
	 * @return true if the specified board object can communicate with this
	 *         object; false otherwise.
	 */
	public boolean accept(IBoardObject object);

	/**
	 * Getter method for the footprint of this object.
	 * 
	 * @return the footprint of this object.
	 */
	public IBoardObject getFootPrint();

	public void add(IBoardObject object);

	/**
	 * Indicates whether the specified object is equal to this board object.
	 * <p>
	 * The specified object is equal to this board object if the object is an
	 * instance of {@link IBoardObject}, it has the same color, row and column
	 * positions with this board object and it has the same character value with
	 * this board object.
	 * 
	 * @param o
	 *            the object to be compared for equality
	 * @return true if the specified object is equal to this board object; false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object o);

	/**
	 * Returns the string representation of the board object on the board.
	 * 
	 * @return the string representation of the board object on the board.
	 */
	@Override
	public String toString();

}
