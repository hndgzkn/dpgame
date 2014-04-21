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
import java.awt.Image;
import java.util.Observable;

import javax.swing.BorderFactory;

import org.dpgame.puzzle.model.basics.ISquare;
import org.dpgame.puzzle.model.objects.IBoardObject;
import org.dpgame.puzzle.ui.AGamePanel;

/**
 * A tile to view each square on the board panel (see {@link BoardPanel}).
 * 
 * @see BoardPanel
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class Tile extends AGamePanel {

	protected Image image = null;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Backgroung color of the tile.
	 */
	private Color color = Color.white;

	/**
	 * Constructor of the tile.
	 */
	public Tile() {
		initialize();
	}

	/**
	 * Constructor of the tile with the background color.
	 * 
	 * @param color
	 *            the backgroud color of the tile.
	 */
	public Tile(Color color) {
		this.color = color;
		initialize();
		//ImageIcon icon = new ImageIcon("arrow3.jpg");
		//this.image = icon.getImage();
	}

	/**
	 * Initializes the tile.
	 * <p>
	 * Sets background color, border and size.
	 */
	private void initialize() {
		setBackground(color);
		setBorder(BorderFactory.createLineBorder(Color.black, 1));
		setSize(30, 30);
	}

	@Override
	public void update(Observable o, Object arg) {
		Color color = null;

		if (arg != null) {
			IBoardObject object = (IBoardObject) arg;
			color = object.getColor();
		} else {
			ISquare square = (ISquare) o;
			color = square.getColor();
		}
		setBackground(color);
		validate();
		repaint();
	}

//	public void update(Graphics g) {
//		if (image != null) {
//			g.drawImage(image, 0, 0, this.getSize().width,
//					this.getSize().height, this);
//		}
//			else {
//			super.validate();
//			super.repaint();
//		}
//	}
//
//	public void paint(Graphics g) {
//		update(g);
//	}
//
}