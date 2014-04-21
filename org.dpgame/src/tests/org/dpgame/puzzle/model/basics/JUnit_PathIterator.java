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

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.dpgame.puzzle.model.basics.AStep;
import org.dpgame.puzzle.model.basics.HVPath;
import org.dpgame.puzzle.model.basics.IStep;
import org.dpgame.puzzle.model.basics.PathIterator;
import org.dpgame.puzzle.model.basics.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class to test {@link PathIterator}.
 * 
 * @see PathIterator
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 *
 */
public class JUnit_PathIterator {
	
	/**
	 * An iterator to be used for the tests.
	 */
	private Iterator<IStep> forwardIterator;

	/**
	 * A path to be used for the tests.
	 */
	private HVPath path;
	
	/**
	 * A square with direction (1,0) to be used for the tests.
	 */
	private AStep squareR;

	/**
	 * A square with direction (-1,0) to be used for the tests.
	 */
	private AStep squareL;

	/**
	 * A square with direction (0,1) to be used for the tests.
	 */
	private AStep squareU;

	/**
	 * A square with direction (0,-1) to be used for the tests.
	 */
	private AStep squareD;

	/**
	 * Initializes necessary resources to be used during the tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		path = new HVPath();
		squareR = new Step(1, 0);
		squareL = new Step(-1, 0);
		squareU = new Step(0, 1);
		squareD = new Step(0, -1);
	}

	/**
	 * Releases any resources used during tests.
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		forwardIterator = null;
		path = null;
		squareL = null;
		squareR = null;
		squareU = null;
		squareD = null;
	}

	/**
	 * Tests the {@link PathIterator#PathIterator(abstractions.IPath)} constructor.
	 */
	@Test
	public void testConstructor() {
		forwardIterator = new PathIterator(path);
		assertNotNull(forwardIterator);
		assertFalse(forwardIterator.hasNext());
		
		path.add(squareD);
		path.add(squareD);
		path.add(squareD);
		
		forwardIterator = new PathIterator(path);
		assertNotNull(forwardIterator);
		assertTrue(forwardIterator.hasNext());
	}

	/**
	 * Tests the {@link PathIterator#PathIterator(abstractions.IPath)} constructor for exception.
	 * 
	 * Tests the case when a null path is specified in the constructor.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructor_Exception1() {
		forwardIterator = new PathIterator(null);
	}

	/**
	 * Tests the {@link PathIterator#hasNext()} method.
	 */
	@Test
	public void testHasNext() {
		forwardIterator = new PathIterator(path);
		assertFalse(forwardIterator.hasNext());
		
		path.add(squareL);
		path.add(squareU);
		path.add(squareU);
		path.add(squareR);
		path.add(squareD);
		
		for(int i = 0; i < 5; i++) {
			assertTrue(forwardIterator.hasNext());
		}
	}

	/**
	 * Tests the {@link PathIterator#next()} method.
	 */
	@Test
	public void testNext() {
		path.add(squareL);
		forwardIterator = new PathIterator(path);
		assertTrue(forwardIterator.next().equals(squareL));
	}

	/**
	 * Tests the {@link PathIterator#next()} method for exception.
	 * 
	 * Tests the case that next element method is called when next element is null.
	 */
	@Test (expected = NoSuchElementException.class)
	public void testNext_Exception1() {
		forwardIterator = new PathIterator(path);
		forwardIterator.next();
	}
	
	/**
	 * Tests the {@link PathIterator#remove()} method.
	 */
	@Test
	public void testRemove() {
		path.add(squareL);
		assertEquals(1,path.getLength());
		
		forwardIterator = path.iterator();
		forwardIterator.remove();
		assertEquals(1,path.getLength());
	}

}
