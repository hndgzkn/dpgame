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
package org.dpgame.tools.exceptions;

import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.basics.IStep;
import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.objects.IBoardObject;

/**
 * Exception to be thrown when a specified path (see {@link IPath})
 * executed from the staring point on board (see
 * {@link IBoardObject}) reaches the target but the current step
 * (see {@link IStep}) is not the last step.
 * 
 * @see IPath
 * @see IBoard
 * @see IStep
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class TargetMissedException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the exception.
	 */
	public TargetMissedException() {
		super();
	}

	/**
	 * Constructor to initialize the exception with the related message.
	 * 
	 * @param message
	 *            exception related message.
	 */
	public TargetMissedException(String message) {
		super(message);
	}

}
