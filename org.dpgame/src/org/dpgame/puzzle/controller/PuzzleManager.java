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
package org.dpgame.puzzle.controller;

import java.util.ArrayList;
import java.util.Random;

import org.dpgame.main.ui.IGUIFactory;
import org.dpgame.main.ui.IMainGUIBuilder;
import org.dpgame.puzzle.model.IPuzzle;
import org.dpgame.puzzle.model.IPuzzleGenerator;
import org.dpgame.puzzle.model.IPuzzleGeneratorBuilder;
import org.dpgame.puzzle.model.components.ISolutionPart;
import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;

/**
 * 
 * @author Hande Özaygen
 * 
 */
public class PuzzleManager implements IPuzzleManager {

	/**
	 * The GUI builder.
	 */
	private IPuzzleGUIBuilder guiBuilder;

	/**
	 * Model.
	 */
	private IPuzzle puzzle = null;

	/**
	 * Random generator to be used to generate the puzzle.
	 */
	private Random rng;

	private IMainGUIBuilder mainGUIBuilder;

	private IPuzzleGenerator puzzleGenerator;

	/**
	 * Builder used to build the puzzle.
	 */
	private IPuzzleGeneratorBuilder puzzleGeneratorBuilder;

	private IGUIFactory guiFactory;

	/**
	 * Constructor of the main controller.
	 */
	public PuzzleManager(IGUIFactory guiFactory,
			IMainGUIBuilder mainGUIBuilder) {
		if (guiFactory == null)
			throw new NullPointerException("GUI factory cannot be null.");
		if (this.rng == null)
			this.rng = new Random();
		if (mainGUIBuilder == null)
			throw new NullPointerException("Main controller cannot be null.");
		this.mainGUIBuilder = mainGUIBuilder;
		this.guiFactory = guiFactory;
	}

	@Override
	public void setSelectedPuzzle(IPuzzleGeneratorBuilder puzzleGeneratorBuilder, String puzzleType, int level) {
		this.puzzleGeneratorBuilder = puzzleGeneratorBuilder;
		guiBuilder = guiFactory.getPuzzleGUIBuilder(this, puzzleType);
		constructNewPuzzle();
	}

	@Override
	public void constructNewPuzzle() {
		puzzleGeneratorBuilder.buildBoardParameters();
		puzzleGeneratorBuilder.buildActionList();
		puzzleGeneratorBuilder.buildToolboxParameters();
		puzzleGeneratorBuilder.buildBoardObjects();

		puzzleGenerator = puzzleGeneratorBuilder.getPuzzleGenerator();
		puzzle = puzzleGenerator.generatePuzzle();
		displayPuzzle();
	}

	private void displayCurrentPuzzle() {
		puzzle = puzzleGenerator.getCurrentPuzzle();
		displayPuzzle();
	}

	/**
	 * Displays the current puzzle inside the puzzle manager.
	 */
	private void displayPuzzle() {
		puzzle.display(guiBuilder);
		mainGUIBuilder.showPuzzleView(guiBuilder.getGUI());
	}

	@Override
	public void viewMenu() {
		mainGUIBuilder.showPuzzleSelectView();
	}

	@Override
	public void viewHelp(String url) {
		mainGUIBuilder.showHelpView(url);
	}

	@Override
	public void run(ArrayList<ISolutionPart> solutionParts) {
		boolean successful = puzzle.runSolution(solutionParts);
		// if (successful)
		// constructNewPuzzle();
		// else
		// displayCurrentPuzzle();
	}
}
