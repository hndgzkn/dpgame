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



/**
 * A board is a platform where elements of type E are positioned.
 * 
 * @author Hande Özaygen
 * @version 1.1.3
 * 
 * @param <E>
 *            Type of element on the board.
 */
public interface IBoard<E> extends IPuzzleComponent {

	/**
	 * Getter method for the number of rows of the board.
	 * 
	 * @return the number of rows of the board.
	 */
	public int getRowCount();

	/**
	 * Getter method for the number of columns of the board.
	 * 
	 * @return the number of columns of the board.
	 */
	public int getColumnCount();

	/**
	 * Sets all the elements of the board to null.
	 */
	public void clean();

	/**
	 * Getter method for the element on the specified row and column position on
	 * the board.
	 * 
	 * @param row
	 *            the row number on the board.
	 * @param column
	 *            the column number on the board.
	 * @return the object on the specified row and column position on the board.
	 * @throws IndexOutOfBoundsException
	 *             if the row number or column number is less that zero or
	 *             greater than number of rows and columns respectively.
	 */
	public E get(int rowIndex, int columnIndex) throws IndexOutOfBoundsException ;

	/**
	 * Setter method for the element on the specified row and column position on the
	 * board.
	 * 
	 * @param element
	 *            the element to be set on the board.
	 * @param row
	 *            the row number on the board.
	 * @param column
	 *            the column number on the board.
	 * @throws IndexOutOfBoundsException
	 *             if the row number or column number is less that zero or
	 *             greater than number of rows and columns respectively.
	 */
	public void set(E element, int rowIndex, int columnIndex)
			 throws IndexOutOfBoundsException;

	/**
	 * Returns the string representation of board in the form:
	 * 
	 * <pre>
	 * (0,n)....(n,n)
	 *  ...........
	 *  ...........
	 *  ...........
	 * (0,0)....(0,n)
	 * </pre>
	 * 
	 * @return the string representation of the board.
	 */
	@Override
	public String toString();
	
}
