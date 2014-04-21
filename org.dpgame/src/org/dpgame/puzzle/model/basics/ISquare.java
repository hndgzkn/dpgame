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
package org.dpgame.puzzle.model.basics;

import org.dpgame.puzzle.model.IColorable;
import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.components.IPuzzleComponent;
import org.dpgame.puzzle.model.objects.IBoardObject;

/**
 * A square is a component of the board (see {@link IBoard}). It holds objects
 * (see {@link IBoardObject}) that are located on the board.
 * 
 * @see IBoard
 * @see IBoardObject
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public interface ISquare extends IColorable, IPuzzleComponent {

	/**
	 * Getter method for the next square in the direction of the specified step.
	 * 
	 * @param step
	 *            the step whose direction specifies the next step to be
	 *            returned.
	 * @return the next square in the direction of the specified step. If there
	 *         is no square in the specified direction, returns null.
	 * @throws IllegalArgumentException
	 *             if the specified step is null.
	 */
	public ISquare getNext(IStep step) throws IllegalArgumentException;

	/**
	 * Locates the specified board object on this square.
	 * 
	 * @param object
	 *            the board object to be located on this square.
	 * @return true if the board object is successfully located on the square;
	 *         false otherwise.
	 * @throws IllegalArgumentException
	 *             if the specified object is null.
	 */
	public boolean set(IBoardObject object) throws IllegalArgumentException;

	/**
	 * Checks wheather the specified object can be located on this square.
	 * 
	 * @param object
	 *            the object to be located on this square.
	 * @return true if this object can be located on this square; false
	 *         otherwise.
	 */
	public boolean canBeLocated(IBoardObject object);

	/**
	 * Removes the specified board object from the square.
	 * 
	 * The removed object leaves its footprint in the square if it has a
	 * footprint.
	 * 
	 * @param object
	 *            the board object to be removed from the square.
	 * @throws IllegalArgumentException
	 *             if the specified board object is null.
	 */
	public void remove(IBoardObject object) throws IllegalArgumentException;

	/**
	 * Checks if the square contains the specified board object.
	 * 
	 * @param object
	 *            the object to be checked whether it exists on the square.
	 * @return true if the specified object is on the square; false otherwise.
	 */
	public boolean contains(IBoardObject object);

	/**
	 * Checks if this square contains a board object.
	 * 
	 * @return true if this square contains a board object; false otherwise.
	 */
	public boolean isEmpty();

	// /**
	// * Checks if the specified object is the same as this square.
	// *
	// * The specified object if equal to this square if the object is an
	// instance
	// * of {@link ISquare}, it shares the same parent board with this square,
	// it
	// * has the same row and column coordinates with this square and it has the
	// * same type of board object and same color.
	// *
	// * @param o
	// * the object to be compared for equality.
	// * @return true if the specified object is equal to this square; false
	// * otherwise.
	// */
	// public boolean equals(Object o);

	/**
	 * Returns the string representation of the square.
	 * 
	 * @return the string representation of the board object on the square if
	 *         there exists one; '-' otherwise.
	 */
	public String toString();

}
