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
import static org.junit.Assert.assertTrue;

import org.dpgame.puzzle.model.basics.HVPath;
import org.dpgame.tools.exceptions.PathCrossesItSelf;
import org.junit.Test;


/**
 * A JUnit test class to test the {@link HVPath} class.
 * 
 * @see HVPath
 * @see HVPath
 * 
 * @author Hande Özaygen
 * @version 1.1.2
 * 
 */
public class JUnit_HVPath extends JUnit_APath {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		path = new HVPath();
		pathAdded = new HVPath();
	}

	/**
	 * Tests the {@link HVPath#Path()} constructor.
	 */
	@Test
	public void testConstructor() {
		assertTrue(((HVPath) path).invariant());
		assertEquals(0, path.getLength());
	}

	@Override
	public void testAddStep() {
		path.add(stepU);
		assertEquals(1, path.getLength());
		assertTrue(path.getFirst().equals(stepU));
		assertTrue(path.getLast().equals(stepU));

		path.add(stepL);
		assertEquals(2, path.getLength());
		assertTrue(path.getLast().equals(stepL));

		path.add(stepD);
		assertEquals(3, path.getLength());
		assertTrue(path.getLast().equals(stepD));

		stepL.setNext(stepD);

		path.add(stepL);
		assertTrue(path.getLast().equals(stepL));
		assertEquals(4, path.getLength());
	}

	/**
	 * Tests the {@link HVPath#add(abstractions.IStep)} method for exception.
	 * 
	 * Tests the case that a step with diagonal direction is added to the end of
	 * the path.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddStep_Exception4() {
		path.add(stepDiagonal);
	}

	/**
	 * Tests the {@link HVPath#add(abstractions.IStep)} method for exception.
	 * 
	 * Tests the case that a step that will cause the path to go to staring
	 * position and cross itself is tried to be added to the path.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddStep_Exception5() {
		path.add(stepD);
		path.add(stepR);
		path.add(stepU);
		path.add(stepL);
	}

	/**
	 * Tests the {@link HVPath#add(abstractions.IStep)} method for exception.
	 * 
	 * Tests the case that a step that will cause the path to go to staring
	 * position and cross itself is tried to be added to the path.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddStep_Exception6() {
		path.add(stepU);
		path.add(stepR);
		path.add(stepR);
		path.add(stepD);
		path.add(stepD);
		path.add(stepL);
		path.add(stepL);
		path.add(stepU);
	}

	/**
	 * Tests the {@link HVPath#add(abstractions.IStep)} method for exception.
	 * 
	 * Tests the case that a step that will cause the path to cross itself some
	 * point in the middle is tried to be added to the path.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddStep_Exception7() {
		path.add(stepU);
		path.add(stepR);
		path.add(stepR);
		path.add(stepD);
		path.add(stepD);
		path.add(stepL);
		path.add(stepU);
		path.add(stepU);
	}

	/**
	 * Tests the {@link HVPath#add(abstractions.IPath)} method for exception.
	 * 
	 * Tests the case that the added path causes the path to cross itself on the first path.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddPath_Exception4() {
		path.add(stepU);
		path.add(stepU);
		path.add(stepR);
		
		pathAdded.add(stepR);
		pathAdded.add(stepR);
		pathAdded.add(stepD);
		pathAdded.add(stepL);
		pathAdded.add(stepL);
		pathAdded.add(stepU);
		
		path.add(pathAdded);
	}

	/**
	 * Tests the {@link HVPath#add(abstractions.IPath)} method for exception.
	 * 
	 * Tests the case that the added path causes the path to cross the staring point of the first.
	 */
	@Test(expected = PathCrossesItSelf.class)
	public void testAddPath_Exception5() {
		path.add(stepU);
		path.add(stepU);
		path.add(stepR);
		
		pathAdded.add(stepR);
		pathAdded.add(stepR);
		pathAdded.add(stepD);
		pathAdded.add(stepL);
		pathAdded.add(stepL);
		pathAdded.add(stepD);
		pathAdded.add(stepL);
		
		path.add(pathAdded);
	}

}
