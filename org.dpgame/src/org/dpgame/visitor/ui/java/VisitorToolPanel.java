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
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dpgame.puzzle.model.basics.IPath;
import org.dpgame.puzzle.model.basics.IStep;
import org.dpgame.puzzle.model.basics.PathIterator;
import org.dpgame.puzzle.model.components.IToolbox;
import org.dpgame.puzzle.ui.java.Tile;


/**
 * A panel to view each individual tool in the tool-box (see
 * {@link IToolbox}, {@link VisitorToolboxPanel}).
 * 
 * @see IToolbox
 * @see VisitorToolboxPanel
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class VisitorToolPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int num = 0;
	private IPath path;

	public VisitorToolPanel(int i, IPath path) {
		num = i;
		this.path = path;
		initPanel();
	}

	private void initPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		Iterator<IStep> pathIterator = path.iterator();
		IStep step = null;
		int up = 0;
		int left = 0;

		int x = 0;
		int y = 0;

		while (pathIterator.hasNext()) {
			step = pathIterator.next();
			if (step.getHDirection() == -1)
				left++;
			if (step.getVDirection() == 1)
				up++;
		}

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		JLabel toolboxLabel = new JLabel();
		toolboxLabel.setText("" + num);
		toolboxLabel.setBackground(Color.darkGray);
		add(toolboxLabel, constraints);

		x = left + 1;
		y = up + 1;

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new Tile(Color.BLUE), constraints);

		pathIterator = (PathIterator) path.iterator();
		while (pathIterator.hasNext()) {
			step = pathIterator.next();

			x += step.getHDirection();
			y -= step.getVDirection();
			constraints = new GridBagConstraints();
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridx = x;
			constraints.gridy = y;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			add(new Tile(step.getColor()), constraints);
		}

		validate();
		setVisible(true);
	}
}
