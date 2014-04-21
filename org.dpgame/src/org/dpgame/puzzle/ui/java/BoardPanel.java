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
package org.dpgame.puzzle.ui.java;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.dpgame.puzzle.ui.IBoardPanel;



/**
 * A board panel views a n x m board. Each square on the board is viewed using a
 * tile (see {@link Tile}).
 * 
 * @see Tile
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public class BoardPanel extends JPanel implements IBoardPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Number of rows of the board.
	 */
	private int numberOfRows = 0;

	/**
	 * Number of columns of the board.
	 */
	private int numberOfColumns = 0;

	/**
	 * Constructor for the board panel.
	 * 
	 * @param numberOfRows
	 *            number of rows of the board.
	 * @param numberOfColumns
	 *            number of columns of the board.
	 */
	public BoardPanel(int numberOfRows, int numberOfColumns) {
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		initPanel();
	}

	/**
	 * Initializes the board panel.
	 * <p>
	 * Sets layout, background color, border and creates the tiles.
	 */
	private void initPanel() {
		setLayout(new GridLayout(numberOfRows, numberOfColumns, 0, 0));
		setBackground(Color.GRAY);

		for (int i = 0; i < (numberOfRows * numberOfColumns); i++)
			add(new Tile());

		Border border = getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		setBorder(new CompoundBorder(border, margin));

		validate();
		setVisible(true);
	}

	/**
	 * Getter method for the view that displays the square at the specified
	 * position on the board.
	 * 
	 * @param row the row number of tile.
	 * @param column the column number of tile.
	 * @return
	 */
	public Tile getTile(int row, int column) {
		return (Tile) getComponent((column + ((numberOfRows - 1) - row)
				* numberOfColumns) );
	}
}