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

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.dpgame.main.controller.GameManager;
import org.dpgame.main.ui.IPanel;


/**
 * A panel used to view two panels side by side separated by a divider.
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class SplitPanel extends JPanel implements IPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The main controller of the game.
	 */
	private GameManager controller;

	/**
	 * The split pane that provides
	 */
	private JSplitPane splitPane;

	/**
	 * Constructor of the SplitPanel
	 */
	public SplitPanel(GameManager controller) throws IllegalArgumentException {
		if (controller == null)
			throw new IllegalArgumentException("The controller cannot be null.");
		this.controller = controller;
		initPanel();
	}

	/**
	 * 
	 */
	private void initPanel() {
		setOpaque(true);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
		splitPane.setOneTouchExpandable(true);
		add(splitPane);

	}

	/**
	 * Sets the preferred size of this panel.
	 * 
	 * @param d
	 *            the preferred size of this panel.
	 */
	public void setPanelSize(Dimension d) {
		splitPane.setPreferredSize(d);
	}

	/**
	 * Sets the left component of the <code>SplitPanel</code> and the location
	 * of the divider.
	 * 
	 * @param leftPanel
	 *            the panel to be set as the right component.
	 * @param dividerLocation
	 *            @param dividerLocation an integer specifying a UI-specific
	 *            value (typically a pxiel count).
	 */
	public void setLeftPane(IPanel leftPanel, int dividerLocation) {
		splitPane.setLeftComponent((JPanel) leftPanel);
		splitPane.setDividerLocation(dividerLocation);
	}

	/**
	 * Sets the right component of the <code>SplitPanel</code> and the location
	 * of the divider.
	 * 
	 * @param rightPanel
	 *            the panel to be set as the right component.
	 * @param dividerLocation
	 *            an integer specifying a UI-specific value (typically a pxiel
	 *            count).
	 */
	public void setRightPane(IPanel rightPanel, int dividerLocation) {
		splitPane.setRightComponent((JPanel) rightPanel);
		splitPane.setDividerLocation(dividerLocation);
	}
}
