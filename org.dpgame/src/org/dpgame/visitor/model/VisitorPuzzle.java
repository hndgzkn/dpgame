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
package org.dpgame.visitor.model;

import java.util.ArrayList;

import org.dpgame.puzzle.model.IPuzzle;
import org.dpgame.puzzle.model.IPuzzleGeneratorBuilder;
import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.basics.ISquare;
import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.components.ISolution;
import org.dpgame.puzzle.model.components.ISolutionPart;
import org.dpgame.puzzle.model.components.IToolbox;
import org.dpgame.puzzle.model.objects.Catcher;
import org.dpgame.puzzle.model.objects.IMover;
import org.dpgame.puzzle.ui.IPuzzleGUIBuilder;
import org.dpgame.tools.exceptions.CompileException;
import org.dpgame.tools.exceptions.PathOutOfBoardException;
import org.dpgame.tools.exceptions.TargetMissedException;

/**
 * A puzzle class that extends {@link IPuzzle} class. This class deals with the
 * puzzles whose solution depend on using the visitor pattern.
 * <p>
 * A visitor puzzle is composed of a board (see {@link IBoard}) of squares (see
 * {@link ISquare}), a tool-box and a catcher (see {@link IMover},
 * {@link Catcher}) on the board.
 * 
 * 
 * The board is given with an initial state. There exists a catcher object on
 * the board and its goal is to reach the marked target on the board (see
 * {@link IBoard}). The tool-box (see {@link IToolbox}) contains the tools that
 * can be used in the solution-box to help the catcher reach the target. The
 * solution-box requires the application of the visitor pattern.
 * 
 * @see IPuzzleGeneratorBuilder
 * @see IBoard
 * @see IToolbox
 * @see IMover
 * @see ICatcher
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 */
public class VisitorPuzzle implements IPuzzle {

	/**
	 * The board of the puzzle.
	 */
	private IBoard<ISquare> board;

	/**
	 * The toolbox of the puzzle.
	 */
	private IToolbox toolbox;

	/**
	 * The object that tries to catch the target on the board.
	 */
	private IMover catcher;

	/**
	 * Constructor for visitor puzzle.
	 * @param board
	 * @param toolbox
	 * @param catcher
	 * @throws NullPointerException
	 */
	public VisitorPuzzle(IBoard<ISquare> board, IToolbox toolbox, IMover catcher)
			throws NullPointerException {
		if (board == null)
			throw new NullPointerException("Board cannot be null.");
		if (toolbox == null)
			throw new NullPointerException("Toolbox cannot be null.");
		if (catcher == null)
			throw new NullPointerException("Catcher cannot be null.");
		this.board = board;
		this.toolbox = toolbox;
		this.catcher = catcher;
	}

	@Override
	public boolean runSolution(ArrayList<ISolutionPart> solutionParts) {
		boolean solved = true;

		try {
			ISolution solution = toolbox.getSolution(solutionParts);
			catcher.moveAlong((IPath) solution);
		} catch (PathOutOfBoardException e) {
			solved = false;
			System.out.println("The path goes out of the bounds of the board.");
		} catch (TargetMissedException e) {
			solved = false;
			System.out.println("The target is missed.");
		} catch (CompileException e) {
			solved = false;
			System.out.println(e.getMessage());
		}

		if (solved)
			System.out.println("Caught!!"); // add something here to view this
											// in gui
		System.out.print("\n" + board.toString());
		return solved;
	}

	@Override
	public void display(IPuzzleGUIBuilder builder) {
		board.display(builder);
		for (int i = 0; i < board.getRowCount(); i++)
			for (int j = 0; j < board.getColumnCount(); j++)
				board.get(i, j).display(builder);
		toolbox.display(builder);
	}

}
