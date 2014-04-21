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
import java.util.Iterator;

import org.dpgame.tools.exceptions.PathCrossesItSelf;

/**
 * An abstract path that implements {@link IPath} interface.
 * 
 * @see IPath
 * 
 * @author Hande Özaygen
 * @version 1.1.3
 * 
 */
public abstract class APath implements IPath {
	/**
	 * The number of steps in the path.
	 */
	protected int length = 0;

	/**
	 * The first step of the path.
	 */
	protected IStep first = null;

	/**
	 * The last step of the path.
	 */
	protected IStep last = null;

	/**
	 * The id of the path.
	 */
	private int id;

	@Override
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public int getID() {
		return id;
	}

	/**
	 * The path is in safe state if:
	 * <ul>
	 * <li>The number of steps in the path is equal to the length of the path.</li>
	 * <li>There are no consecutive steps which have opposite directions.</li>
	 * </ul>
	 */
	public boolean invariant() {
		int length_temp = 0;

		Iterator<IStep> iterator = iterator();
		IStep current = null;
		IStep next = null;

		while (iterator.hasNext()) {
			length_temp++;
			current = iterator.next();
			try {
				if (current.hasOppositeDirection(next))
					return false;
			} catch (IllegalArgumentException ex) {
			}
			next = current;
		}

		if (length_temp != length)
			return false;
		return true;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public IStep getFirst() {
		return first;
	}

	@Override
	public IStep getLast() {
		return last;
	}

	@Override
	public Iterator<IStep> iterator() {
		return new PathIterator(this);
	}

	@Override
	public void add(IStep step) throws IllegalArgumentException,
			PathCrossesItSelf {
		canAdd(step);
		if (first != null) {
			last.setNext(step);
			last = last.getNext();
		} else {
			first = step.getCopy();
			last = first;
		}
		length++;
	}

	/**
	 * Checks if the specified step can be added to this path.
	 * 
	 * @throws IllegalArgumentException
	 *             if the specified step is null.
	 */
	protected void canAdd(IStep step) throws IllegalArgumentException {
		if (step == null)
			throw new IllegalArgumentException(
					"A null step cannot be added to a path.");
	}

	@Override
	public void add(IPath path) throws IllegalArgumentException,
			PathCrossesItSelf {
		canAdd(path);
		for (IStep step : path)
			add(step);
	}

	/**
	 * Checks if the specified path can be added to the end of this path.
	 * 
	 * @throws IllegalArgumentException
	 *             if the specified path is null.
	 */
	protected void canAdd(IPath path) throws IllegalArgumentException {
		if (path == null)
			throw new IllegalArgumentException(
					"A null path cannot be added to a path.");
	}

	/**
	 * @override The color that is set is set for the steps that the path
	 *           contains at that moment. A new step with a different color can
	 *           be added after the path is colored.
	 */
	@Override
	public void setColor(Color color) throws IllegalArgumentException {
		if (color == null)
			throw new IllegalArgumentException("Cannot set a null color.");
		for (IStep step : this)
			step.setColor(color);
	}

	@Override
	public void rotate() {
		for (IStep step : this)
			step.rotate();
	}

	@Override
	public boolean isEquivalent(IPath path) {
		if (path == null || path.getLength() != getLength())
			return false;
		IPath pathTemp = path.getCopy();
		Iterator<IStep> thisIterator = null;

		boolean isEquivalent = false;
		for (int i = 0; i < 4; i++) {
			isEquivalent = true;
			thisIterator = iterator();
			for (IStep step : pathTemp) {
				if (!step.isEquivalent(thisIterator.next()))
					isEquivalent = false;
			}
			if (isEquivalent)
				return true;
			pathTemp.rotate();
		}
		return isEquivalent;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof IPath))
			return false;
		IPath path = (IPath) o;

		if (path.getLength() != getLength())
			return false;

		Iterator<IStep> iteratorThis = iterator();

		for (IStep step : path)
			if (!step.equals(iteratorThis.next()))
				return false;
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		Iterator<IStep> iterator = iterator();
		while (iterator.hasNext()) {
			str += iterator.next().toString();
			if (iterator.hasNext())
				str += " -> ";
		}
		return str;
	}
}
