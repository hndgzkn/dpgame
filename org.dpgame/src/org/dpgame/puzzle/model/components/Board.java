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
package org.dpgame.puzzle.model.components;

import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;

/**
 * An abstract class that implements {@link IBoard}.
 * 
 * @see IBoard
 * 
 * @author Hande Özaygen
 * @version 1.1.3
 * 
 * @param <E>
 * 
 */
public class Board<E> implements IBoard<E> {

	/**
	 * The board is a two dimensional array of elements.
	 */
	private Object[][] board = null;

	/**
	 * Number of columns of the board.
	 */
	private int numberOfColumns;

	/**
	 * Number of rows of the board.
	 */
	private int numberOfRows;

	/**
	 * Constructor for the board.
	 * <p>
	 * Initializes a board with the specified number of rows and columns.
	 * 
	 * @param num_row
	 *            number of rows of the board.
	 * @param num_col
	 *            number of columns of the board.
	 * @throws IllegalArgumentException
	 *             if either number of rows or columns is less than 0.
	 */
	public Board(int numberOfRows, int numberOfColumns)
			throws IllegalArgumentException {
		if (numberOfRows < 0)
			throw new IllegalArgumentException(
					"Number of rows must be greater than 0.");
		if (numberOfColumns < 0)
			throw new IllegalArgumentException(
					"Number of columns must be greater than 0.");
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		board = new Object[numberOfRows][numberOfColumns];
	}

	@Override
	public void clean() {
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				board[i][j] = null;
	}

	@Override
	public int getRowCount() {
		return numberOfRows;
	}

	@Override
	public int getColumnCount() {
		return numberOfColumns;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int rowIndex, int columnIndex)
			throws IndexOutOfBoundsException {
		checkBounds(rowIndex, columnIndex);
		return (E) board[rowIndex][columnIndex];
	}

	@Override
	public void set(E element, int rowIndex, int columnIndex)
			throws IndexOutOfBoundsException {
		checkBounds(rowIndex, columnIndex);
		board[rowIndex][columnIndex] = element;
	}

	/**
	 * Checks if the specified row and column numbers are inside the bounds of
	 * the board.
	 * 
	 * @param rowIndex
	 *            the row index on the board.
	 * @param columnIndex
	 *            ths column index on the board.
	 * @throws IndexOutOfBoundsException
	 *             if the row number or column number is less that zero or
	 *             greater than number of rows and columns respectively.
	 */
	private void checkBounds(int rowIndex, int columnIndex)
			throws IndexOutOfBoundsException {
		if (rowIndex >= numberOfRows || rowIndex < 0)
			throw new IndexOutOfBoundsException(
					"Row index must be in bounds 0 - " + numberOfRows + ".");
		if (columnIndex >= numberOfColumns || columnIndex < 0)
			throw new IndexOutOfBoundsException(
					"Row index must be in bounds 0 - " + numberOfColumns + ".");
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = numberOfRows - 1; i > -1; i--) {
			for (int j = 0; j < numberOfColumns; j++) {
				str += board[i][j].toString() + " ";
			}
			str += "\n";
		}
		return str;
	}

	@Override
	public void display(IPuzzleGUIBuilder builder) {
		builder.buildBoard(numberOfRows, numberOfColumns);
	}
}
