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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;

import org.dpgame.puzzle.model.basics.AStep;
import org.dpgame.puzzle.model.basics.IStep;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * An abstract JUnit test class to test {@link AStep}.
 * 
 * @see AStep
 * @see JUnit_IColorable
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public abstract class JUnit_AStep extends JUnit_IColorable {

	/**
	 * A step of direction (0,0) to be used for the tests.
	 */
	protected AStep step;

	/**
	 * A step of direction (0,0) to be used for the tests.
	 */
	protected AStep stepS;

	/**
	 * A step in the direction (1,0) to be used for the tests.
	 */
	protected AStep stepR;

	/**
	 * A step in the direction (-1,0) to be used for the tests.
	 */
	protected AStep stepL;

	/**
	 * A step in the direction (0,-1) to be used for the tests.
	 */
	protected AStep stepD;

	/**
	 * A step in the direction (0,1) to be used for the tests.
	 */
	protected AStep stepU;

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
		step = null;
		stepS = null;
		stepR = null;
		stepL = null;
		stepD = null;
		stepU = null;
	}

	/**
	 * Tests the {@link AStep#getHDirection()} method.
	 */
	@Test
	public void testGetHDirection() {
		assertEquals(0, step.getHDirection());
		assertEquals(1, stepR.getHDirection());
		assertEquals(-1, stepL.getHDirection());
		assertEquals(0, stepD.getHDirection());
	}

	/**
	 * Tests the {@link AStep#getVDirection()} method.
	 */
	@Test
	public void testGetVDirection() {
		assertEquals(0, step.getVDirection());
		assertEquals(0, stepR.getVDirection());
		assertEquals(-1, stepD.getVDirection());
		assertEquals(1, stepU.getVDirection());
	}

	/**
	 * Tests the {@link AStep#hasOppositeDirection(IStep)} method.
	 */
	@Test
	public void testHasOppositeDirection() {
		assertTrue(stepD.hasOppositeDirection(stepU));
		assertTrue(stepU.hasOppositeDirection(stepD));
		assertTrue(stepL.hasOppositeDirection(stepR));
		assertTrue(stepR.hasOppositeDirection(stepL));

		assertFalse(step.hasOppositeDirection(stepD));
		assertFalse(step.hasOppositeDirection(stepR));
		assertFalse(step.hasOppositeDirection(stepS));

		assertFalse(stepU.hasOppositeDirection(stepR));
		assertFalse(stepD.hasOppositeDirection(stepL));
	}

	/**
	 * Tests the {@link AStep#hasOppositeDirection(IStep)} method for exception.
	 * 
	 * Tests the case that the specified step is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testHasOppositeDirection_Exception1() {
		step.hasOppositeDirection(null);
	}

	@Override
	public void testGetColor() {
		assertEquals(Color.lightGray, step.getColor());
		assertEquals(Color.BLACK, stepS.getColor());
	}

	@Override
	public void testSetColor() {
		step.setColor(Color.BLACK);
		assertEquals(Color.BLACK, step.getColor());
	}

	@Override
	public void testSetColor_Exception1() {
		step.setColor(null);
	}

	/**
	 * Tests the {@link AStep#getNext()} method.
	 */
	@Test
	public void testGetNext() {
		assertEquals(null, step.getNext());
		assertEquals(null, stepR.getNext());
		assertEquals(null, stepD.getNext());

		step.setNext(stepR);

		assertEquals(stepR, step.getNext());
		assertEquals(null, stepR.getNext());
	}

	/**
	 * Tests the {@link AStep#setNext(org.dpgame.puzzle.model.basics.IStep)} method.
	 */
	@Test
	public void testSetNext() {
		assertEquals(null, step.getNext());

		stepR.setNext(step);
		assertTrue(step.equals(stepR.getNext()));
		assertTrue(stepR.invariant());

		step.setNext(stepD);
		assertTrue(stepD.equals(step.getNext()));
		assertTrue(step.invariant());

		assertFalse(step.getNext().equals(stepR.getNext().getNext()));

		stepD.setNext(stepR);

		step.setNext(null);
		assertEquals(null, step.getNext());
	}

	/**
	 * Tests the {@link AStep#addMoveCommand(abstractions.IMoveCommand)} method.
	 */
	@Test
	public void testAddMoveCommand() {
		fail();
	}

	/**
	 * Tests the {@link AStep#addMoveCommand(abstractions.IMoveCommand)} method for exception.
	 * Tests the case that the specified command is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testAddMoveCommand_Exception1() {
		step.addMoveCommand(null);
	}

	/**
	 * Tests the {@link AStep#getMoveInvoker()} method.
	 */
	@Test
	public void testGetMoveInvoker() {
		fail();
	}

	/**
	 * Tests the {@link IStep#rotate()} method.
	 */
	@Test
	public void testRotate() {
		stepL.rotate();
		assertTrue(stepL.equals(stepD));

		stepD.rotate();
		assertTrue(stepD.equals(stepR));

		stepR.rotate();
		assertTrue(stepR.equals(stepU));
	}

	/**
	 * Tests the {@link AStep#equals(Object)} method.
	 */
	@Test
	public void testEquals() {
		assertFalse(step.equals(null));
		assertFalse(step.equals(new Object()));

		assertFalse(step.equals(stepR));
		assertFalse(stepR.equals(step));
		assertFalse(stepR.equals(stepD));
		assertFalse(stepD.equals(step));
		assertFalse(stepR.equals(stepL));

		assertTrue(step.equals(step));
		assertTrue(stepR.equals(stepR));
		assertTrue(stepD.equals(stepD));

		// tests for different colors
		assertFalse(step.equals(stepS));

		stepS.setColor(step.getColor());
		// tests with next steps same
		step.setNext(stepR);
		assertTrue(step.equals(stepS));

		stepS.setNext(stepR);
		assertTrue(step.equals(stepS));

		// tests with next steps different
		step.setNext(stepD);
		assertTrue(step.equals(stepS));
	}

	/**
	 * Tests the {@link IStep#isEquivalent(IStep)} method.
	 */
	@Test
	public void testIsEquivalent() {
		assertFalse(stepD.isEquivalent(null));

		assertTrue(stepD.isEquivalent(stepD));
		assertTrue(stepD.isEquivalent(stepU));
		assertTrue(stepD.isEquivalent(stepL));
		assertTrue(stepD.isEquivalent(stepR));

		stepR.setColor(Color.CYAN);
		assertTrue(stepD.isEquivalent(stepR));
	}

	/**
	 * Tests the {@link IStep#toString()} method.
	 */
	@Test
	public void testToString() {
		assertTrue(step.toString().equals("(0,0)"));
		assertTrue(stepR.toString().equals("(1,0)"));
		assertTrue(stepL.toString().equals("(-1,0)"));
		assertTrue(stepD.toString().equals("(0,-1)"));
	}

	/**
	 * Tests the {@link IStep#toStringWithNext()} method.
	 */
	@Test
	public void testToStringWithNext() {
		assertTrue(step.toStringWithNext().equals("(0,0) -> "));
		step.setNext(stepD);
		assertTrue(step.toStringWithNext().equals("(0,0) -> (0,-1)"));

		assertTrue(stepR.toStringWithNext().equals("(1,0) -> "));

		stepR.setNext(stepU);
		assertTrue(stepR.toStringWithNext().equals("(1,0) -> (0,1)"));

		stepU.setNext(stepU);
		assertTrue(stepR.toStringWithNext().equals("(1,0) -> (0,1)"));
	}
}