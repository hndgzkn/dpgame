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
package org.dpgame.visitor.ui.java;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.dpgame.puzzle.controller.IPuzzleManager;
import org.dpgame.puzzle.model.components.ISolutionPart;
import org.dpgame.puzzle.ui.ISolutionboxPanel;
import org.dpgame.tools.parameters.ActionList;

/**
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class VisitorSolutionboxPanel extends JPanel implements
		ISolutionboxPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The main controller of the game.
	 */
	private IPuzzleManager controller = null;

	private ActionList actionList;

	private int numberOfTools = 0;

	/**
	 * The list of solutions added by the player
	 */
	private ArrayList<VisitorSolutionPanel> solutionPanels = new ArrayList<VisitorSolutionPanel>();

	/**
	 * Constructor for the solution-box panel.
	 * 
	 * @param controller
	 *            the controller to communicate with model.
	 * @param actionList
	 * @param numberOfTools
	 */
	public VisitorSolutionboxPanel(IPuzzleManager controller,
			ActionList actionList, int numberOfTools) {
		this.controller = controller;
		this.actionList = actionList;
		this.numberOfTools = numberOfTools;
		initPanel();
	}

	private void initPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		setBackground(Color.ORANGE);

		GridBagConstraints constraints = new GridBagConstraints();

		// Adds the button that will enable to add new solution panel.
		JButton button1 = new JButton("Add");
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addPanel();
			}
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(button1, constraints);

		// adds the button that enables to run the solution generated by the
		// player.
		JButton button = new JButton("Run");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(button, constraints);

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;

		JLabel mysolutionLabel = new JLabel();
		mysolutionLabel.setText("My Visitor");
		mysolutionLabel.setBackground(Color.WHITE);
		add(mysolutionLabel, constraints);

		Border border = getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		setBorder(new CompoundBorder(border, margin));
		addPanel();
	}

	public void addPanel() {
		VisitorSolutionPanel solutionPanel;

		solutionPanel = new VisitorSolutionPanel(actionList, numberOfTools);
		solutionPanels.add(solutionPanel);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 3 * solutionPanels.size() + 3;
		constraints.gridwidth = 2;
		constraints.gridheight = 3;
		constraints.weighty = 1;
		add(solutionPanel, constraints);
		validate();
	}

	/**
	 * Runs the solution created by the player.
	 */
	private void run() {
		ArrayList<ISolutionPart> solutionParts = new ArrayList<ISolutionPart>();
		ISolutionPart solution = null;
		
		for (VisitorSolutionPanel solutionPanel : solutionPanels) {
			solution = solutionPanel.getSolution();
			if (solution == null) {
				JOptionPane.showMessageDialog(this, "Select a path number for Visit!");
				return;
			}
			solutionParts.add(solution);
		}
		controller.run(solutionParts);
	}
}
