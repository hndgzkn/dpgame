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
package org.dpgame.puzzle.model.objects;



import java.awt.Color;


public class Fruit extends ABoardObject implements IPickable {
	
	public Fruit() {
		setColor(Color.RED);
		this.value = 'F';
	}

	@Override
	public IBoardObject getFootPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(IBoardObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean accept(IBoardObject object) {
		// TODO Auto-generated method stub
		return false;
	} 
}
