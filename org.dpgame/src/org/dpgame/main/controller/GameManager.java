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
package org.dpgame.main.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.dpgame.main.ui.IGUIFactory;
import org.dpgame.main.ui.IMainGUIBuilder;
import org.dpgame.puzzle.controller.IPuzzleManager;
import org.dpgame.puzzle.controller.PuzzleManager;
import org.dpgame.puzzle.model.Help;
import org.dpgame.puzzle.model.PuzzleToolFactory;
import org.dpgame.tools.xml.IXMLParser;
import org.dpgame.tools.xml.dom.GameXMLParser;
import org.xml.sax.SAXException;


/**
 * The main controller of the program which provides communication between the
 * model and the view. It also is responsible for managing the transitions
 * between views.
 * <p>
 * 
 * @author Hande Özaygen
 * @version 1.1.2
 * 
 */
public class GameManager {

	/**
	 * The GUI factory that this controller takes its GUI Builders and GUI
	 * components.
	 */
	private IGUIFactory guiFactory;

	/**
	 * The controller for puzzle view to control navigation in the puzzle
	 * generation and view.
	 */
	private IPuzzleManager puzzleManager = null;

	/**
	 * The GUI Builder that is responsible for generating GUI related to main
	 * menu navigations. (All menu navigations except the ones on puzzle)
	 */
	private IMainGUIBuilder guiBuilder;

	/**
	 * The xml file that contains types and levels of puzzles and parameters to
	 * build a random puzzle for each type and each level.
	 */
	private String gameXMLFile = "resources/game.xml";

	/**
	 * The XML parser that parses the <code>gameXMLFile</code> file.
	 */
	private IXMLParser xmlParser = null;

	/**
	 * Constructor of the main controller. Main controller is initiated with the
	 * type of GUIBuilder, so that the constructed GUI comply with the specified
	 * GUI library.
	 * 
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws NullPointerException
	 *             if the specified GUIFactory is <code>null</code>.
	 */
	public GameManager(IGUIFactory guiFactory) {
		if (guiFactory == null)
			throw new NullPointerException("GUI factory cannot be null.");
		this.guiFactory = guiFactory;
		// initializes the GameXMLParser that will parse the game configuration
		// XML.
		try {
			xmlParser = new GameXMLParser(gameXMLFile);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiBuilder = guiFactory.getGUIBuilder(this, xmlParser);
	}

	/**
	 * Starts the game and displays the main game GUI.
	 */
	public void start() {
		guiBuilder.showWelcomeView(Help.WELCOME);
	}

	public void showPuzzleSelectView() {
		guiBuilder.showPuzzleSelectView();
	}

	public void showScoreView() {
		guiBuilder.showScoreView();
	}

	/**
	 * Shows the game help which gives instructions on how to play the game.
	 */
	public void showHelpView(String htmlText) {
		guiBuilder.showHelpView(htmlText);
	}

	public void selectPuzzle(String puzzleType, int level) {
		if (puzzleManager == null)
			puzzleManager = new PuzzleManager(guiFactory, guiBuilder);
		
		xmlParser.setSelectedPuzzle(puzzleType, level);
		puzzleManager.setSelectedPuzzle(PuzzleToolFactory.getInstance().getPuzzleBuilder(
				puzzleType, xmlParser), puzzleType, level);
		
	}

	/**
	 * Closes the game main frame and exits game.
	 */
	public void exit() {
		System.exit(0);
	}
	
}
