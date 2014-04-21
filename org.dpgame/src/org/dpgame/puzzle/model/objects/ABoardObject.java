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

import java.awt.Color;
import java.awt.Image;

/**
 * An abstract class that implements {@link IBoardObject}.
 * 
 * @see IBoardObject
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public abstract class ABoardObject implements IBoardObject {

	/**
	 * The color of this board object on the board.
	 */
	private Color color = Color.WHITE;

	/**
	 * Image of this board object on the board.
	 */
	protected Image image = null;

	/**
	 * The value that represents this board object.
	 */
	protected char value;

	/**
	 * A board object is in safe state if:
	 * <ul>
	 * <li>Both row and column numbers are greater than zero</li>
	 * <li>Color is not null</li>
	 * </ul>
	 * 
	 * This method does not have 100% coverage in unit tests. It is due to the
	 * fact that it is not possible to set row or column number less than 0 and
	 * it is not possible to set color to null.
	 */
	public boolean invariant() {
		return true;// (rowNumber > 0 && colNumber > 0 && color != null);
	}

	// /**
	// * Constructor for the board object. Initializes a board object at the
	// * specified row and column on the specified board.
	// */
	// public ABoardObject() throws IllegalArgumentException {
	// }
	// /**
	// * Constructor for the board object. Initializes a board object at the
	// * specified row and column on the specified board.
	// *
	// * @param board
	// * the board that this object is located on.
	// * @param row
	// * the row of the object.
	// * @param column
	// * the column of the object.
	// */
	// public ABoardObject(IBoard board, int row, int column) {
	// this.board = board;
	// this.rowNumber = row;
	// this.colNumber = column;
	// board.setBoardObject(row, column, this);
	// }

	@Override
	public Image getImage() {
		return image;
	}

	// can be deleted
	@Override
	public void setColor(Color color) throws IllegalArgumentException {
		if (color == null)
			throw new IllegalArgumentException(
					"A non existing color cannot be set.");
		this.color = color;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public abstract boolean accept(IBoardObject object);

	// public abstract boolean check(IBoardObject object);

	@Override
	public String toString() {
		return value + "";
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof IBoardObject))
			return false;
		IBoardObject object = (IBoardObject) o;
		if (!object.toString().equals(this.toString())
				|| object.getColor() != color)
			// || object.getRow() != rowNumber
			// || object.getColumn() != colNumber)
			return false;
		return true;
	}
}
