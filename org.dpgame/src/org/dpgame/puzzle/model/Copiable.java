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
package org.dpgame.puzzle.model;

/**
 * An interface for copiable classes. Any class that allows copying the instance
 * should implement this interface.
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 * @param <E>
 *            Type of instance to return after copying.
 */
public interface Copiable<E> {

	/**
	 * Creates a copy of this object and returns it.
	 *  
	 * All the objects that are pointed by this object are also copied. So there are
	 * no common references with this instance and the copied instance.
	 * 
	 * @return the copy of this instance.
	 */
	public E getCopy();
}
