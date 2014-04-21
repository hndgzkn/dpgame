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

public class ActionList implements Iterable<Action> {

	HashMap<String, Action> actions;

	public ActionList() {
		actions = new HashMap<String, Action>();
	}

	public void add(Action action) throws NullPointerException {
		if (action == null)
			throw new NullPointerException("Action cannot be null.");
		actions.put(action.getName(), action);
	}

	public Action get(String actionName) {
		return actions.get(actionName);
	}

	@Override
	public Iterator<Action> iterator() {
		return new ActionIterator(actions);
	}

}
