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

import org.dpgame.puzzle.model.components.Board;
import org.dpgame.puzzle.model.components.IBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class to test the {@link IBoard} class.
 * 
 * @see IBoard
 * 
 * @author Hande Özaygen
 * @version 1.1.2
 * 
 */
public abstract class JUnit_IBoard {

	/**
	 * Number of rows of the board.
	 */
	protected int numberOfRows = 15;

	/**
	 * Number of columns of the board.
	 */
	protected int numberOfColumns = 15;

	/**
	 * Initializes necessary resources to be used during the tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public abstract void setUp() throws Exception;

	/**
	 * Releases any resources used during tests.
	 * 
	 * @throws Exception
	 */
	@After
	public abstract void tearDown() throws Exception;

	/**
	 * Tests the {@link IBoard#getRowCount()} method.
	 */
	@Test
	public abstract void testGetRowCount();

	/**
	 * Tests the {@link IBoard#getColumnCount()} method.
	 */
	@Test
	public abstract void testGetColumnCount();

	/**
	 * Tests the {@link IBoard#clean()} method.
	 */
	@Test
	public abstract void testClean();

	/**
	 * Tests the {@link IBoard#get(int, int)} method.
	 */
	@Test
	public abstract void testGet();

	/**
	 * Tests the {@link Board#get(int, int)} method for exception.
	 * 
	 * Tests for the case that the specified row index is less than 0.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public abstract void testGet_Exception1();

	/**
	 * Tests the {@link Board#get(int, int)} method for exception.
	 * 
	 * Tests for the case that the specified row index is greater than
	 * numberOfRows - 1.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public abstract void testGet_Exception2();

	/**
	 * Tests the {@link Board#get(int, int)} method for exception.
	 * 
	 * Tests for the case that the specified column index is less than 0.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public abstract void testGet_Exception3();

	/**
	 * Tests the {@link Board#get(int, int)} method for exception.
	 * 
	 * Tests for the case that the specified column index is greater than
	 * numberOfColumns - 1.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public abstract void testGet_Exception4();

	/**
	 * Tests the {@link IBoard#set(Object, int, int)} method.
	 */
	@Test
	public abstract void testSet();

	/**
	 * Tests the {@link Board#set(Object, int, int)} method for expection.
	 * 
	 * Tests for the case that the specified row index is less than 0.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public abstract void testSet_Exception1();

	/**
	 * Tests the {@link Board#set(Object, int, int)} method for exception.
	 * 
	 * Tests for the case that the specified row index is greater than
	 * numberOfRows - 1.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public abstract void testSet_Exception2();

	/**
	 * Tests the {@link Board#set(Object, int, int)} method for exception.
	 * 
	 * Tests for the case that the specified column index is less than 0.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public abstract void testSet_Exception3();

	/**
	 * Tests the {@link Board#set(Object, int, int)} method for exception.
	 * 
	 * Tests for the case that the specified column index is greater than
	 * numberOfColumns - 1.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public abstract void testSet_Exception4();

	/**
	 * Tests the {@link IBoard#toString()} method.
	 */
	@Test
	public abstract void testToString();
	
}
