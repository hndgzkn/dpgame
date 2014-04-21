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

public class ToolParameterIterator implements Iterator<ToolParameters> {

	private HashMap<String, ToolParameters> tools;
	private Iterator<String> iterator;

	public ToolParameterIterator(HashMap<String, ToolParameters> map)
			throws NullPointerException {
		if (map == null)
			throw new NullPointerException("Action list cannot be null.");
		this.tools = map;
		iterator = tools.keySet().iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public ToolParameters next() {
		return tools.get(iterator.next());
	}

	@Override
	public void remove() {
		iterator.remove();
	}

}
