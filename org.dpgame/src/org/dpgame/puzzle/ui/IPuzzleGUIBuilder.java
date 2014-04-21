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
package org.dpgame.puzzle.ui;

import java.util.ArrayList;
import java.util.Observable;

import org.dpgame.main.ui.IPanel;
import org.dpgame.puzzle.model.IPuzzle;
import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.components.ISolutionCompiler;
import org.dpgame.puzzle.model.components.ITool;
import org.dpgame.puzzle.model.components.IToolbox;
import org.dpgame.tools.parameters.ActionList;

/**
 * 
 * An interface for a GUI builder to view puzzle. The generated GUI has to be an
 * instance of {@link IPanel}.
 * <p>
 * A puzzle consists of a board (see {@link IBoard}) which is composed of puzzle
 * specific components, a tool-box (see {@link IToolbox}) with puzzle specific
 * tools (@see {@link ITool}) and a solution-box (@see {@link ISolutionCompiler}
 * ).
 * <p>
 * This GUI builder interface can be used implement different GUI builders for
 * different environments using the specific environment GUI libraries.
 * 
 * @see IPuzzle
 * @see IPanel
 * @see IBoard
 * @see IToolbox
 * @see ITool
 * @see ISolutionCompiler
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public interface IPuzzleGUIBuilder {

	/**
	 * Builds the GUI to display a two dimensional board.
	 * 
	 * @param numberOfRows
	 *            number of rows of the board.
	 * @param number
	 *            of columns of the board.
	 */
	public void buildBoard(int numberOfRows, int numberOfColumns);

	/**
	 * Builds the display of the components of the board.
	 * 
	 * @param observable
	 *            the object that will be displayed on the tile on the board.
	 *            This object is observable and updates the view each time a
	 *            change occurs.
	 * @param row
	 *            the row position of the observable object on the board.
	 * @param column
	 *            the column position of the observable object on the board.
	 */
	public void buildBoardComponent(Observable observable, int row, int column);

	/**
	 * Builds the display of the toolbox to show the tools in the toolbox.
	 * 
	 * @param tools
	 *            list of tools that are in the toolbox.
	 */
	public void buildToolbox(ArrayList<ITool> tools);

	/**
	 * Builds the display of solutionbox to let the player enter the solution
	 * for the puzzle.
	 * 
	 * @param actionList
	 */
	public void buildSolutionbox(ActionList actionList);

	/**
	 * Builds the display of message panel that views compiler messages.
	 * 
	 * @param observable
	 */
	public void buildCompilerMessageView(Observable observable);

	/**
	 * Returns the generated panel to view the puzzle.
	 * 
	 * @return the generated panel to view the puzzle.
	 */
	public IPanel getGUI();
}
