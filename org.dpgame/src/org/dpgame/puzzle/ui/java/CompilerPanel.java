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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.dpgame.puzzle.controller.IPuzzleManager;
import org.dpgame.puzzle.ui.AGamePanel;


public class CompilerPanel extends AGamePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea compileErrors;
	private JButton button;
	private IPuzzleManager controller;

	/**
	 * Constructor of the compiler panel.
	 */
	public CompilerPanel(IPuzzleManager controller) {
		this.controller = controller;
		initPanel();
	}

	/**
	 * Initializes the compiler panel.
	 */
	private void initPanel() {
		FlowLayout layout = new FlowLayout();
		setLayout(layout);

		compileErrors = new JTextArea();
		compileErrors.setEditable(false);
		compileErrors.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		compileErrors.setForeground(Color.RED);
		button = new JButton("Menu");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.viewMenu();
			}
		});
		
		
		JScrollPane pane = new JScrollPane(compileErrors); 
		add(pane);
		pane.setPreferredSize(new Dimension(410, 40));
		compileErrors.setPreferredSize(new Dimension(400, 40));
		add(button);
	}

	@Override
	public void update(Observable o, Object arg) {
		String errors = (String) arg;
		compileErrors.setText(errors);
		System.out.println("compiler panel: " + compileErrors.getText()
				+ " end");
		validate();
		repaint();
	}

}
