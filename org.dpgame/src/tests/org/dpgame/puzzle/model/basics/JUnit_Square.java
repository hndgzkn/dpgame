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
package tests.org.dpgame.puzzle.model.basics;

import static org.junit.Assert.*;

import org.dpgame.puzzle.model.basics.Square;
import org.junit.Test;


/**
 * A JUnit test class to test {@link Square} class.
 * 
 * @see Square
 * @see JUnit_ISquare
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 *
 */
public class JUnit_Square extends JUnit_ISquare {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		initBoard();
		squareInMiddle = board.get(numberOfRows / 2, numberOfColumns / 2);
		squareInCorner = board.get(0, 0);
		squareAboveMiddle = board.get((numberOfRows / 2) - 1, numberOfColumns / 2);
		squareLeftToMiddle = board.get(numberOfRows / 2, (numberOfColumns / 2) - 1);
	}
	
	/**
	 * Initializes the board.
	 */
	private void initBoard() {
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				board.set(new Square(board, i, j), i, j);
	}

	/**
	 * Tests the {@link Square#Square(abstractions.IBoard, int, int)} constructor.
	 */
	@Test
	public void testConstructor() {
		assertTrue(squareInMiddle.isEmpty());
		//assertEquals("-", squareInMiddle.toString());
	}
	
	/**
	 * Tests the {@link Square#Square(abstractions.IBoard, int, int)} constructor for exception.
	 * 
	 * Tests the case that the board specified is null.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_Exception1() {
		squareInCorner = new Square(null, 1, 1);
	}

	/**
	 * Tests the {@link Square#Square(abstractions.IBoard, int, int)} constructor for exception.
	 * 
	 * Tests the case that the specified row number is less than 0.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_Exception2() {
		squareInCorner = new Square(board, -1, 1);
	}

	/**
	 * Tests the {@link Square#Square(abstractions.IBoard, int, int)} constructor for exception.
	 * 
	 * Tests the case that the specified column number is less than 0.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_Exception3() {
		squareInCorner = new Square(board, 1, -1);
	}

}
