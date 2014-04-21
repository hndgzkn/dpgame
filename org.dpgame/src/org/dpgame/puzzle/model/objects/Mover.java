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
package org.dpgame.puzzle.model.objects;

import java.awt.Color;
import java.awt.Image;

import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.basics.ISquare;
import org.dpgame.puzzle.model.basics.IStep;
import org.dpgame.puzzle.model.basics.actions.MoveInvoker;
import org.dpgame.puzzle.model.components.IBoard;

/**
 * A mover is an object on the board which can move along a specified path (see
 * {@link IPath}). It knows on which square (see {@link ISquare}) it is on the
 * board (see {@link IBoard}).
 * 
 * 
 * @see IPath
 * @see IBoard
 * @see ISquare
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public class Mover implements IMover, IBoardObject {

	/**
	 * The board object that this mover moves along a path.
	 */
	private IBoardObject boardObject;

	/**
	 * The square that this object is on.
	 */
	private ISquare parent;

	/**
	 * The move behavior of the movable object. Determined by the step that it
	 * will take.
	 */
	private MoveInvoker moveInvoker;

	/**
	 * Constructor for the mover. Initializes a mover object on the specified
	 * square with specified row and column on the specified board.
	 * 
	 * @param board
	 *            the board that this catcher is located on.
	 * @throws NullPointerException
	 *             if the specified square or board object is null.
	 */
	public Mover(ISquare square, IBoardObject boardObject)
			throws NullPointerException {
		if (square == null)
			throw new NullPointerException(
					"This object cannot be located on a null square.");
		this.parent = square;
		if (boardObject == null)
			throw new NullPointerException("Board object cannor be null.");
		this.boardObject = boardObject;
	}

	@Override
	public void moveAlong(final IPath path) throws IllegalArgumentException {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (IStep step : path) {
					takeStep(step);
					// System.out.println("step taken");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();

		// for (IStep step : path)
		// takeStep(step);

	}

	/**
	 * Moves in the direction of the step with the behavior that this step
	 * provides.
	 * 
	 * @param step
	 *            the step to move
	 */
	private void takeStep(IStep step) throws IllegalArgumentException {
		moveInvoker = step.getMoveInvoker();
		ISquare next = parent.getNext(step);
		if (next != null) {
			parent.remove(boardObject);
			next.set(boardObject);
			parent = next;
		}
		// if (next != null && behavior.canMove(this, next)) {
		// if (parent.contains(this))
		// parent.set(getFootPrint());
		// parent = next;
		// } else
		// throw new IllegalArgumentException("Move not possible");
	}

	@Override
	public IBoardObject getFootPrint() {
		return boardObject.getFootPrint();
	}

	@Override
	public boolean accept(IBoardObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(IBoardObject object) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor() {
		return boardObject.getColor();
	}

	@Override
	public void setColor(Color color) throws IllegalArgumentException {
		boardObject.setColor(color);
	}

	@Override
	public Image getImage() {
		return boardObject.getImage();
	}

	@Override
	public String toString() {
		return boardObject.toString();
	}
}
