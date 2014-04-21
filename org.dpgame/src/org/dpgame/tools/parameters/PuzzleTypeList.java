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
package org.dpgame.tools.parameters;

import java.util.HashMap;
import java.util.Iterator;

public class PuzzleTypeList implements Iterable<PuzzleType> {

	HashMap<String, PuzzleType> map;

	public PuzzleTypeList() {
		map = new HashMap<String, PuzzleType>();
	}

	public void add(PuzzleType type) throws NullPointerException {
		if (type == null)
			throw new NullPointerException("Puzzle type cannot be null.");
		map.put(type.getPuzzleName(), type);
	}

	public PuzzleType get(String puzzleName) {
		return map.get(puzzleName);
	}

	@Override
	public Iterator<PuzzleType> iterator() {
		return new PuzzleTypeIterator(map);
	}
}
