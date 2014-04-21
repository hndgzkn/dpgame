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

import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.components.ASolutionPart;
import org.dpgame.puzzle.model.components.ITool;


/**
 * A solution part which extends {@link ASolutionPart}. It applies the selected
 * solutions to paths (@see {@link IPath}).
 * 
 * @see ASolutionPart
 * @see IPath
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class VisitorSolutionPart extends ASolutionPart {

	/**
	 * The list of actions to be applied to the tool.
	 */
	private ArrayList<String> actions = new ArrayList<String>();

	/**
	 * Constructor for the partial solution applier to a path.
	 * 
	 * @param id
	 *            the id of the path.
	 * @param actions
	 *            the action list that will be applied to the specified path. If
	 *            it is null, no action is applied to the path.
	 */
	public VisitorSolutionPart(int id, ArrayList<String> actions) {
		this.id = id;
		this.actions = actions;
	}

	@Override
	public ITool apply(ITool tool) {
		ITool temp = tool;
		if (actions.isEmpty())
			return tool;
		if (actions.size() == 0)
			return tool;
		for (String s : actions) {
			if (s.equals("ignore"))
				return null;
			else if (s.equals("rotate (ccw)"))
				tool = rotate(tool);
			else if (s.equals("pick"))
				return pick(tool);
		}
		return tool;
	}

	/**
	 * Rotates the specified path.
	 * 
	 * @param tool
	 *            the path to be rotated.
	 * @return the rotated path.
	 */
	private ITool rotate(ITool tool) {
		IPath path = (IPath) tool;
		path.rotate();
		return (ITool) path;
	}

	/**
	 * Gives the path pick ability.
	 * 
	 * @param tool
	 *            the path that will have pick ability.
	 * @return the path with pick ability.
	 */
	private ITool pick(ITool tool) {
		IPath path = (IPath) tool;
		return path;
	}

	// private void drop(ITool tool) {
	//
	// }
	//
	// private void jump(ITool tool) {
	//
	// }
	//
	// private void push(ITool tool) {
	//
	// }

}
