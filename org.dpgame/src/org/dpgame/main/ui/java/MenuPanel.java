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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.dpgame.main.controller.GameManager;
import org.dpgame.main.ui.IPanel;
import org.dpgame.puzzle.model.Help;




/**
 * 
 * @author Hande Özaygen
 *
 */
public class MenuPanel extends JPanel implements IPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameManager controller;

	public MenuPanel(GameManager controller)
			throws IllegalArgumentException {
		if (controller == null)
			throw new IllegalArgumentException("The controller cannot be null.");
		this.controller = controller;
		initPanel();
	}

	/**
	 * 
	 */
	private void initPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		setBackground(Color.ORANGE);

		GridBagConstraints constraints = new GridBagConstraints();

		JButton startButton = new JButton("START");
		startButton.setPreferredSize(new Dimension(100, 30));
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showPuzzleSelectView();
			}
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 0, 10, 0);
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(startButton, constraints);

		JButton scoresButton = new JButton("SCORES");
		scoresButton.setPreferredSize(new Dimension(100, 30));
		scoresButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showScoreView();
			}
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(10, 0, 10, 0);
		add(scoresButton, constraints);

		JButton helpButton = new JButton("HELP");
		helpButton.setPreferredSize(new Dimension(100, 30));
		helpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showHelpView(Help.MAINHELP);
			}
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(10, 0, 10, 0);
		add(helpButton, constraints);

		JButton exitButton = new JButton("EXIT");
		exitButton.setPreferredSize(new Dimension(100, 30));
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.exit();
			}
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(10, 0, 45, 0);
		add(exitButton, constraints);

	}
}
