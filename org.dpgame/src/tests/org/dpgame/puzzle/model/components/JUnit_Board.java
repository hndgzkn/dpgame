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
package tests.org.dpgame.puzzle.model.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.dpgame.puzzle.model.basics.ISquare;
import org.dpgame.puzzle.model.basics.Square;
import org.dpgame.puzzle.model.components.Board;
import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.objects.Catcher;
import org.dpgame.puzzle.model.objects.Mover;
import org.dpgame.puzzle.model.objects.Target;
import org.junit.Test;


/**
 * A JUnit test class to test the {@link Board} class.
 * 
 * @see IBoard
 * @see JUnit_IBoard
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public class JUnit_Board extends JUnit_IBoard {

	/**
	 * A board to be used for the tests.
	 */
	private IBoard<ISquare> board;

	@Override
	public void setUp() throws Exception {
		board = new Board<ISquare>(numberOfRows, numberOfColumns);
	}

	@Override
	public void tearDown() throws Exception {
		board = null;
	}

	/**
	 * Tests the {@link Board#Board(int, int)} constructor.
	 */
	@Test
	public void testConstructor() {
	}

	/**
	 * Tests the {@link Board#Board(int, int)} constructor for exception.
	 * 
	 * Tests for the case that specified row number is less than 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Exception1() {
		board = new Board<ISquare>(-1, 3);
	}

	/**
	 * Tests the {@link Board#Board(int, int)} constructor for exception.
	 * 
	 * Tests for the case that specified column number is less than 0.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Exception2() {
		board = new Board<ISquare>(1, -1);
	}

	@Override
	public void testGetRowCount() {
		assertEquals(numberOfRows, board.getRowCount());
	}

	@Override
	public void testGetColumnCount() {
		assertEquals(numberOfRows, board.getColumnCount());
	}

	@Override
	public void testClean() {
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				assertEquals(null, board.get(i, j));

		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				board.set(new Square(board, i, j), i, j);

		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				assertNotNull(board.get(i, j));

		board.clean();

		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				assertEquals(null, board.get(i, j));
	}

	@Override
	public void testGet() {
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				assertEquals(null, board.get(i, j));
	}

	@Override
	public void testGet_Exception1() {
		board.get(-1, 1);
	}

	@Override
	public void testGet_Exception2() {
		board.get(numberOfRows, 1);
	}

	@Override
	public void testGet_Exception3() {
		board.get(1, -1);
	}

	@Override
	public void testGet_Exception4() {
		board.get(1, numberOfColumns);
	}

	@Override
	public void testSet() {
		ISquare square = null;
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++) {
				square = new Square(board, i, j);
				board.set(square, i, j);
				assertTrue(board.get(i, j).equals(square));
			}
	}

	@Override
	public void testSet_Exception1() {
		board.set(new Square(board, 1, 1), -1, 1);
	}

	@Override
	public void testSet_Exception2() {
		board.set(new Square(board, 1, 1), numberOfRows, 1);
	}

	@Override
	public void testSet_Exception3() {
		board.set(new Square(board, 1, 1), 1, -1);
	}

	@Override
	public void testSet_Exception4() {
		board.set(new Square(board, 1, 1), 1, numberOfColumns);
	}

	@Override
	public void testToString() {
		ISquare square = null;
		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++) {
				square = new Square(board, i, j);
				board.set(square, i, j);
				assertTrue(board.get(i, j).equals(square));
			}
		board.get(0, 1).set(new Mover(board.get(0, 1), new Catcher()));
		board.get(5, 3).set(new Target());
		assertTrue(board.toString().equalsIgnoreCase(
				"- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - T - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- - - - - - - - - - - - - - - \n"
						+ "- C - - - - - - - - - - - - - \n"));
	}

}
