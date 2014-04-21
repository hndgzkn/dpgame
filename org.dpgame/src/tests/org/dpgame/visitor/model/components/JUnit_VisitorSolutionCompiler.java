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
package tests.org.dpgame.visitor.model.components;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.dpgame.puzzle.model.basics.HVPath;
import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.basics.Step;
import org.dpgame.puzzle.model.components.ISolutionPart;
import org.dpgame.puzzle.model.components.ITool;
import org.dpgame.tools.exceptions.CompileException;
import org.dpgame.tools.parameters.ActionList;
import org.dpgame.visitor.model.components.VisitorSolutionCompiler;
import org.dpgame.visitor.model.components.VisitorSolutionPart;
import org.junit.Test;

import tests.org.dpgame.puzzle.model.components.JUnit_ISolutionCompiler;

/**
 * A JUnit test class to test {@link VisitorSolutionCompiler} class.
 * 
 * @see JUnit_ISolutionCompiler
 * @see VisitorSolutionCompiler
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class JUnit_VisitorSolutionCompiler extends JUnit_ISolutionCompiler {

	/**
	 * An arraylist of solution parts to be used for tests.
	 * 
	 */
	private ArrayList<ISolutionPart> solutionParts;

	/**
	 * An arraylist of tools to be used for tests.
	 */
	private ArrayList<ITool> tools;

	/**
	 * A path of three steps to be used as a tool in the toolbox for the tests.
	 */
	private IPath pathHorizontal;

	/**
	 * A Z shaped path to be used as a tool in the toolbox for the tests.
	 */
	private IPath pathZShape;

	/**
	 * An L shaped path to be used as a tool in the toolbox for the tests.
	 */
	private IPath pathLShape;

	/**
	 * A path obtained by rotating <code>pathHorizontal</code> to be used as a
	 * tool in the toolbox for the tests.
	 */
	private IPath pathVertical;

	/**
	 * A path obtained by rotating <code>pathLShape</code> to be used as a tool
	 * in the toolbox for the tests.
	 */
	private IPath pathLShapeRotated;

	/**
	 * A path to be used for the tests.
	 */
	private IPath totalPath;

	@Override
	public void setUp() throws Exception {
		solutionCompiler = new VisitorSolutionCompiler(new ActionList());
		tools = new ArrayList<ITool>();
		solutionParts = new ArrayList<ISolutionPart>();
		pathHorizontal = new HVPath();
		pathZShape = new HVPath();
		pathLShape = new HVPath();
		initPaths();
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		tools = null;
		solutionParts = null;
		pathHorizontal = null;
		pathLShape = null;
		pathZShape = null;
		pathVertical = null;
		pathLShapeRotated = null;
		totalPath = null;
	}

	/**
	 * Initializes the paths that will be used for the tests.
	 */
	private void initPaths() {
		pathHorizontal.add(new Step(1, 0));
		pathHorizontal.add(new Step(1, 0));
		pathHorizontal.add(new Step(1, 0));
		pathHorizontal.setID(1);

		pathLShape.add(new Step(0, -1));
		pathLShape.add(new Step(0, -1));
		pathLShape.add(new Step(0, -1));
		pathLShape.add(new Step(1, 0));
		pathLShape.setID(2);

		pathZShape.add(new Step(1, 0));
		pathZShape.add(new Step(1, 0));
		pathZShape.add(new Step(0, -1));
		pathZShape.add(new Step(1, 0));
		pathZShape.add(new Step(1, 0));
		pathZShape.setID(3);

		pathVertical = pathHorizontal.getCopy();
		pathVertical.rotate();
		pathVertical.setID(pathHorizontal.getID());

		pathLShapeRotated = pathLShape.getCopy();
		pathLShapeRotated.rotate();
		pathLShapeRotated.setID(pathLShape.getID());

		totalPath = new HVPath();
	}

	/**
	 * Adds <code>pathHorizontal</code>, <code>pathLShape</code> and
	 * <code>pathZShape</code> to the <code>tools</code>.
	 */
	private void addFirstThreeTools() {
		tools.add(pathHorizontal);
		tools.add(pathLShape);
		tools.add(pathZShape);
	}

	/**
	 * Adds three solution parts with ids 1, 2, 3 in order to the
	 * <code>solutionParts</code>.
	 */
	private void addFirstThreeSolutionParts() {
		solutionParts.add(new VisitorSolutionPart(1, new ArrayList<String>()));
		solutionParts.add(new VisitorSolutionPart(2, new ArrayList<String>()));
		solutionParts.add(new VisitorSolutionPart(3, new ArrayList<String>()));
	}

	/**
	 * Concatenates <code>pathHorizontal</code>, <code>pathLShape</code> and
	 * <code>pathZShape</code>.
	 */
	private void concatenateFirstThreePaths() {
		totalPath.add(pathHorizontal);
		totalPath.add(pathLShape);
		totalPath.add(pathZShape);
	}

	@Override
	public void testBuildSolution() {
		addFirstThreeTools();
		addFirstThreeSolutionParts();
		concatenateFirstThreePaths();

		assertTrue(totalPath.equals((IPath) solutionCompiler.buildSolution(
				solutionParts, tools)));
	}

	/**
	 * Tests the
	 * {@link VisitorSolutionCompiler#buildSolution(ArrayList, ArrayList)}
	 * method.
	 * 
	 * Tests the case that there is a missing solution.
	 * 
	 * Expected exception message is "* Solution for tool 3 is missing."
	 */
	@Test(expected = CompileException.class)
	public void testBuildSolution_Case1() {
		addFirstThreeTools();

		solutionParts.add(new VisitorSolutionPart(1, new ArrayList<String>()));
		solutionParts.add(new VisitorSolutionPart(2, new ArrayList<String>()));

		solutionCompiler.buildSolution(solutionParts, tools);
	}

	/**
	 * Tests the
	 * {@link VisitorSolutionCompiler#buildSolution(ArrayList, ArrayList)}
	 * method.
	 * 
	 * Tests the case that there is a repeating solution.
	 * 
	 * Expected exception message is "* Repeated solution for tool 3."
	 */
	@Test(expected = CompileException.class)
	public void testBuildSolution_Case2() {
		addFirstThreeTools();

		addFirstThreeSolutionParts();
		solutionParts.add(new VisitorSolutionPart(3, new ArrayList<String>()));

		solutionCompiler.buildSolution(solutionParts, tools);
	}

	/**
	 * Tests the
	 * {@link VisitorSolutionCompiler#buildSolution(ArrayList, ArrayList)}
	 * method.
	 * 
	 * Tests the case that the index of the solution parts in the arraylist does
	 * not comply with the index of the toolbox in the arraylist.
	 */
	@Test
	public void testBuildSolution_Case3() {
		addFirstThreeTools();

		solutionParts.add(new VisitorSolutionPart(3, new ArrayList<String>()));
		solutionParts.add(new VisitorSolutionPart(2, new ArrayList<String>()));
		solutionParts.add(new VisitorSolutionPart(1, new ArrayList<String>()));

		concatenateFirstThreePaths();

		assertTrue(totalPath.equals((IPath) solutionCompiler.buildSolution(
				solutionParts, tools)));
	}

	/**
	 * Tests the
	 * {@link VisitorSolutionCompiler#buildSolution(ArrayList, ArrayList)}
	 * method.
	 * 
	 * Tests the case that there exists two equivalent paths in the tool list
	 * and only one solution for the equivalent paths.
	 */
	@Test
	public void testBuildSolution_Case4() {
		addFirstThreeTools();
		tools.add(pathVertical);

		addFirstThreeSolutionParts();

		concatenateFirstThreePaths();
		totalPath.add(pathVertical);

		assertTrue(totalPath.equals((IPath) solutionCompiler.buildSolution(
				solutionParts, tools)));
	}

	/**
	 * Tests the
	 * {@link VisitorSolutionCompiler#buildSolution(ArrayList, ArrayList)}
	 * method.
	 * 
	 * Tests the case that there exists two equivalent paths in the tool list
	 * and only one solution for the equivalent paths.
	 */
	@Test
	public void testBuildSolution_Case5() {
		tools.add(pathVertical);
		addFirstThreeTools();
		
		solutionParts.add(new VisitorSolutionPart(2, new ArrayList<String>()));
		solutionParts.add(new VisitorSolutionPart(3, new ArrayList<String>()));
		solutionParts.add(new VisitorSolutionPart(4, new ArrayList<String>()));
	
		totalPath.add(pathVertical);
		concatenateFirstThreePaths();
		
		assertTrue(totalPath.equals((IPath) solutionCompiler.buildSolution(
				solutionParts, tools)));
		System.out.println(totalPath.toString());
		System.out.println(solutionCompiler.buildSolution(
				solutionParts, tools));
	}

	/**
	 * Tests the
	 * {@link VisitorSolutionCompiler#buildSolution(ArrayList, ArrayList)}
	 * method.
	 * 
	 * Tests the case that there exists three equivalent paths in the tool list
	 * and only one solution for the equivalent paths.
	 */
	@Test
	public void testBuildSolution_Case6() {
		IPath pathRotatedVertical = pathVertical.getCopy();
		pathRotatedVertical.rotate();
		pathRotatedVertical.setID(pathVertical.getID());

		addFirstThreeTools();
		tools.add(pathVertical);
		tools.add(pathRotatedVertical);

		addFirstThreeSolutionParts();

		concatenateFirstThreePaths();
		totalPath.add(pathVertical);
		totalPath.add(pathRotatedVertical);

		assertTrue(totalPath.equals((IPath) solutionCompiler.buildSolution(
				solutionParts, tools)));
	}

	/**
	 * Tests the
	 * {@link VisitorSolutionCompiler#buildSolution(ArrayList, ArrayList)}
	 * method.
	 * 
	 * Tests the case that there exists two pairs of equivalent paths in the
	 * tool list and only one solution for the equivalent paths.
	 */
	@Test
	public void testBuildSolution_Case7() {
		addFirstThreeTools();
		tools.add(pathVertical);
		tools.add(pathLShapeRotated);

		addFirstThreeSolutionParts();

		concatenateFirstThreePaths();
		totalPath.add(pathVertical);
		totalPath.add(pathLShapeRotated);

		assertTrue(totalPath.equals((IPath) solutionCompiler.buildSolution(
				solutionParts, tools)));
	}

}
