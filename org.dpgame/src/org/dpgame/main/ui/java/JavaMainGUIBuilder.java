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
package org.dpgame.main.ui.java;

import java.awt.Dimension;

import org.dpgame.main.controller.GameManager;
import org.dpgame.main.ui.IMainGUIBuilder;
import org.dpgame.main.ui.IPanel;
import org.dpgame.puzzle.ui.java.PuzzlePanel;
import org.dpgame.tools.parameters.PuzzleTypeList;
import org.dpgame.tools.xml.IXMLParser;



/**
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class JavaMainGUIBuilder implements IMainGUIBuilder {

	private MainFrame mainframe;
	/**
	 * The main controller of the game. It is passed as an argument to the
	 * constructor of each {@link IPanel}.
	 */
	private GameManager controller;

	/**
	 * The panel that holds the menu panel and corresponding selected panels.
	 */
	private SplitPanel startPanel;

	private HTMLPanel welcomePanel;

	private HTMLPanel helpPanel;

	private SplitPanel puzzleSelectPanel;
	private IXMLParser xmlParser;

	public JavaMainGUIBuilder(GameManager controller, IXMLParser xmlParser)
			throws NullPointerException {
		if (controller == null)
			throw new NullPointerException("The controller cannot be null.");
		if (xmlParser == null)
			throw new NullPointerException("The XML parser cannot be null.");
		this.controller = controller;
		this.xmlParser = xmlParser;
			mainframe = new MainFrame();
	}

	/**
	 * Builds the {@link IPanel} which is a split panel that displays main menu
	 * on the left and behaves as a container for all the views that are
	 * navigated from main menu. It is not a container for {@link PuzzlePanel}.
	 */
	private void buildStartPanel() {
		if (startPanel == null) {
			startPanel = new SplitPanel(controller);
			startPanel.setPanelSize(new Dimension(500, 280));
			MenuPanel menuPanel = new MenuPanel(controller);
			startPanel.setLeftPane(menuPanel, 140);
		}
	}

	@Override
	public void showWelcomeView(String htmlText) {
		if (startPanel == null)
			buildStartPanel();
		if (welcomePanel == null)
			welcomePanel = new HTMLPanel(controller, htmlText);
		startPanel.setRightPane(welcomePanel, 140);
		mainframe.add((IPanel) startPanel);
	}

	@Override
	public void showScoreView() {
	}

	@Override
	public void showHelpView(String htmlText) {
		if (startPanel == null)
			buildStartPanel();
		if (helpPanel == null)
			helpPanel = new HTMLPanel(controller, htmlText);
		startPanel.setRightPane(helpPanel, 140);
		mainframe.add((IPanel) startPanel);
	}

	@Override
	public void showPuzzleSelectView() {
		if (startPanel == null)
			buildStartPanel();
		if (puzzleSelectPanel == null) {
			puzzleSelectPanel = new SplitPanel(controller);
			PuzzleTypeList list = xmlParser.getTypes();
			LevelPanel levelPanel = new LevelPanel(controller, list);
			SelectPanel selectPanel = new SelectPanel(controller, list,
					levelPanel);
			puzzleSelectPanel.setPanelSize(new Dimension(350, 250));
			puzzleSelectPanel.setLeftPane(selectPanel, 220);
			puzzleSelectPanel.setRightPane(levelPanel, 220);
		}
		startPanel.setRightPane(puzzleSelectPanel, 140);
		mainframe.add((IPanel) startPanel);
	}
	
	public void showPuzzleView(IPanel puzzlePanel) {
		mainframe.add((IPanel) puzzlePanel);
	}

}
