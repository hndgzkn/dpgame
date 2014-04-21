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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Iterator;

import org.dpgame.puzzle.model.basics.APath;
import org.dpgame.puzzle.model.basics.AStep;
import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.basics.IStep;
import org.dpgame.puzzle.model.basics.Step;
import org.dpgame.tools.exceptions.PathCrossesItSelf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * An abstract JUnit test class to test the abstract class {@link APath}.
 * 
 * @see APath
 * 
 * @author Hande Özaygen
 * @version 1.1.3
 * 
 */
public abstract class JUnit_APath {

	/**
	 * A path to be used for the tests.
	 */
	protected APath path;

	/**
	 * A path to be used for the tests.
	 */
	protected APath pathAdded;

	/**
	 * A step with direction (1,0) to be used for the tests.
	 */
	protected AStep stepR;

	/**
	 * A step with direction (-1,0) to be used for the tests.
	 */
	protected AStep stepL;

	/**
	 * A step with direction (0,1) to be used for the tests.
	 */
	protected AStep stepU;

	/**
	 * A step with direction (0,-1) to be used for the tests.
	 */
	protected AStep stepD;

	/**
	 * A step with direction (1,1) to be used for the tests.
	 */
	protected AStep stepDiagonal;

	/**
	 * Initializes necessary resources to be used during the tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		stepR = new Step(1, 0);
		stepL = new Step(-1, 0);
		stepU = new Step(0, 1);
		stepD = new Step(0, -1);
		stepDiagonal = new Step(1, 1);
	}

	/**
	 * Releases any resources used during tests.
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		path = null;
		pathAdded = null;
		stepL = null;
		stepR = null;
		stepU = null;
		stepD = null;
		stepDiagonal = null;
	}

	/**
	 * Tests the {@link APath#getLength()} method.
	 */
	@Test
	public void testGetLength() {
		assertEquals(0, path.getLength());

		path.add(stepR);
		assertEquals(1, path.getLength());

		path.add(stepU);
		assertEquals(2, path.getLength());

		path.add(stepL);
		assertEquals(3, path.getLength());
	}

	/**
	 * Tests the {@link APath#getFirst()}
	 */
	@Test
	public void testGetFirst() {
		assertEquals(null, path.getFirst());
		path.add(stepD);
		assertEquals(0, path.getFirst().getHDirection());
		assertEquals(-1, path.getFirst().getVDirection());
	}

	/**
	 * Tests the {@link APath#getLast()} method.
	 */
	@Test
	public final void testGetLast() {
		assertEquals(null, path.getLast());
		path.add(stepD);
		assertEquals(0, path.getLast().getHDirection());
		assertEquals(-1, path.getLast().getVDirection());

		assertTrue(path.getFirst().equals(path.getLast()));

		path.add(stepR);
		assertEquals(stepR, path.getLast());
		assertFalse(path.getFirst().equals(path.getLast()));
	}

	/**
	 * Tests the {@link APath#add(org.dpgame.puzzle.model.basics.IStep)} method.
	 */
	@Test
	public abstract void testAddStep();

	/**
	 * Tests the {@link APath#add(org.dpgame.puzzle.model.basics.IStep)} method for exception.
	 * 
	 * Tests the case that a step with opposite hDirection direction to the last
	 * step of the path is added to the path.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddStep_Exception1() {
		path.add(stepL);
		path.add(stepR);
	}

	/**
	 * Tests the {@link APath#add(org.dpgame.puzzle.model.basics.IStep)} method for exception.
	 * 
	 * Tests the case that a step with opposite vDirection direction to the last
	 * step of the path is added to the path.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddStep_Exception2() {
		path.add(stepU);
		path.add(stepD);
	}

	/**
	 * Tests the {@link APath#add(org.dpgame.puzzle.model.basics.IStep)} method for exception.
	 * 
	 * Tests the case that a null step is added to the path.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddStep_Exception3() {
		path.add((IStep) null);
	}

	/**
	 * Tests the {@link APath#add(IPath)} method.
	 */
	@Test
	public void testAddPath() {
		path.add(stepD);
		path.add(stepR);

		path.add(pathAdded);
		assertEquals(2, path.getLength());

		pathAdded.add(stepU);
		pathAdded.add(stepR);

		path.add(pathAdded);
		assertTrue(path.getLast().equals(stepR));
		assertEquals(4, path.getLength());
		assertTrue(path.toString().equals("(0,-1) -> (1,0) -> (0,1) -> (1,0)"));

		path.add(pathAdded);
		assertTrue(path.toString().equals(
				"(0,-1) -> (1,0) -> (0,1) -> (1,0) -> (0,1) -> (1,0)"));
	}

	/**
	 * Tests the {@link APath#add(IPath)} method for exception.
	 * 
	 * Tests the case that a path whose starting step points to a step which has
	 * opposite horizontal direction with the path.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddPath_Exception1() {
		path.add(stepD);
		path.add(stepL);

		pathAdded.add(stepR);
		pathAdded.add(stepU);

		path.add(pathAdded);
	}

	/**
	 * Tests the {@link APath#add(IPath)} method for exception.
	 * 
	 * Tests the case that a path whose starting step points to a step which has
	 * opposite vertical direction with the path.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddPath_Exception2() {
		path.add(stepL);
		path.add(stepD);

		pathAdded.add(stepU);
		pathAdded.add(stepR);

		path.add(pathAdded);
	}

	/**
	 * Tests the {@link APath#add(IPath)} method for exception.
	 * 
	 * Tests the case that a null path is added to the path.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddPath_Exception3() {
		path.add((IPath) null);
	}

	/**
	 * Tests the {@link APath#iterator()} method.
	 */
	@Test
	public void testIterator() {
		Iterator<IStep> iterator = path.iterator();
		assertNotNull(iterator);
		assertFalse(iterator.hasNext());

		path.add(stepD);
		assertTrue(iterator.hasNext());
	}

	/**
	 * Tests the {@link IPath#setColor(java.awt.Color)} method.
	 */
	@Test
	public void testSetColor() {
		path.setColor(Color.CYAN);
		
		path.add(stepL);
		path.add(stepD);

		
		Iterator<IStep> pathIterator = path.iterator();
		while(pathIterator.hasNext())
			assertNotSame(Color.CYAN, pathIterator.next().getColor());
		path.setColor(Color.CYAN);
		while(pathIterator.hasNext())
			assertEquals(Color.CYAN, pathIterator.next().getColor());
	}

	/**
	 * Tests the {@link IPath#setColor(java.awt.Color)} method for exception.
	 * 
	 * Tests the case that the specified color is null.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testSetColor_Exception1() {
		path.setColor(null);
	}

	/**
	 * Tests the {@link IPath#rotate()} method.
	 */
	@Test
	public void testRotate() {
		path.add(stepL);
		path.add(stepD);
		path.add(stepD);
		
		path.rotate();
		
		Iterator<IStep> pathIterator = path.iterator();
		assertTrue(pathIterator.next().equals(stepD));
		assertTrue(pathIterator.next().equals(stepR));
		assertTrue(pathIterator.next().equals(stepR));
	}

	/**
	 * Tests the {@link IPath#getCopy()} method.
	 */
	@Test
	public void testGetCopy() {
		path.add(stepL);
		path.add(stepD);
		path.add(stepD);
		
		pathAdded = (APath)path.getCopy();
		
		assertEquals(path.getLength(), pathAdded.getLength());
		assertTrue(path.equals(pathAdded));
		
		Iterator<IStep> pathIterator = path.iterator();
		Iterator<IStep> pathAddedIterator = pathAdded.iterator();
		
		while (pathIterator.hasNext())
			assertTrue(pathIterator.next().equals(pathAddedIterator.next()));
	}
	
	/**
	 * Tests the {@link IPath#isEquivalent(IPath)} method.
	 */
	@Test
	public void testIsEquivalent() {
		assertFalse(path.isEquivalent(null));
		path.add(stepD);
		path.add(stepD);
		path.add(stepL);
		
		pathAdded.add(stepD);
		pathAdded.add(stepD);
		
		assertFalse(path.isEquivalent(pathAdded));
		pathAdded.add(stepL);
		
		assertTrue(path.isEquivalent(pathAdded));
		
		pathAdded.rotate();
		assertTrue(path.isEquivalent(pathAdded));
		
		pathAdded.add(stepL);
		assertFalse(path.isEquivalent(pathAdded));
		
		path.add(stepU);
		assertTrue(path.isEquivalent(pathAdded));
		
		path.setColor(Color.CYAN);
		assertTrue(path.isEquivalent(pathAdded));
	}
	
	/**
	 * Tests the {@link APath#equals(Object)} method.
	 */
	@Test
	public void testEquals() {
		assertFalse(path.equals(null));
		assertFalse(path.equals(new Object()));

		path.add(stepD);
		assertFalse(path.equals(pathAdded));
		assertFalse(pathAdded.equals(path));

		pathAdded.add(new Step(0, -1));
		assertTrue(path.equals(pathAdded));
		assertTrue(pathAdded.equals(path));

		path.add(stepR);
		pathAdded.add(stepL);

		assertFalse(path.equals(pathAdded));
		assertFalse(pathAdded.equals(path));
	}

	/**
	 * Tests the {@link APath#toString()} method.
	 */
	@Test
	public void testToString() {
		path.add(stepU);
		path.add(stepR);
		path.add(stepR);
		path.add(stepD);

		assertTrue(path.toString().equals("(0,1) -> (1,0) -> (1,0) -> (0,-1)"));
	}

}
