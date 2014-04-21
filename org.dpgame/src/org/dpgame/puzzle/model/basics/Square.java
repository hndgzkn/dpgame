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

import java.awt.Color;
import java.util.Observable;

import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.objects.IBoardObject;
import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;

/**
 * A square class that implements {@link ISquare} interface.
 * 
 * <p>
 * A square is a component of a board (see {@link IBoard}) at a specified
 * position. The square knows its position on the board which is specified as
 * its parent. The row and column numbers of the square must be specified with
 * the same positions that it is located on the board. Otherwise when it asks
 * information from parent, it will ask with the position it knows which will
 * result in wrong state or behavior.
 * 
 * @see ISquare
 * @see IBoard
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public class Square extends Observable implements ISquare {

	/**
	 * The board that this square is on.
	 */
	private IBoard<ISquare> parent;

	/**
	 * The background color of this square.
	 */
	private Color color = Color.WHITE;

	/**
	 * The row number of this square on the board.
	 */
	private int row;

	/**
	 * The column number of this square on the board.
	 */
	private int column;

	/**
	 * The board object that this square holds;
	 */
	protected IBoardObject boardObject = null;

	/**
	 * Constructor for the square.
	 * 
	 * The row and column numbers of the square must be specified with the same
	 * positions that it is located on the board. Otherwise when it asks
	 * information from parent, it will ask with the position it knows which
	 * will result in wrong state or behavior.
	 * 
	 * @param parent
	 *            the board that this square is on.
	 * @param row
	 *            the row number of the square on the board.
	 * @param column
	 *            the column number of the square on the board.
	 * @throws IllegalArgumentException
	 *             if the parent is null, or the specified row or column number
	 *             is less than 0.
	 */
	public Square(IBoard<ISquare> parent, int row, int column)
			throws IllegalArgumentException {
		if (parent == null)
			throw new IllegalArgumentException(
					"The parent board cannot be null.");
		this.parent = parent;
		if (row < 0)
			throw new IllegalArgumentException(
					"Row number cannot be less than 0.");
		if (column < 0)
			throw new IllegalArgumentException(
					"Column number cannot be less than 0.");
		this.row = row;
		this.column = column;
	}

	@Override
	public ISquare getNext(IStep step) throws IllegalArgumentException {
		if (step == null)
			throw new IllegalArgumentException(
					"Cannot get the direction of null step.");
		ISquare square;

		try {
			square = (ISquare) parent.get(row + step.getVDirection(), column
					+ step.getHDirection());
		} catch (IndexOutOfBoundsException e) {
			square = null;
		}

		return square;
	}

	@Override
	public boolean set(IBoardObject object) throws IllegalArgumentException {
		if (object == null)
			throw new IllegalArgumentException(
					"Cannot locate a null object on the square.");
		if (boardObject == null)
			boardObject = object;
		else {
			// if (object.accept(boardObject))
			boardObject = object;
			setChanged();
			notifyObservers(boardObject);
				// else
			return false;
		}
		setChanged();
		notifyObservers(boardObject);
		// System.out.println(parent.toString());
		return true;
	}

	@Override
	public void remove(IBoardObject object) throws IllegalArgumentException {
		if (object == null)
			throw new IllegalArgumentException(
					"Cannot remove a null object from the square.");
		if (contains(object))
			boardObject = boardObject.getFootPrint();
		// throw exception if it does not contain object ???
		setChanged();
		notifyObservers(boardObject);
	}

	@Override
	public boolean contains(IBoardObject object) {
		return (object != null ? object.equals(this.boardObject) : false);
	}

	@Override
	public boolean isEmpty() {
		return this.boardObject == null;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setColor(Color color) throws IllegalArgumentException {
		if (color == null)
			throw new IllegalArgumentException("A null color cannot be set");
		this.color = color;
		setChanged();
		notifyObservers(this.boardObject);
	}

	@Override
	public String toString() {
		return (this.boardObject != null ? (this.boardObject.toString() + "")
				: "-");
	}

	@Override
	public void display(IPuzzleGUIBuilder builder) {
		builder.buildBoardComponent(this, row, column);
		setChanged();
		notifyObservers(boardObject);
	}

	@Override
	public boolean canBeLocated(IBoardObject object) {
		if (object == null)
			throw new IllegalArgumentException(
					"Cannot locate a null object on the square.");
		if (boardObject == null)
			return true;
		else
			return object.accept(boardObject);
	}
}
