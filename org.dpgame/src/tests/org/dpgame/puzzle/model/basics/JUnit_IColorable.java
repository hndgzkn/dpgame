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
package tests.org.dpgame.puzzle.model.basics;

import org.dpgame.puzzle.model.IColorable;
import org.junit.Test;


/**
 * An abstract class for a JUnit test to test {@link IColorable} interface.
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public abstract class JUnit_IColorable {

	/**
	 * Tests the {@link IColorable#getColor()} method.
	 */
	@Test
	public abstract void testGetColor();

	/**
	 * Tests the {@link IColorable#setColor()} method.
	 */
	@Test
	public abstract void testSetColor();

	/**
	 * Tests the {@link IColorable#getColor()} method for exception.
	 * 
	 * Tests for the case where the color set is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public abstract void testSetColor_Exception1();
}