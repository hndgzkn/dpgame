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
package org.dpgame.visitor.model.components;

import java.util.ArrayList;
import java.util.Observable;

import org.dpgame.puzzle.model.basics.HVPath;
import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.components.ISolution;
import org.dpgame.puzzle.model.components.ISolutionCompiler;
import org.dpgame.puzzle.model.components.ISolutionPart;
import org.dpgame.puzzle.model.components.ITool;
import org.dpgame.puzzle.model.components.IToolbox;
import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;
import org.dpgame.tools.exceptions.CompileException;
import org.dpgame.tools.exceptions.PathCrossesItSelf;
import org.dpgame.tools.parameters.ActionList;

/**
 * A solution compiler for visitor pattern which implements
 * {@link ISolutionCompiler}. For visitor compiler to compile correctly,
 * solution parts (see {@link ISolutionPart}) must contain a solution for each
 * distinct type of tool in the tool-box and there has to be unique solution for
 * each tool. The tools in the tool box are {@link HVPath} instances for visitor
 * pattern. An equivalent of a path is a path that can be obtained from any
 * number of rotations of the original path.
 * 
 * @see ISolutionCompiler
 * @see Observable
 * @see ISolutionPart
 * @see IToolbox
 * @see ITool
 * @see HVPath
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class VisitorSolutionCompiler extends Observable implements
		ISolutionCompiler {

	/**
	 * The string that stores the compilation errors.
	 */
	private String compilationErrors = "";

	/**
	 * The action list that can be applied to the tools in the toolbox.
	 */
	private ActionList actionList;

	/**
	 * The array that keeps track of the generated solutions for each tool. The
	 * size of the array is the same as the number of tools in the toolbox. The
	 * array is initialized with value -1. As the solution parts are parsed, the
	 * values in the array are updated. The index of the array corresponds the
	 * the index of the tool in the toolbox. The value stores the index of the
	 * correspoding solution part.
	 */
	private int[] solutionIndex;

	// action list can be removed from here and put into toolbox
	/**
	 * The constructor for the solution compiler for visitor pattern.
	 * 
	 * @param actionList
	 *            the list of actions that can be applied to each tool in the
	 *            toolbox.
	 */
	public VisitorSolutionCompiler(ActionList actionList)
			throws NullPointerException {
		if (actionList == null)
			throw new NullPointerException("Action list cannot be null.");
		this.actionList = actionList;
	}

	@Override
	public ISolution buildSolution(ArrayList<ISolutionPart> solutionParts,
			ArrayList<ITool> tools) throws NullPointerException,
			CompileException {
		if (solutionParts == null)
			throw new NullPointerException("Solution parts cannot be null.");
		if (tools == null)
			throw new NullPointerException("Tools cannot be null.");
		// initialize compilation errors to empty string each time a new
		// solution is built.
		compilationErrors = "";

		checkSolutionExists(solutionParts, tools);
		checkCompilationErrors();
		IPath solutionPath = generateSolutionPath(solutionParts, tools); 
		checkCompilationErrors();
		return solutionPath;
	}
	
	private boolean checkCompilationErrors() throws CompileException {
		if (!compilationErrors.equals("")) {
			System.out.println("compiler error: " + compilationErrors);
			setChanged();
			notifyObservers(compilationErrors);
			throw new CompileException(compilationErrors);
		}
		return true;
	}

	/**
	 * Concatanetes the paths in the toolbox applying each path the
	 * corresponding solution.
	 * 
	 * @param solutionParts
	 *            the solution part list.
	 * @param tools
	 *            the tool list.
	 * 
	 * @return the concatenated path.
	 */
	private IPath generateSolutionPath(ArrayList<ISolutionPart> solutionParts,
			ArrayList<ITool> tools) {

		IPath solutionPath = new HVPath();

		IPath pathTemp = null;

		for (int i = 0; i < solutionIndex.length; i++) {
			pathTemp = (IPath) solutionParts.get(solutionIndex[i]).apply(
					tools.get(i));
			if (pathTemp != null) {
				try {
				solutionPath.add(pathTemp);
				} catch (PathCrossesItSelf e) {
					compilationErrors += "* The generated solution is a self crossing path.\n";
				}
			}
			// System.out.println("Solution path is " +
			// solutionPath.toString());
		}
		return solutionPath;
	}

	/**
	 * Initializes the values in the <code>solutionIndex</code> array to -1
	 * depending on the number of tools in the toolbox.
	 * 
	 * @param toolSize
	 *            the number of tools in the toolbox.
	 */
	private void initSolutionIndex(int toolSize) {
		solutionIndex = new int[toolSize];
		for (int i = 0; i < toolSize; i++)
			solutionIndex[i] = -1;
	}

	/**
	 * Checks if the solution exists in each of the tools in the toolbox and
	 * there are no repetitive solutions.
	 * 
	 * @param solutionParts
	 * @param tools
	 */
	private void checkSolutionExists(ArrayList<ISolutionPart> solutionParts,
			ArrayList<ITool> tools) {

		initSolutionIndex(tools.size());
		int idTemp = 0;

		for (int i = 0; i < solutionParts.size(); i++) {
			idTemp = solutionParts.get(i).getID() - 1;
			if (solutionIndex[idTemp] != -1)
				compilationErrors += ("* Repeated solution for tool "
						+ (idTemp + 1) + ".\n");
			solutionIndex[idTemp] = i;
			checkEquivalent(idTemp, tools);
		}
		checkIfExistsForAll();
	}

	/**
	 * Checks if there exists a solution for each of the tools in the toolbox.
	 */
	private void checkIfExistsForAll() {
		for (int i = 0; i < solutionIndex.length; i++)
			if (solutionIndex[i] == -1)
				compilationErrors += ("* Solution for tool " + (i + 1) + " is missing.\n");
	}

	/**
	 * Fills the <code>solutionIndex</code> array for equivalent solutions and
	 * checks if there are repetative solutions for equivalent tools in the
	 * toolbox.
	 * 
	 * @param index
	 *            the index of the tool in the toolbox, for which the equivalent
	 *            tools are going to be searched in the toolbox.
	 * @param tools
	 *            the tools in the toolbox.
	 */
	private void checkEquivalent(int index, ArrayList<ITool> tools) {
		IPath path = (IPath) tools.get(index);
		for (int j = 0; j < tools.size(); j++) {
			if (index != j) {
				if (((IPath) tools.get(j)).isEquivalent(path)) {
					if (solutionIndex[j] != -1)
						compilationErrors += ("* Tool " + (index + 1)
								+ " and tool " + (j + 1) + " are equivalent. Only one solution must be added.\n");
					solutionIndex[j] = index;
				}
			}
		}
	}

	@Override
	public void display(IPuzzleGUIBuilder builder) {
		builder.buildSolutionbox(actionList);
		builder.buildCompilerMessageView(this);
	}
}
