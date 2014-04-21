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

import java.util.Iterator;

import org.dpgame.tools.exceptions.PathCrossesItSelf;

/**
 * A path class that extends {@link APath}.
 * 
 * <p>
 * An HV path can only contain steps which have either horizontal or vertical
 * direction. It cannot contain steps with diagonal directions. An HV path does
 * not cross itself.
 * <p>
 * 
 * @see APath
 * @see IPath
 * 
 * @author Hande Özaygen
 * @version 1.1.3
 * 
 */
public class HVPath extends APath {

	private int currentHorizontal = 0;

	private int currentVertical = 0;

	/**
	 * Constructor for a path.
	 * 
	 * Creates a path which has no steps.
	 */
	public HVPath() {
	}

	/**
	 * @Override The HVPath is in safe state if there exists no step with
	 *           diagonal direction inside the path.
	 */
	@Override
	public boolean invariant() {
		Iterator<IStep> iterator = iterator();
		while (iterator.hasNext())
			if (iterator.next().getVDirection() == iterator.next()
					.getHDirection())
				return false;
		return super.invariant();
	}

	/**
	 * @throws IllegalArgumentException
	 * @Override IllegalArgumentException if the specified step has diagonal
	 *           direction
	 * @throws PathCrossesItSelf
	 * @Override adding the specified step makes the path cross itself.
	 */
	@Override
	protected void canAdd(IStep step) throws IllegalArgumentException,
			PathCrossesItSelf {
		super.canAdd(step);

		if (step.getHDirection() == step.getVDirection())
			throw new IllegalArgumentException(
					"Cannot add a step with diagonal direction.");
		if (last != null && last.hasOppositeDirection(step))
			throw new PathCrossesItSelf(
					"The step to be added cannot have opposite direction with the last step of the path.");
		if (currentHorizontal + step.getHDirection() == 0
				&& currentVertical + step.getVDirection() == 0)
			throw new PathCrossesItSelf("Cannot add step " + step.toString()
					+ ". Causes path self cross.");
		checkCross(step);
		currentHorizontal += step.getHDirection();
		currentVertical += step.getVDirection();
	}

	/**
	 * Checks if the specified step causes the path to cross itself when added.
	 * 
	 * @param step
	 *            the step to be added to the path.
	 * @throws PathCrossesItSelf
	 *             if the step causes the path to cross itself when added.
	 */
	private void checkCross(IStep step) throws PathCrossesItSelf {
		int tempCurrentH = 0;
		int tempCurrentV = 0;
		IStep stepTemp = null;

		Iterator<IStep> iterator = this.iterator();
		while (iterator.hasNext()) {
			stepTemp = iterator.next();
			tempCurrentH += stepTemp.getHDirection();
			tempCurrentV += stepTemp.getVDirection();

			if (currentHorizontal + step.getHDirection() == tempCurrentH
					&& currentVertical + step.getVDirection() == tempCurrentV)
				throw new PathCrossesItSelf("Cannot add step "
						+ step.toString() + ". Causes path self cross.");
		}
	}

	/**
	 * @throws PathCrossesItSelf
	 * @Override if the specified path causes the path to cross itself.
	 */
	@Override
	protected void canAdd(IPath path) throws PathCrossesItSelf {
		super.canAdd(path);

		IPath pathTemp = this.getCopy();
		Iterator<IStep> iterator = path.iterator();
		while (iterator.hasNext())
				pathTemp.add(iterator.next());
	}

	@Override
	public IPath getCopy() {
		IPath path = new HVPath();
		Iterator<IStep> pathIterator = iterator();

		while (pathIterator.hasNext())
			path.add(pathIterator.next().getCopy());
		return path;
	}

}
