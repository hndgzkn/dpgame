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
package org.dpgame.main.ui;

import org.dpgame.main.controller.GameManager;
import org.dpgame.puzzle.controller.IPuzzleManager;
import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;
import org.dpgame.tools.xml.IXMLParser;

/**
 * An interface of a factory of GUI builders of the game. GUI is basically
 * constructed using
 * <ul>
 * <li>{@link IMainGUIBuilder}</li>
 * <li>{@link IPuzzleGUIBuilder}</li>
 * </ul>
 * 
 * @see IMainGUIBuilder
 * @see IPuzzleGUIBuilder
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public interface IGUIFactory {

	/**
	 * Getter method for the main GUI builder of the game.
	 * 
	 * @param controller
	 * @return
	 * 
	 * @see IMainGUIBuilder
	 */
	public IMainGUIBuilder getGUIBuilder(GameManager controller, IXMLParser xmlParser);

	/**
	 * Getter method for the puzzle GUI builder of the game.
	 * @param controller
	 * @param type TODO
	 * @param parser
	 * @return
	 * 
	 * @see IPuzzleGUIBuilder
	 */
	public IPuzzleGUIBuilder getPuzzleGUIBuilder(IPuzzleManager controller, String type);

}
