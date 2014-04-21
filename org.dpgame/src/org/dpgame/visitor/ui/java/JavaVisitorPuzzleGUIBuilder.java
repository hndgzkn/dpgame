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
package org.dpgame.visitor.ui.java;

import java.util.ArrayList;
import java.util.Observable;

import org.dpgame.main.ui.IPanel;
import org.dpgame.puzzle.controller.IPuzzleManager;
import org.dpgame.puzzle.model.components.ITool;
import org.dpgame.puzzle.ui.IBoardPanel;
import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;
import org.dpgame.puzzle.ui.ISolutionboxPanel;
import org.dpgame.puzzle.ui.IToolboxPanel;
import org.dpgame.puzzle.ui.java.BoardPanel;
import org.dpgame.puzzle.ui.java.CompilerPanel;
import org.dpgame.puzzle.ui.java.PuzzlePanel;
import org.dpgame.tools.parameters.ActionList;



/**
 * A GUI builder that implements {@link IPuzzleGUIBuilder} to generate a JAVA
 * GUI using AWT and SWING library to view the puzzle.
 * 
 * This builder can use a factory which will give the necessary panel depending
 * on the puzzle type.
 * 
 * @see IPuzzleGUIBuilder
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class JavaVisitorPuzzleGUIBuilder implements IPuzzleGUIBuilder {

	/**
	 * The panel that views the board.
	 */
	private IBoardPanel boardPanel;

	/**
	 * The panel that views the solution-box of the puzzle.
	 */
	private ISolutionboxPanel solutionboxPanel;

	/**
	 * The panel that views the tool-box of the puzzle.
	 */
	private IToolboxPanel toolboxPanel;

	/**
	 * The panel that views the results of the compilation.
	 */
	private CompilerPanel compilerPanel;

	/**
	 * The number of tools in the tool-box. It is used for the solution-box.
	 */
	private int numberOfTools = 0;

	/**
	 * The controller that establishes the communication between the model and
	 * the view.
	 */
	private IPuzzleManager controller;

	/**
	 * Constructor for the GUIBuilder to build a JAVA GUI.
	 * 
	 * @param controller
	 *            the controller that establishes the communication between the
	 *            model and the view.
	 * @throws NullPointerException
	 *             if the specified controller is <code>null</code>.
	 */
	public JavaVisitorPuzzleGUIBuilder(IPuzzleManager controller)
			throws NullPointerException {
		if (controller == null)
			throw new NullPointerException("The controller cannot be null.");
		this.controller = controller;
	}

	@Override
	public void buildBoard(int numberOfRows, int numberOfColumns) {
		boardPanel = new BoardPanel(numberOfRows, numberOfColumns);
	}

	@Override
	public void buildBoardComponent(Observable observable, int row, int column) {
		observable.addObserver(boardPanel.getTile(row, column));
	}

	@Override
	public void buildToolbox(ArrayList<ITool> tools) {
		numberOfTools = tools.size();
		toolboxPanel = new VisitorToolboxPanel(tools);
	}

	@Override
	public void buildSolutionbox(ActionList actionList) {
		solutionboxPanel = new VisitorSolutionboxPanel(controller, actionList,
				numberOfTools);
	}

	@Override
	public void buildCompilerMessageView(Observable observable) {
		compilerPanel = new CompilerPanel(controller);
		observable.addObserver(compilerPanel);
	}

	@Override
	public IPanel getGUI() {
		return (IPanel) new PuzzlePanel(boardPanel, toolboxPanel,
				solutionboxPanel, compilerPanel);
	}
}
