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

import org.dpgame.puzzle.model.components.ISolution;
import org.dpgame.puzzle.model.components.ISolutionCompiler;
import org.dpgame.puzzle.model.components.ISolutionPart;
import org.dpgame.puzzle.model.components.ITool;
import org.dpgame.puzzle.model.components.IToolbox;
import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;

/**
 * A tool-box class that extends the {@link IToolbox} class.
 * 
 * @see IToolbox
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public class VisitorToolbox implements IToolbox {

	/**
	 * The list of paths that will be used as tools to build the solution.
	 */
	private ArrayList<ITool> pathCollection;

	/**
	 * The solution builder that will apply the actions defined by player to the
	 * tools in the toolbox and return the solution to be executed.
	 */
	private ISolutionCompiler solutionCompiler;

	/**
	 * Constructor for the toolbox.
	 */
	public VisitorToolbox(ArrayList<ITool> tools,
			ISolutionCompiler solutionBuilder) {
		this.pathCollection = tools;
		this.solutionCompiler = solutionBuilder;
	}

	@Override
	public ISolution getSolution(ArrayList<ISolutionPart> solutionParts) {
		return solutionCompiler.buildSolution(solutionParts, pathCollection);
	}

	@Override
	public void display(IPuzzleGUIBuilder builder) {
		builder.buildToolbox(pathCollection);
		solutionCompiler.display(builder);
	}
}
