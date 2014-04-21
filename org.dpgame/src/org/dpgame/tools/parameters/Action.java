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

import java.util.ArrayList;

public class Action {

	private String name;

	private int maxNumberOfApplication;

	private int incrementOffset;

	private ArrayList<ActionObject> actionObjects;

	public Action(String name, int maxNumberOfApplication, int incrementOffset,
			ArrayList<ActionObject> actionObjects) {
		// this.name = name;
	}

	public Action() {
		actionObjects = new ArrayList<ActionObject>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxNumberOfApplication() {
		return maxNumberOfApplication;
	}

	public void setMaxNumberOfApplication(int maxNumberOfApplication) {
		this.maxNumberOfApplication = maxNumberOfApplication;
	}

	public int getIncrementOffset() {
		return incrementOffset;
	}

	public void setIncrementOffset(int incrementOffset) {
		this.incrementOffset = incrementOffset;
	}

	public ArrayList<ActionObject> getActionObjects() {
		return actionObjects;
	}

	public void addAction(ActionObject object) {
		actionObjects.add(object);
	}

	public void setActionObjects(ArrayList<ActionObject> actionObjects) {
		this.actionObjects = actionObjects;
	}

	// do this later
	// may be object.getcompatibleInterface
	// private ArrayList<String> preConditionAction;
}
