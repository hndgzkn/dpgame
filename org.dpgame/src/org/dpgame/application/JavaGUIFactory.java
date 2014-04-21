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
package org.dpgame.application;

import org.dpgame.main.controller.GameManager;
import org.dpgame.main.ui.IGUIFactory;
import org.dpgame.main.ui.IMainGUIBuilder;
import org.dpgame.main.ui.java.JavaMainGUIBuilder;
import org.dpgame.puzzle.controller.IPuzzleManager;
import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;
import org.dpgame.tools.xml.IXMLParser;
import org.dpgame.visitor.ui.java.JavaVisitorPuzzleGUIBuilder;

/**
 * 
 * @author Hande Özaygen
 * 
 */
public class JavaGUIFactory implements IGUIFactory {

	private static JavaGUIFactory factory = new JavaGUIFactory();

	private JavaGUIFactory() {

	}

	public static JavaGUIFactory getInstance() {
		return factory;
	};

	@Override
	public IMainGUIBuilder getGUIBuilder(GameManager controller, IXMLParser xmlParser) {
		return new JavaMainGUIBuilder(controller, xmlParser);
	}

	@Override
	public IPuzzleGUIBuilder getPuzzleGUIBuilder(IPuzzleManager controller,
			String type) {
		// this should change to use reflections
		if (type.equals("Visitor Pattern"))
			return new JavaVisitorPuzzleGUIBuilder(controller);
		return null;
	}
}
