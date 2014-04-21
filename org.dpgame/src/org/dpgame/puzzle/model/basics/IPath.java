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

import org.dpgame.puzzle.model.components.ISolution;
import org.dpgame.puzzle.model.components.ITool;
import org.dpgame.tools.exceptions.PathCrossesItSelf;

/**
 * 
 * A path is a queue of steps.
 * 
 * <p>
 * A path is formed by connecting steps to a path or a path to another path.
 * <p>
 * A step cannot be connected to a path if it has the opposite direction with
 * the last step of the path.
 * <p>
 * A path B is connected to a path A, locating the first step of path B after
 * the last step of the path A. The connected path starts with the first step of
 * path A and ends with the last step of path B.
 * <p>
 * Two paths are equal if they have the same length and the step at index i in
 * one path is equal to the step at index i in the other path.
 * <p>
 * The length of a path is the number of steps it contains.
 * <p>
 * 
 * @see IStep
 * 
 * @author Hande Özaygen
 * @version 1.1.3
 * 
 */
public interface IPath extends Iterable<IStep>, ITool, ISolution {

	/**
	 * Setter for the id of the path.
	 * 
	 * @param id
	 *            the id of the path that identifies it in the toolbox.
	 *            Equivalent paths have equal ids.
	 */
	public void setID(int id);

	/**
	 * Getter for the id of the path.
	 * 
	 * @return the id of the path that identifies it in the toolbox. Equivalent
	 *         paths have equal ids.
	 */
	public int getID();

	/**
	 * Getter method for the length of the path.
	 * <p>
	 * The length of the path is the number of steps in the path.
	 * 
	 * @return the length of the path.
	 */
	public int getLength();

	/**
	 * Getter method for the first step of the path.
	 * 
	 * @return the first step of the path.
	 */
	public IStep getFirst();

	/**
	 * Getter method for the last step of the path.
	 * 
	 * @return the last step of the path.
	 */
	public IStep getLast();

	/**
	 * Adds the specified step to the end of the path.
	 * <p>
	 * The copy of the specified step is set as the last step of the path. The
	 * length of the path is increased by 1. If the next step of the specified
	 * step has also a next step it is not added.
	 * <p>
	 * 
	 * @param step
	 *            the step to be added to the end of the path.
	 * @throws IllegalArgumentException
	 *             if the specified step is null
	 * @throws PathCrossesItSelf
	 *             if the specified step has opposite direction with the last
	 *             step of this path.
	 */
	public void add(IStep step) throws IllegalArgumentException, PathCrossesItSelf;

	/**
	 * Adds the specified path to the end of this path.
	 * <p>
	 * The step pointed by the starting step of the specified path is set as
	 * next step to the last step of this path and the last step of the
	 * specified path is set as the last step of this path. The length of the
	 * path is increased by length of the specified path.
	 * <p>
	 * 
	 * @param path
	 *            the path to be added to the end of this path.
	 * @throws IllegalArgumentException
	 *             if the specified path is null
	 * @throws PathCrossesItSelf
	 *             if the specified path starts with a step that has opposite
	 *             direction with the last step of this path.
	 */
	public void add(IPath path) throws IllegalArgumentException,
			PathCrossesItSelf;

	/**
	 * Sets the color of the each step of the path as the specified color.
	 * 
	 * @param color
	 *            the color to be set to the path.
	 * @throws IllegalArgumentException
	 *             if the specified color is null.
	 */
	public void setColor(Color color) throws IllegalArgumentException;

	/**
	 * Creates an equivalent path of this path.
	 * <p>
	 * An equivalent path has the same number of steps in the same order, same
	 * direction and same color.
	 * 
	 * @return an equivalent of this path.
	 */
	public IPath getCopy();

	/**
	 * Rotates the path in counterclockwise direction.
	 */
	public void rotate();

	/**
	 * Checks if the specified path is equivalent to this path.
	 * 
	 * Two paths are equivalent if one is obtained by rotating the other.
	 * 
	 * @param path
	 *            the path to be compared for equivalance.
	 * @return true if the two paths are equivalent; false otherwise.
	 */
	public boolean isEquivalent(IPath path);

	/**
	 * Indicates whether the specified object is equal to this path.
	 * <p>
	 * The specified object is equal to this path if the object is an instance
	 * of {@link IPath} and it has the same length with this path and all the
	 * steps in this path and the specified object are equal.
	 * 
	 * @param o
	 *            the object to be compared for equality
	 * @return true if the specified object is equal to this path; false
	 *         otherwise.
	 */
	public boolean equals(Object o);

	/**
	 * Returns the string representation of the path.
	 * <p>
	 * It prints all the steps in the path in order starting from the first step
	 * of the path. Ex. (0,0)->(1,0)->(0,1)
	 * 
	 * @return the string representation of the path.
	 */
	public String toString();

}
