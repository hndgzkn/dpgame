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
import org.dpgame.tools.parameters.PuzzleType;
import org.dpgame.tools.parameters.PuzzleTypeList;


/**
 * 
 * @author Hande Özaygen
 * 
 */
public class SelectPanel extends JPanel implements IPanel, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameManager controller;
	private PuzzleTypeList list;
	private LevelPanel levelPanel;

	public SelectPanel(GameManager controller, PuzzleTypeList list,
			LevelPanel levelPanel) throws NullPointerException {
		if (controller == null)
			throw new NullPointerException("Controller cannot be null.");
		if (list == null)
			throw new NullPointerException("Puzzle type list cannot be null.");

		this.controller = controller;
		this.list = list;
		this.levelPanel = levelPanel;
		initPanel();
	}

	private void initPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		setBackground(Color.ORANGE);

		GridBagConstraints constraints = new GridBagConstraints();
		int i = 0;

		for (PuzzleType type : list) {
			JButton button = new JButton(type.getPuzzleName());
			button.setPreferredSize(new Dimension(200, 30));
			button.addActionListener(this);
			constraints = new GridBagConstraints();
			constraints.anchor = GridBagConstraints.SOUTH;
			constraints.fill = GridBagConstraints.NONE;
			constraints.gridx = 0;
			constraints.gridy = ++i;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.insets = new Insets(5, 0, 5, 0);
			add(button, constraints);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton actionButton = (JButton) e.getSource();
		levelPanel.setSelected(actionButton.getText());
	}

}
