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

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dpgame.main.ui.IPanel;


/**
 * The main frame of the game using JAVA Swing and AWT libraries. It behaves as
 * a container for all the views in the game application. It has fixed size of
 * 500x280.
 * 
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public class MainFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Width of the frame.
	 */
	private int width = 500;

	/**
	 * Height of the frame.
	 */
	private int height = 280;

	/**
	 * Constructor for the <code>MainFrame</code> of the game.
	 */
	public MainFrame() {
		super("Design Patterns Game");
		initFrame();
	}

	/**
	 * Initializes the frame. The frame is fixed size of specified
	 * <code>width</code> and <code>height</code>.
	 */
	private void initFrame() {
		setResizable(false);
		pack();
		setSize(new Dimension(width, height));
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Adds the {@link IPanel} to be set as the current view.
	 * 
	 * @param panel
	 *            <code>IPanel</code> to be set as the current view. All other
	 *            <code>panel</code>s are removed from the
	 *            <code>MainFrame</code>. If the specified <code>panel</code> is
	 *            <code>null</code> it just removes the current
	 *            <code>panel</code>s.
	 */
	public void add(IPanel panel) {
		getContentPane().removeAll();
		if (panel != null) {
			getContentPane().add((JPanel) panel);
		}
		pack();
		setSize(new Dimension(width, height));
	}
}
