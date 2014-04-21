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

import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;

/**
 * An interface for a GUI builder which builds the main menu views of the game.
 * Puzzle view is not built with this builder. It is built by
 * {@link IPuzzleGUIBuilder}.
 * 
 * 
 * 
 * @see IPuzzleGUIBuilder
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public interface IMainGUIBuilder {

	/**
	 * Builds the {@link IPanel} that is displayed when the game is executed.
	 * @param htmlText TODO
	 */
	public void showWelcomeView(String htmlText);

	/**
	 * Builds the {@link IPanel} that displays the scores for all saved players.
	 */
	public void showScoreView();

	/**
	 * Builds the {@link IPanel} that displays the game help.
	 * @param htmlText TODO
	 */
	public void showHelpView(String url);

	/**
	 * Builds the {@link IPanel} that displays the puzzle types and
	 * corresponding levels.
	 */
	public void showPuzzleSelectView();

	public void showPuzzleView(IPanel puzzlePanel);
}
