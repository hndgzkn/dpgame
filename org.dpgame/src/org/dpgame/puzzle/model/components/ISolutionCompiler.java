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
package org.dpgame.puzzle.model.components;

import java.util.ArrayList;

import org.dpgame.tools.exceptions.CompileException;

/**
 * An interface for a solution compiler that compiles the specified solution
 * parts with the specified tool set and generates a solution to solve the
 * puzzle. If the solution specified bu the player does not comply with the
 * design pattern requirements of the puzzle a compilation error is generated.
 * 
 * 
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public interface ISolutionCompiler extends IPuzzleComponent {

	/**
	 * Builds the solution from the specified solution parts using the given
	 * tools.
	 * 
	 * 
	 * @param solutionParts
	 *            the solution part list.
	 * @param tools
	 *            the tool list.
	 * @return the generated solution.
	 * @throws CompileException
	 *             if there exists compilation errors while compiling and
	 *             building the solution.
	 */
	public ISolution buildSolution(ArrayList<ISolutionPart> solutionParts,
			ArrayList<ITool> tools) throws CompileException;

}
