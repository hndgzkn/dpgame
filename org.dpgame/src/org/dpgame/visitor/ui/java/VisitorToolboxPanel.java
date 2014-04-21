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
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.components.ITool;
import org.dpgame.puzzle.ui.IToolboxPanel;

public class VisitorToolboxPanel extends JPanel implements IToolboxPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numberOfTools = 0;

	private GridBagConstraints constraints = null;

	public VisitorToolboxPanel(ArrayList<ITool> tools) {
		initPanel();
		Iterator<ITool> iterator = tools.iterator();
		while (iterator.hasNext())
			addTool(iterator.next());
	}

	private void initPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		setBackground(Color.GRAY);

		// constraints = new GridBagConstraints();
		// constraints.anchor = GridBagConstraints.NORTH;
		// constraints.fill = GridBagConstraints.NONE;
		// constraints.gridx = 0;
		// constraints.gridy = 0;
		// constraints.gridwidth = 1;
		// constraints.gridheight = 1;
		// constraints.weightx = 1;
		// constraints.ipadx = 30;
		// // constraints.weighty = 0;
		// JLabel toolboxLabel = new JLabel();
		// toolboxLabel.setText("Tool-box");
		// toolboxLabel.setBackground(Color.darkGray);
		// add(toolboxLabel, constraints);
		//
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 30;
		// constraints.weightx = 0;
		// constraints.weighty = 0;
		JLabel mytoolsLabel = new JLabel();
		mytoolsLabel.setText("My Paths");
		mytoolsLabel.setBackground(Color.white);
		add(mytoolsLabel, constraints);

		Border border = getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		setBorder(new CompoundBorder(border, margin));

	}

	private void addTool(ITool tool) {
		IPath path = (IPath) tool;
		numberOfTools++;

		VisitorToolPanel toolPanel = new VisitorToolPanel(numberOfTools, path);
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 3 * numberOfTools + 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		constraints.weightx = 1;
		constraints.weighty = 1;
		add(toolPanel, constraints);
		validate();
	}
}
