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
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.Color;

import org.dpgame.puzzle.model.objects.ABoardObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class to test the {@link ABoardObject} class.
 * 
 * @see ABoardObject
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public abstract class JUnit_BoardObjectAbstraction {

	/**
	 * A board object to be used for the tests.
	 */
	protected ABoardObject boardObjDefault;

	/**
	 * A board object to be used for the tests.
	 */
	protected ABoardObject boardObj;
	
	/**
	 * Different type of board object to be tested for equality 
	 */
	protected ABoardObject anotherBoardObj;

	/**
	 * Row number for the boardObj to be set in constructor.
	 */
	protected int rowNumber = 5;

	/**
	 * Column number for boardObj to be set in constructor.
	 */
	protected int colNumber = 4;

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
	public void tearDown() throws Exception {
		boardObjDefault = null;
		boardObj = null;
		anotherBoardObj = null;
	}

	/**
	 * Tests the {@link ABoardObject#BoardObjectAbstraction()}
	 * constructor.
	 */
	@Test
	public void testConstructor() {
		boardObjDefault.invariant();
		boardObj.invariant();
	}
	
	/**
	 * Tests the {@link ABoardObject#BoardObjectAbstraction(int, int)} constructor for exception.
	 * 
	 * Tests for the case that the specified row number is less than 0. 
	 */
	@Test (expected = IllegalArgumentException.class)
	public abstract void testConstructor_Exception1();
	
	/**
	 * Tests the {@link ABoardObject#BoardObjectAbstraction(int, int)} constructor for exception.
	 * 
	 * Tests for the case that the specified column number is less than 0. 
	 */
	@Test (expected = IllegalArgumentException.class)
	public abstract void testConstructor_Exception2();

	/**
	 * Tests the {@link ABoardObject#getColor()} method.
	 */
	@Test
	public abstract void testGetColor();
	
	/**
	 * Tests the {@link ABoardObject#setColor(Color)} method.
	 */
	@Test
	public void testSetColor() {
		boardObj.setColor(Color.BLACK);
		assertEquals(Color.BLACK, boardObj.getColor());
	}

	/**
	 * Tests the {@link ABoardObject#setColor(Color)} method for exception.
	 * 
	 * Tests for the case that the specified color is null.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testSetColor_Exception1() {
		boardObj.setColor(null);
	}
	/**
	 * Tests the {@link ABoardObject#getValue()} method.
	 */
	@Test
	public abstract void testGetValue();
	
	/**
	 * Tests the {@link ABoardObject#equals(Object)} method.
	 */
	@Test
	public void testEquals() {
		assertFalse(boardObj.equals(null));
		assertFalse(boardObj.equals(new Object()));
		assertFalse(boardObj.equals(boardObjDefault));
		
		
		
		boardObjDefault.setColor(Color.BLACK);
		assertFalse(boardObj.equals(boardObjDefault));
		
		anotherBoardObj.setColor(boardObj.getColor());
		assertFalse(boardObj.equals(anotherBoardObj));
	}
}
