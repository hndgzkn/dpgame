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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.dpgame.puzzle.model.IPuzzle;
import org.dpgame.puzzle.model.IPuzzleGenerator;
import org.dpgame.puzzle.model.basics.HVPath;
import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.basics.ISquare;
import org.dpgame.puzzle.model.basics.IStep;
import org.dpgame.puzzle.model.basics.Square;
import org.dpgame.puzzle.model.basics.Step;
import org.dpgame.puzzle.model.components.Board;
import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.components.ISolutionCompiler;
import org.dpgame.puzzle.model.components.ITool;
import org.dpgame.puzzle.model.components.IToolbox;
import org.dpgame.puzzle.model.objects.Catcher;
import org.dpgame.puzzle.model.objects.IBoardObject;
import org.dpgame.puzzle.model.objects.IMover;
import org.dpgame.puzzle.model.objects.Mover;
import org.dpgame.puzzle.model.objects.Target;
import org.dpgame.tools.exceptions.PathCrossesItSelf;
import org.dpgame.tools.parameters.ActionList;
import org.dpgame.tools.parameters.BoardParameters;
import org.dpgame.tools.parameters.ToolParameters;
import org.dpgame.tools.parameters.ToolboxParameters;
import org.dpgame.visitor.model.components.VisitorSolutionCompiler;
import org.dpgame.visitor.model.components.VisitorToolbox;

/**
 * A puzzle generator class that extends {@link IPuzzleGenerator} to generate
 * random puzzles for visitor puzzles.
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 * 
 */
public class VisitorPuzzleGenerator implements IPuzzleGenerator {

	/**
	 * Board generation parameters read from game.xml file.
	 */
	private BoardParameters boardParameters;

	/**
	 * The list of applicable actions read from game.xml file.
	 */
	private ActionList actionList;

	/**
	 * Tool-box generation parameters read from game.xml file.
	 */
	private ToolboxParameters toolboxParameters;

	/**
	 * The board of the puzzle.
	 */
	private IBoard<ISquare> board;

	/**
	 * The toolbox of the puzzle.
	 */
	private IToolbox toolbox;

	/**
	 * The solution builder that builds the solution using the specified process
	 * by the player on the tools in the tool-box.
	 */
	private ISolutionCompiler solutionCompiler;

	/**
	 * The square that is used during path generation to keep track of the
	 * current position on the board.
	 */
	private ISquare currentSquare;

	/**
	 * The object that tries to catch the target on the board.
	 */
	private IMover mover;

	/**
	 * A random generator to build random puzzle components.
	 */
	private Random rng;

	private long seed = 12;

	private ArrayList<ITool> paths = new ArrayList<ITool>();

	public VisitorPuzzleGenerator(BoardParameters boardParam,
			ActionList actionList, ToolboxParameters toolboxParam) {
		this.boardParameters = boardParam;
		this.actionList = actionList;
		this.toolboxParameters = toolboxParam;
		//rng = new Random(seed);
		rng = new Random();
	}

	// can be template method
	private void generateNew() {
		generateBoard();
		generateSolutionCompiler();
		generateToolbox();
	}

	/**
	 * Generates a board according to the parameters specified by
	 * {@link BoardParameters}.
	 */
	private void generateBoard() {
		int numberOfRows = boardParameters.getNumberOfRows();
		int numberOfColumns = boardParameters.getNumberOfColumns();

		board = new Board<ISquare>(numberOfRows, numberOfColumns);

		for (int i = 0; i < numberOfRows; i++)
			for (int j = 0; j < numberOfColumns; j++)
				board.set(new Square(board, i, j), i, j);
	}

	/**
	 * Generates a toolbox according to the parameters specified by
	 * {@link ToolboxParameters}.
	 */
	private void generateToolbox() {

		ToolParameters toolParam = toolboxParameters.get("HVPATH");

		int startX = rng.nextInt(board.getRowCount());
		int startY = rng.nextInt(board.getColumnCount());

		// the catcher will be located on this square.
		ISquare startSquare = board.get(startX, startY);

		currentSquare = startSquare;

		int minLength = toolParam.getMinLength();
		int maxLength = toolParam.getMaxLength();

		int numberOfPaths = toolParam.getMinNumber();
		if (toolParam.getMinNumber() != toolParam.getMaxNumber()) {
			numberOfPaths = toolParam.getMinNumber()
					+ rng.nextInt(toolParam.getMaxNumber()
							- toolParam.getMinNumber()) + 1;
		}
		int numberOfEquivalentPaths = toolParam.getRepeating();
		// System.out.println("Total number of paths : " + numberOfPaths +
		// "\n");
		// System.out.println("Repeating paths : " + numberOfEquivalentPaths
		// + "\n");

		int selectedEquivalent = 0;
		IPath path = null;
		IPath totalPath = new HVPath();

		int ignoreCounter = 0;
		// adds the specified number of paths
		while (paths.size() < numberOfPaths) {
			// builds a path of specified length
			// path = buildPath(minLength + rng.nextInt(maxLength - minLength) +
			// 1);
			path = buildPath(rng.nextInt(5) + 1);
			// if equivalent exists already, does not add the path.
			if (!checkEquivalentExists(paths, path)) {
				// if (ignoreCounter < 1 && rng.nextBoolean())
				// paths.add(path);
				// else {
				if (add(totalPath, path)) {
					path.setID(paths.size());
					// adds an equivalent path to the list
					if (rng.nextBoolean() && paths.size() < numberOfPaths
							&& selectedEquivalent < numberOfEquivalentPaths) {
						IPath pathTemp = (IPath) paths.get(rng.nextInt(paths
								.size()));
						IPath pathEquivalent = getEquivalent(pathTemp);
						if (checkOnBoard(totalPath, pathEquivalent,
								currentSquare)) {
							pathEquivalent.setID(pathTemp.getID());
							selectedEquivalent++;
						}
					}
				} else if (rng.nextBoolean() && ignoreCounter < 1) {
					paths.add(path);
					ignoreCounter++;
				}
			}
		}

		mover = new Mover(startSquare, new Catcher());
		startSquare.set((IBoardObject) mover);
		// adds the target at the end of the path found.
		currentSquare.set(new Target());

		// int placeOfFruit = rng.nextInt(paths.size());
		//
		// Iterator<ITool> iterator = paths.iterator();
		// int i = 0;
		// ISquare square = startSquare;
		// IPath fruitPath = null;
		// while (i < placeOfFruit && iterator.hasNext()) {
		// square = getEndSquare(square, fruitPath);
		// fruitPath = (IPath) iterator.next();
		// }
		// if (fruitPath != null) {
		// square = square.getNext(fruitPath.getFirst());
		// square.set(new Fruit());
		// }
		// else System.out.println("no fruit");
		// randomly rotate paths
		int which = rng.nextInt(paths.size());
		IPath pathRotate = (IPath) paths.get(which);
		int num = rng.nextInt(2) + 1;
		IPath pathTemp = null;

		for (int j = 0; j < paths.size(); j++) {
			pathTemp = (IPath) paths.get(j);
			if (pathTemp.getID() == pathRotate.getID())
				for (int i = 0; i < num; i++) {
					pathTemp.rotate();
				}
		}
		toolbox = new VisitorToolbox(paths, solutionCompiler);
	}

	private boolean checkEquivalentExists(ArrayList<ITool> paths, IPath path) {
		for (ITool tool : paths) {
			IPath pathTool = (IPath) tool;
			if (path.isEquivalent(pathTool))
				return true;
		}
		return false;
	}

	/**
	 * Generates a solution compiler according to the parameters specified by
	 * {@link SolutionCompilerParameters}.
	 */
	private void generateSolutionCompiler() {
		solutionCompiler = new VisitorSolutionCompiler(actionList);
	}

	/**
	 * Builds a random path which does not cross itself with the specified
	 * number of steps.
	 * 
	 * @param length
	 *            length of the path to be generated.
	 * @return the generated path of specified length.
	 */
	private IPath buildPath(int length) {
		IPath path = new HVPath();
		ISquare tempSquare = currentSquare;
		IStep step = null;

		// System.out.println("Starting new path, length : " + length);
		while (path.getLength() < length) {
			if (rng.nextBoolean())
				step = new Step(0, rng.nextBoolean() ? 1 : -1);
			else
				step = new Step(rng.nextBoolean() ? 1 : -1, 0);
			if (tempSquare.getNext(step) != null) {
				try {
					tempSquare = tempSquare.getNext(step);
					path.add(step);
				} catch (PathCrossesItSelf e) {
					// System.out.println("Got exception");
					tempSquare = tempSquare.getNext(new Step(-1
							* step.getHDirection(), -1 * step.getVDirection()));
				}
			}
		}
		path.setColor(Color.getHSBColor(rng.nextFloat() * 255,
				rng.nextFloat() * 255, rng.nextFloat() * 255));
		return path;
	}

	/**
	 * Checks if the specified path can be added to the specified total path
	 * without crossing itself; if possible adds the path to the total path and
	 * list of paths.
	 * 
	 * @param totalPath
	 *            the total path to add the new path.
	 * @param newPath
	 *            the new path to be added.
	 * @return true if the path is added to the total path; false otherwise.
	 */
	private boolean add(IPath totalPath, IPath newPath) {
		boolean canAdd = true;
		try {
			totalPath.add(newPath);
			paths.add(newPath);
			// System.out.println("Added path " + newPath.toString());
			currentSquare = getEndSquare(currentSquare, newPath);
		} catch (RuntimeException ex) {
			canAdd = false;
			// System.out.println("Not added path " + newPath.toString());
		}
		return canAdd;
	}

	/**
	 * Returns the square that this path arrives starting from the specified
	 * square.
	 * 
	 * @param start
	 *            the initial square
	 * @param path
	 *            the path to follow.
	 * @return the square that this path arrives starting from the start square.
	 */
	private ISquare getEndSquare(ISquare start, IPath path) {
		if (path == null)
			return start;
		Iterator<IStep> iterator = path.iterator();
		while (iterator.hasNext()) {
			start = start.getNext(iterator.next());
			// System.out.println(start.toString());
		}
		return start;
	}

	private boolean checkOnBoard(IPath totalPath, IPath path, ISquare current) {
		IPath equivalent = path;
		ISquare currentTemp = current;
		Iterator<IStep> iterator = null;

		boolean isOnBoard = false;
		for (int i = 0; i < 4 && isOnBoard == false; i++) {
			isOnBoard = true;
			iterator = equivalent.iterator();
			currentTemp = current;
			while (iterator.hasNext()) {
				currentTemp = currentTemp.getNext(iterator.next());
				if (currentTemp == null) {
					isOnBoard = false;
					break;
				}
			}
			if (!isOnBoard)
				equivalent.rotate();
			else
				return add(totalPath, equivalent);
		}
		return false;
	}

	/**
	 * Returns an equivalent path with this path which has different color.
	 * 
	 * @param path
	 *            the path that the equivalent is required.
	 * @return an equivalent of the specified path.
	 */
	private IPath getEquivalent(IPath path) {
		IPath newPath = path.getCopy();
		int numberOfRotates = rng.nextInt(4);
		while (numberOfRotates >= 0) {
			newPath.rotate();
			numberOfRotates--;
		}
		newPath.setColor(Color.getHSBColor(rng.nextFloat() * 255,
				rng.nextFloat() * 255, rng.nextFloat() * 255));
		return newPath;
	}

	@Override
	public IPuzzle generatePuzzle() {
		generateNew();
		return new VisitorPuzzle(board, toolbox, mover);
	}

	@Override
	public IPuzzle getCurrentPuzzle() {
		return null;
	}
}
