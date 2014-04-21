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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import org.dpgame.puzzle.model.components.ISolutionPart;
import org.dpgame.tools.parameters.Action;
import org.dpgame.tools.parameters.ActionList;
import org.dpgame.visitor.model.components.VisitorSolutionPart;

public class VisitorSolutionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ActionList actionList;

	private int numberOfTools = 0;

	private JTextArea text;

	private int itemNumber = -1;

	private JLabel solutionboxNumberLabel;

	/**
	 * The arraylist that stores the selected actions by the player to be
	 * applied to the selected tool from the toolbox.
	 */
	private ArrayList<String> selected = new ArrayList<String>();

	/**
	 * 
	 * @param actionList
	 * @param numberOfTools
	 */
	public VisitorSolutionPanel(ActionList actionList, int numberOfTools) {
		this.actionList = actionList;
		this.numberOfTools = numberOfTools;
		initPanel();
	}

	/**
	 * Initializes the panel.
	 */
	private void initPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		setBackground(Color.gray);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		JLabel solutionboxLabel = new JLabel();
		solutionboxLabel.setText("Visit");
		JPopupMenu popupVisit = new JPopupMenu();
		JMenuItem item = null;

		for (int i = 0; i < numberOfTools; i++) {
			item = new JMenuItem((i + 1) + "");
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JMenuItem eventItem = (JMenuItem) e.getSource();
					add(eventItem.getText());
				}
			});
			popupVisit.add(item);
		}
		solutionboxLabel.setComponentPopupMenu(popupVisit);
		add(solutionboxLabel, constraints);

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 5;
		constraints.weightx = 1;
		constraints.weighty = 1;

		text = new JTextArea();
		text.setEditable(false);
		text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		text.setForeground(Color.RED);
		JPopupMenu popup = new JPopupMenu();

		Iterator<Action> actionIterator = actionList.iterator();

		while (actionIterator.hasNext()) {
			item = new JMenuItem(actionIterator.next().getName());
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JMenuItem eventItem = (JMenuItem) e.getSource();
					addText(eventItem.getText());
				}
			});
			popup.add(item);
		}

		text.setComponentPopupMenu(popup);
		add(text, constraints);

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		solutionboxNumberLabel = new JLabel();
		solutionboxNumberLabel.setText(" ( ? )");
		solutionboxNumberLabel.setComponentPopupMenu(popupVisit);
		add(solutionboxNumberLabel, constraints);
	}

	/**
	 * Adds the selected action from the menu to the text view and the selected
	 * items list.
	 * 
	 * @param action
	 *            action to be added to the list for applying to the specific
	 *            tool in the toolbox.
	 */
	private void addText(String action) {
		text.setText(text.getText() + action + "\n");
		selected.add(action);
	}

	/**
	 * 
	 * @param number
	 */
	private void add(String number) {
		itemNumber = Integer.parseInt(number);
		solutionboxNumberLabel.setText(" ( " + number + " )");
		validate();
		repaint();
	}

	/**
	 * Returns the solution generated by the player for a specific tool in the
	 * toolbox.
	 * 
	 * @return the solution generated by the player for a specific tool in the
	 *         toolbox.
	 */
	public ISolutionPart getSolution() {
		if (itemNumber == -1)
			return null;
		return new VisitorSolutionPart(itemNumber, selected);
	}
}
