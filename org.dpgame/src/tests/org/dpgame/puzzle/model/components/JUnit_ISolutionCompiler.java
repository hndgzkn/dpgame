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

import java.util.ArrayList;

import org.dpgame.puzzle.model.components.ISolutionCompiler;
import org.dpgame.puzzle.model.components.ISolutionPart;
import org.dpgame.puzzle.model.components.ITool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class to test {@link ISolutionCompiler} interface.
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public abstract class JUnit_ISolutionCompiler {

	/**
	 * A solution compiler to be used for the tests.
	 */
	protected ISolutionCompiler solutionCompiler;

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
		solutionCompiler = null;
	}

	/**
	 * Tests the
	 * {@link ISolutionCompiler#buildSolution(java.util.ArrayList, java.util.ArrayList)}
	 * method.
	 */
	@Test
	public abstract void testBuildSolution();

	/**
	 * Tests the
	 * {@link ISolutionCompiler#buildSolution(java.util.ArrayList, java.util.ArrayList)}
	 * method for exception.
	 * 
	 * Tests the case that the solutionParts specified is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testBuildSolution_Exception1() {
		solutionCompiler.buildSolution(null, new ArrayList<ITool>());
	}

	/**
	 * Tests the
	 * {@link ISolutionCompiler#buildSolution(java.util.ArrayList, java.util.ArrayList)}
	 * method for exception.
	 * 
	 * Tests the case that the tools specified is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testBuildSolution_Exception2() {
		solutionCompiler.buildSolution(new ArrayList<ISolutionPart>(), null);
	}

}
