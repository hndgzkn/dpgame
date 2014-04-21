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
package org.dpgame.puzzle.model.objects;

import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.components.IBoard;

/**
 * An interface for an object that can move along a path (see {@link IPath}) on
 * the board (see {@link IBoard}).
 * 
 * @see IPath
 * @see IBoard
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public interface IMover {

	/**
	 * Moves following the specified path from its current position on the
	 * board.
	 * 
	 * @param path
	 *            the path to follow.
	 * @throws IllegalArgumentException
	 *             if the specified path is null.
	 */
	public void moveAlong(IPath path) throws IllegalArgumentException;

}
