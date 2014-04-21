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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.awt.Color;

import org.dpgame.puzzle.model.basics.AStep;
import org.dpgame.puzzle.model.basics.Step;
import org.junit.Test;


/**
 * A JUnit test class to test the {@link Step} class.
 * 
 * @see AStep
 * @see Step
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class JUnit_Step extends JUnit_AStep {

	@Override
	public void setUp() throws Exception {
		step = new Step();
		stepS = new Step(0, 0, Color.black);
		stepR = new Step(1, 0);
		stepL = new Step(-1, 0);
		stepD = new Step(0, -1);
		stepU = new Step(0, 1);
	}

	/**
	 * Tests the {@link AStep#stepAbstraction(int, int)} constructor.
	 */
	@Test
	public void testConstructor() {
		// default constructor
		assertTrue(step.invariant());
		// constructor with horizontal and vertical directions
		assertTrue(stepR.invariant());
		assertTrue(stepD.invariant());
		// constructor with horizontal-vertical directions and color.
		assertTrue(stepS.invariant());
	}

	/**
	 * Tests the {@link Step#Step(int, int)} constructor.
	 * 
	 * Tests the case that the horizontal direction is greater than 1.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Exception1() {
		step = new Step(2, 0);
	}

	/**
	 * Tests the {@link Step#Step(int, int)} constructor.
	 * 
	 * Tests the case that the horizontal direction is less than -1.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Exception2() {
		step = new Step(-2, 0);
	}

	/**
	 * Tests the {@link Step#Step(int, int)} constructor.
	 * 
	 * Tests the case that the vertical direction is greater than 1.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Exception3() {
		step = new Step(0, 2);
	}

	/**
	 * Tests the {@link Step#Step(int, int, Color)} constructor.
	 * 
	 * Tests the case that the vertical direction is less than -1.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Exception4() {
		step = new Step(0, -2);
	}

	/**
	 * Tests the {@link Step#st} constructor.
	 * 
	 * Tests the case that the specified color is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Exception5() {
		step = new Step(0, 1, null);
	}
	
	@Override
	public void testHasOppositeDirection() {
		super.testHasOppositeDirection();
		assertFalse(new Step(1, 1).hasOppositeDirection(new Step(-1, 0)));
	}
}
