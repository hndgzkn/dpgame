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


/**
 * A step class that extends the {@link AStep} class.
 * 
 * @see AStep
 * 
 * @author Hande Özaygen
 * @version 1.1.2
 * 
 */
public class Step extends AStep {

	/**
	 * Default constructor for a step.
	 * <p>
	 * The horizontal and vertical directions are 0 and the color is light gray.
	 */
	public Step() {

	}

	/**
	 * Constructor for a step.
	 * <p>
	 * A step is initialized by specifying its horizontal and vertical
	 * directions.
	 * 
	 * @param hDirection
	 *            horizontal direction of the step
	 * @param vDirection
	 *            vertical direction of the step
	 * @throws IllegalArgumentException
	 *             if the specified horizontal or vertical direction is out of
	 *             the range from -1 up to 1.
	 */
	public Step(int horizontal, int vertical) throws IllegalArgumentException {
		if (horizontal > 1 || horizontal < -1 || vertical > 1 || vertical < -1)
			throw new IllegalArgumentException(
					"Directions must be in the range -1 up to 1");
		this.hDirection = horizontal;
		this.vDirection = vertical;
	}

	/**
	 * Constructor for a step.
	 * <p>
	 * A step is initialized by specifying its horizontal-vertical directions
	 * and its color.
	 * 
	 * @param hDirection
	 *            horizontal direction of the step
	 * @param vDirection
	 *            vertical direction of the step
	 * @param color
	 *            color of the step
	 * @throws IllegalArgumentException
	 *             if the specified horizontal or vertical direction is out of
	 *             the range from -1 up to 1, or if the specified color is null.
	 */
	public Step(int horizontal, int vertical, Color color)
			throws IllegalArgumentException {
		this(horizontal, vertical);
		if (color == null)
			throw new IllegalArgumentException("A null color cannot be set.");
		this.color = color;
	}

	@Override
	public IStep getCopy() {
		return new Step(this.getHDirection(), this.getVDirection(),
				this.getColor());
	}
}
