/* Copyright (C) 2014 Hande Özaygen
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
package org.dpgame.puzzle.model.objects;

import java.awt.Image;

/**
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class BoardObject extends ABoardObject {

	public BoardObject(Image image, char value) throws NullPointerException {
//		if (image == null)
//			throw new NullPointerException("Image cannot be null.");
		this.image = image;
		this.value = value;
	}

	@Override
	public boolean accept(IBoardObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IBoardObject getFootPrint() {
		return null;
	}

	@Override
	public void add(IBoardObject object) {
	}

}
