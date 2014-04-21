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

public class ActionObject {

	private String type;
	private String preconditionAction;
	private String preconditionType;
	
	public ActionObject(String type, String preconditionAction, String preconditionType) {
		this.type = type;
		this.preconditionAction = preconditionAction;
		this.preconditionType = preconditionType;
	}
	public ActionObject() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPreconditionAction() {
		return preconditionAction;
	}

	public void setPreconditionAction(String preconditionAction) {
		this.preconditionAction = preconditionAction;
	}

	public String getPreconditionType() {
		return preconditionType;
	}

	public void setPreconditionType(String preconditionType) {
		this.preconditionType = preconditionType;
	}
}
