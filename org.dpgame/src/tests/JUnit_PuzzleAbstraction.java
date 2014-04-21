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

import static org.junit.Assert.fail;

import org.dpgame.puzzle.model.IPuzzle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class to test the {@link PuzzleAbstraction} class.
 * 
 * @see PuzzleAbstraction
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 *
 */
public abstract class JUnit_PuzzleAbstraction {

	/**
	 * A puzzle to be used for the tests.
	 */
	IPuzzle puzzle;
	
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
		puzzle = null;
	}

	/**
	 * Tests the {@link PuzzleAbstraction#solve()} method.
	 */
	@Test
	public void testSolve() {
		fail("Not yet implemented"); // TODO
	}

}
