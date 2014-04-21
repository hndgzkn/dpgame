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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.dpgame.main.controller.GameManager;
import org.dpgame.main.ui.IPanel;
import org.dpgame.tools.parameters.PuzzleType;
import org.dpgame.tools.parameters.PuzzleTypeList;


public class LevelPanel extends JPanel implements IPanel, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameManager controller;
	private PuzzleTypeList list;
	GridBagConstraints constraints;
	private PuzzleType current;
	
	public LevelPanel(GameManager controller, PuzzleTypeList list) throws NullPointerException {
		if (controller == null) throw new NullPointerException("Controller cannot be null.");
		if (list == null) throw new NullPointerException("Puzzle type list cannot be null.");
		
		this.controller = controller;
		this.list = list;
		initPanel();
	}
	
	private void initPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		setBackground(Color.ORANGE);

		constraints = new GridBagConstraints();
	}
	
	public void setSelected(String name) {
		removeAll();
		repaint();
		validate();
		current = list.get(name);
		int i = 0;
		for (i = 0; i < current.getNumberOfLevels(); i++) {
			JButton button = new JButton("LEVEL" + (i + 1));
			//button.setPreferredSize(new Dimension(60, 20));
			button.addActionListener(this);
			constraints = new GridBagConstraints();
			constraints.anchor = GridBagConstraints.SOUTH;
			constraints.fill = GridBagConstraints.NONE;
			constraints.gridx = 0;
			constraints.gridy = i + 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.insets = new Insets(10, 0, 10, 0);
			add(button, constraints);
		}
		
//		JButton button = new JButton("HELP");
//		//button.setPreferredSize(new Dimension(60, 20));
//		button.addActionListener(this);
//		constraints = new GridBagConstraints();
//		constraints.anchor = GridBagConstraints.SOUTH;
//		constraints.fill = GridBagConstraints.NONE;
//		constraints.gridx = 0;
//		constraints.gridy = i + 1;
//		constraints.gridwidth = 1;
//		constraints.gridheight = 1;
//		constraints.insets = new Insets(10, 0, 10, 0);
//		add(button, constraints);
	
		validate();
	}

	private int getLevel(String text) {
		return Integer.parseInt(text.substring(5));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton actionButton = (JButton) e.getSource();	
		controller.selectPuzzle(current.getPuzzleName(), getLevel(actionButton.getText()));
	}
}
