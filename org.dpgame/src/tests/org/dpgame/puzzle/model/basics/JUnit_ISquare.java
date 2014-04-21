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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.dpgame.puzzle.model.basics.ISquare;
import org.dpgame.puzzle.model.basics.IStep;
import org.dpgame.puzzle.model.basics.Step;
import org.dpgame.puzzle.model.components.Board;
import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.objects.Catcher;
import org.dpgame.puzzle.model.objects.IBoardObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * An abstract JUnit test class to test {@link ISquare} interface.
 * 
 * @see ISquare
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public abstract class JUnit_ISquare {

	/**
	 * Number of rows on the board.
	 */
	protected int numberOfRows = 10;

	/**
	 * Number of columns on the board.
	 */
	protected int numberOfColumns = 10;

	/**
	 * A board to be used for the tests.
	 */
	protected IBoard<ISquare> board;

	/**
	 * A board object to be used for the tests.
	 */
	protected IBoardObject boardObject;

	/**
	 * A square located at some position in the middle of the board to be used
	 * for tests.
	 */
	protected ISquare squareInMiddle;

	/**
	 * A square located to the left of squareInMiddle to be used for tests.
	 */
	protected ISquare squareLeftToMiddle;

	/**
	 * A square located above squareInMidle to be used for tests.
	 */
	protected ISquare squareAboveMiddle;

	/**
	 * A square located in the left upper corner of the board to be used for
	 * tests.
	 */
	protected ISquare squareInCorner;

	/**
	 * A step in the direction (-1,0) to be used for tests.
	 */
	protected IStep stepL;

	/**
	 * A step in the direction (0,-1) to be used for tests.
	 */
	protected IStep stepD;

	/**
	 * Initializes necessary resources to be used during the tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board<ISquare>(numberOfRows, numberOfColumns);
		stepL = new Step(-1, 0);
		stepD = new Step(0, -1);
	}

	/**
	 * Releases any resources used during tests.
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		board = null;
		boardObject = null;
		stepL = null;
		stepD = null;
		squareInCorner = null;
		squareInMiddle = null;
		squareAboveMiddle = null;
		squareLeftToMiddle = null;
	}

	/**
	 * Tests the {@link ISquare#getNext(org.dpgame.puzzle.model.basics.IStep)} method.
	 */
	@Test
	public void testGetNext() {
		assertNull(squareInCorner.getNext(stepL));
		assertNull(squareInCorner.getNext(stepD));

		// assertEquals(expected, actual)
	}

	/**
	 * Tests the {@link ISquare#getNext(org.dpgame.puzzle.model.basics.IStep)} method for
	 * exception.
	 * 
	 * Tests the case that the specified step is null;
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetNext_Exception1() {
		squareInCorner.getNext(null);
	}

	/**
	 * Tests the {@link ISquare#set(org.dpgame.puzzle.model.objects.IBoardObject)} method.
	 */
	@Test
	public void testSet() {

	}

	/**
	 * Tests the {@link ISquare#set(org.dpgame.puzzle.model.objects.IBoardObject)} method for
	 * exception.
	 * 
	 * Tests the case that the specified board object is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSet_Exception1() {
		squareInMiddle.set(null);
	}

	/**
	 * Tests the {@link ISquare#remove(org.dpgame.puzzle.model.objects.IBoardObject)} method.
	 */
	@Test
	public void testRemove() {
		assertTrue(squareInCorner.isEmpty());
		Catcher catcher = new Catcher();
		squareInCorner.set(catcher);
		assertFalse(squareInCorner.isEmpty());
		squareInCorner.remove(catcher);
		assertTrue(squareInCorner.contains(catcher.getFootPrint()));
	}

	/**
	 * Tests the {@link ISquare#remove(org.dpgame.puzzle.model.objects.IBoardObject)} method for
	 * exception.
	 * 
	 * Tests the case that the specified board object is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemove_Exception1() {
		squareInCorner.remove(null);
	}

	/**
	 * Tests the {@link ISquare#contains(org.dpgame.puzzle.model.objects.IBoardObject)} method.
	 */
	@Test
	public void testContains() {
		assertTrue(squareInCorner.isEmpty());
		Catcher catcher = new Catcher();
		squareInCorner.set(catcher);
		assertFalse(squareInCorner.isEmpty());
		assertFalse(squareInCorner.contains(catcher.getFootPrint()));
		assertTrue(squareInCorner.contains(catcher));
	}

	/**
	 * Tests the {@link ISquare#isEmpty()} method.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(squareInCorner.isEmpty());
		squareInCorner.set(new Catcher());
		assertFalse(squareInCorner.isEmpty());
	}

	/**
	 * Tests the {@link ISquare#toString()} method.
	 */
	@Test
	public void testToString() {
		assertTrue("-".equals(squareInCorner.toString()));
		squareInCorner.set(new Catcher());
		assertTrue("C".equals(squareInCorner.toString()));
	}

	/**
	 * Tests the {@link ISquare#getColor()} method.
	 */
	@Test
	public void testGetColor() {
		assertEquals(Color.WHITE, squareAboveMiddle.getColor());
		assertEquals(Color.WHITE, squareInCorner.getColor());
	}

	/**
	 * Tests the {@link ISquare#setColor(java.awt.Color)} method.
	 */
	@Test
	public void testSetColor() {
		squareAboveMiddle.setColor(Color.BLACK);
		squareInCorner.setColor(Color.BLUE);
		assertEquals(Color.BLACK, squareAboveMiddle.getColor());
		assertEquals(Color.BLUE, squareInCorner.getColor());
	}
}
