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
package org.dpgame.puzzle.model.basics;

import org.dpgame.puzzle.model.IColorable;
import org.dpgame.puzzle.model.basics.actions.IMoveCommand;
import org.dpgame.puzzle.model.basics.actions.MoveInvoker;
import org.dpgame.puzzle.model.components.IBoard;

/**
 * 
 * A step is the basic unit of movement on the board (see {@link IBoard}).
 * <p>
 * A step has a direction indicating the direction of movement.
 * <p>
 * Every step specifies its direction in two coordinates (x, y) where -1 <= x <=
 * 1 specifies the horizontal direction and -1 <= y <= 1 specifies the vertical
 * direction.
 * <p>
 * (0,0) means step has no direction; ie there is no movement.
 * <p>
 * A step has a pointer to next step.
 * <p>
 * A step has color.
 * 
 * 
 * @see IBoard
 * @see IColorable
 * 
 * @author Hande Özaygen
 * @version 1.1.4
 * 
 */
public interface IStep extends IColorable {

	/**
	 * Getter method for the horizontal direction of the step. It can have
	 * values -1, 0, 1.
	 * 
	 * @return the horizontal direction of the step.
	 */
	public int getHDirection();

	/**
	 * Getter method for the vertical direction of the step. It can have values
	 * -1, 0, 1.
	 * 
	 * @return the vertical direction of the step.
	 */
	public int getVDirection();

	/**
	 * Checks if the specified step has opposite direction with this step.
	 * 
	 * @param step
	 *            the step to be compared for direction.
	 * @return true if the specified step has opposite direction with this step;
	 *         false otherwise.
	 * @throws IllegalArgumentException
	 *             if the specified step is <code>null</code>.
	 */
	public boolean hasOppositeDirection(IStep step)
			throws IllegalArgumentException;

	/**
	 * Getter method for the next step.
	 * 
	 * @return the next step.
	 */
	public IStep getNext();

	/**
	 * Setter method for the next step.
	 * <p>
	 * A copy of the specified step with horizontal-vertical directions and
	 * color is created and set as next step.
	 * 
	 * @param next
	 *            the next step.
	 */
	public void setNext(IStep next);

	/**
	 * Creates a new step with the same color and horizontal-vertical directions
	 * with this step and returns it.
	 * 
	 * @return a new step with the same color and horizontal-vertical directions
	 *         with this step.
	 */
	public IStep getCopy();

	/**
	 * Adds the specified move command to the step. The object that uses this
	 * step will execute the specified command.
	 * 
	 * @param command
	 *            the command that the object that uses this step will execute.
	 * @throws NullPointerException
	 *             if the specified command is null.
	 */
	public void addMoveCommand(IMoveCommand command)
			throws NullPointerException;

	/**
	 * Getter method for the move invoker that all the move commands on this
	 * step are stored.
	 * 
	 * @return the move invoker that all the move commands on this step are
	 *         stored.
	 */
	public MoveInvoker getMoveInvoker();

	/**
	 * Indicates whether the specified object is equal to this step.
	 * <p>
	 * The specified object is equal to this step if the object is an instance
	 * of {@link IStep}, it has the same horizontal-vertical directions and the
	 * same color with this step.
	 * 
	 * @param o
	 *            the object to be compared for equality
	 * @return true if the specified object is equal to this step; false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object o);

	/**
	 * Checks if the specified step is equivalent to this step.
	 * 
	 * Two step are equivalent if one is obtained by rotating the other.
	 * 
	 * @param step
	 *            the step to be compared for equivalence.
	 * @return true if the two steps are equivalent; false otherwise.
	 */
	public boolean isEquivalent(IStep step);

	/**
	 * Returns the string representation of the step.
	 * <p>
	 * For a step of direction (1,0) returns (1,0). It does not include the next
	 * step.
	 * 
	 * @return the string representation of the step.
	 */
	@Override
	public String toString();

	/**
	 * Returns the string representation of the step including the next step.
	 * <p>
	 * For a step of direction (1,0) whose next step is null returns (1,0). If
	 * the next step is not null, for example if the direction of the next step
	 * is (0,-1), returns (1,0)->(0,-1).
	 * 
	 * @return the string representation of the step including the next step.
	 */
	public String toStringWithNext();

	/**
	 * Rotates the step in counterclockwise direction.
	 */
	public void rotate();

}
