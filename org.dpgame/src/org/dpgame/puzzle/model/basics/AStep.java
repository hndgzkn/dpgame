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

import java.awt.Color;

import org.dpgame.puzzle.model.basics.actions.IMoveCommand;
import org.dpgame.puzzle.model.basics.actions.MoveInvoker;

/**
 * An abstract class that implements {@link IStep}.
 * 
 * {@link #setNext(IStep)} method copies the specified step to avoid cross
 * references.
 * 
 * @see IStep
 * 
 * @author Hande Özaygen
 * @version 1.1.3
 * 
 */
public abstract class AStep implements IStep {

	/**
	 * Horizontal direction of the step. It can have values -1, 0, 1. The
	 * default value is 0.
	 */
	protected int hDirection = 0;

	/**
	 * Vertical direction of the step. It can have values -1, 0, 1. The default
	 * value is 0.
	 */
	protected int vDirection = 0;

	/**
	 * The color of the step. The default color is lightGray.
	 */
	protected Color color = Color.lightGray;

	/**
	 * The next step. Cannot be the step itself.
	 */
	private IStep next = null;

	/**
	 * The move invoker that all the move commands on this step are stored.
	 */
	private MoveInvoker moveInvoker;

	/**
	 * The step is in safe state if:
	 * <ul>
	 * <li>-1 <= hDirection <= 1</li>
	 * <li>-1 <= vDirection <= 1</li>
	 * </ul>
	 * 
	 * This method does not have 100% coverage in unit tests. It is due to the
	 * fact that it is not possible to initialize a step with either horizontal
	 * or vertical direction outside the range from -1 up to 1.
	 */
	public boolean invariant() {
		return (hDirection <= 1 && hDirection >= -1 && vDirection <= 1 && vDirection >= -1);
	}

	@Override
	public int getHDirection() {
		return hDirection;
	}

	@Override
	public int getVDirection() {
		return vDirection;
	}

	@Override
	public boolean hasOppositeDirection(IStep step)
			throws IllegalArgumentException {
		if (step == null)
			throw new IllegalArgumentException(
					"A null step cannot be compared.");
		return (!(getHDirection() == 0 && getVDirection() == 0) && ((getHDirection() == -1
				* step.getHDirection()) && (getVDirection() == -1
				* step.getVDirection())));
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) throws IllegalArgumentException {
		if (color == null)
			throw new IllegalArgumentException("A null color cannot be set.");
		this.color = color;
	}

	@Override
	public void addMoveCommand(IMoveCommand command)
			throws NullPointerException {
		if (command == null)
			throw new NullPointerException("The command cannot be null.");
		moveInvoker.addCommand(command);
	}

	@Override
	public MoveInvoker getMoveInvoker() {
		return moveInvoker;
	}

	@Override
	public IStep getNext() {
		return next;
	}

	@Override
	public void setNext(IStep next) {
		if (next != null)
			this.next = next.getCopy();
		else
			this.next = null;
	}

	@Override
	public void rotate() {
		int directionTemp = hDirection;
		hDirection = -1 * vDirection;
		vDirection = directionTemp;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof IStep))
			return false;
		IStep step = (IStep) o;
		if (getHDirection() != step.getHDirection()
				|| getVDirection() != step.getVDirection()
				|| getColor() != step.getColor())
			return false;
		return true;
	}

	@Override
	public boolean isEquivalent(IStep step) {
		if (step == null)
			return false;
		IStep stepTemp = step.getCopy();
		for (int i = 0; i < 4; i++) {
			if (getHDirection() == stepTemp.getHDirection()
					&& getVDirection() == stepTemp.getVDirection()) {
				return true;
			}
			stepTemp.rotate();
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + getHDirection() + "," + getVDirection() + ")";
	}

	@Override
	public String toStringWithNext() {
		String str = toString() + " -> ";
		if (getNext() != null)
			str += getNext().toString();
		return str;
	}
}
