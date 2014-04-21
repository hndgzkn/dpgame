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

import org.dpgame.puzzle.model.IPuzzle;


/**
 * An interface for part of a solution (see {@link ISolution}) to the puzzle
 * (see IPuzzle). It is formed by the player using the puzzle GUI. The solution
 * is applied to the specified tool (see {@link ITool}) from the toolbox (see
 * {@link IToolbox}).
 * 
 * <p>
 * The solution parts has to be compiled by the solution compiler (see
 * {@link ISolutionCompiler}) to form the final solution.
 * 
 * <p>
 * The solution part specifies the id of the component that this solution has to
 * be applied.
 * 
 * @see ISolution
 * @see ISolutionCompiler
 * @see IPuzzle
 * @see ITool
 * @see IToolbox
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public interface ISolutionPart {

	/**
	 * Getter method for the ID of the tool that this solution will be applied to.
	 * 
	 * @return the id of the tool that this solution will be applied to.
	 */
	public int getID();

	/**
	 * Applies the solution to the specified tool and returns it.
	 * 
	 * @param tool
	 *            the tool that the solution will be applied to.
	 * @return the tool with the solution applied.
	 */
	public ITool apply(ITool tool);
}
