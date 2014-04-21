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



import java.util.NoSuchElementException;


/**
 * An iterator to iterate through the steps (see {@link IStep}) of the path (see
 * {@link IPath}) starting from the first step in forward direction.
 * <p>
 * 
 * @see java.util.Iterator
 * @see IStep
 * @see IPath
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public class PathIterator implements java.util.Iterator<IStep> {

	/**
	 * Path to iterate through the elements.
	 */
	private IPath path;

	/**
	 * The current step.
	 */
	private IStep current = null;

	/**
	 * Constructor for the forward path iterator.
	 * 
	 * @param path
	 *            the path that this iterator iterates over.
	 * @throws IllegalArgumentException
	 *             if the specified path is null.
	 */
	public PathIterator(IPath path) throws IllegalArgumentException {
		if (path == null)
			throw new IllegalArgumentException("Cannot iterate a null path.");
		this.path = path;
	}

	@Override
	public boolean hasNext() {
		return current != path.getLast();
	}

	@Override
	public IStep next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException(
					"There is no more step in the path.");

		if (current == null)
			current = path.getFirst();
		else
			current = current.getNext();
		return current;
	}

	/**
	 * This method is not implemented. It is not possible to remove a step from
	 * the path once added.
	 */
	@Override
	public void remove() {

	}

}
