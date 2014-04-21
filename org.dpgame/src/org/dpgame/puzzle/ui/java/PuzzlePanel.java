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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import org.dpgame.main.ui.IPanel;
import org.dpgame.puzzle.model.components.IBoard;
import org.dpgame.puzzle.model.components.ISolutionCompiler;
import org.dpgame.puzzle.model.components.IToolbox;
import org.dpgame.puzzle.ui.IBoardPanel;
import org.dpgame.puzzle.ui.ISolutionboxPanel;
import org.dpgame.puzzle.ui.IToolboxPanel;


/**
 * The panel that combines basic puzzle components to view in the main frame
 * (see {@link IMainFrame}) of the game.
 * <p>
 * The puzzle components are puzzle board (see {@link IBoard}), toolbox (see
 * {@link IToolbox}) and the solution compiler (see {@link ISolutionCompiler}).
 * The puzzle is viewed in {@link IBoardPanel}, the toolbox is viewed in
 * {@link IToolboxPanel} and the solution compiler components are viewed in
 * {@link ISolutionboxPanel}.
 * 
 * @see IMainFrameos
 * 
 * @see IBoard
 * @see IToolbox
 * @see SolutionCompiler
 * 
 * @see IBoardPanel
 * @see IToolboxPanel
 * @see ISolutionboxPanel
 * 
 * @author Hande Özaygen
 * @version 1.1.1
 * 
 */
public class PuzzlePanel extends JPanel implements IPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constraints to locate the added components.
	 */
	private GridBagConstraints constraints = null;

	/**
	 * Constructor for puzzle panel.
	 * <p>
	 * Adds the board, solution-box and tool-box panels to this panel.
	 * 
	 * @param boardPanel
	 *            the panel for the board view.
	 * @param toolboxPanel
	 *            the panel for the tool-box view.
	 * @param solutionboxPanel
	 *            the panel for the solution-box view.
	 * @param compilerPanel
	 *            the panel to view the compilation errors.
	 * @throws NullPointerException
	 *             if
	 *             <ul>
	 *             <li>if the specified <code>boardPanel</code> is <code>null
	 *             </code></li> <li>if the specified <code>toolboxPanel</code>
	 *             is <code>null</code></li> <li>if the specified <code>
	 *             solutionboxPanel</code> is <code>null</code></li> <li>if the
	 *             specified <code> compilerPanel</code> is <code>null</code>
	 *             </li>
	 *             </ul>
	 */
	public PuzzlePanel(IBoardPanel boardPanel, IToolboxPanel toolboxPanel,
			ISolutionboxPanel solutionboxPanel, CompilerPanel compilerPanel)
			throws NullPointerException {
		if (boardPanel == null)
			throw new NullPointerException("Board panel cannot be null");
		if (toolboxPanel == null)
			throw new NullPointerException("Toolbox panel cannot be null");
		if (solutionboxPanel == null)
			throw new NullPointerException("Solutionbox panel cannot be null");
		if (compilerPanel == null)
			throw new NullPointerException("Compiler panel cannot be null");
		initPanel(boardPanel, toolboxPanel, solutionboxPanel, compilerPanel);
	}

	/**
	 * Initializes the panel adding the board panel, solution-box panel and
	 * tool-box panel that were specified in the constructor.
	 * 
	 * @param boardPanel
	 *            the panel for the board view.
	 * @param toolboxPanel
	 *            the panel for the tool-box view.
	 * @param solutionboxPanel
	 *            the panel for the solution-box view.
	 * @param compilerPanel
	 *            the panel to view the compilation errors.
	 */
	private void initPanel(IBoardPanel boardPanel, IToolboxPanel toolboxPanel,
			ISolutionboxPanel solutionboxPanel, CompilerPanel compilerPanel) {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		addBoard(boardPanel);
		addSolutionbox(solutionboxPanel);
		addToolbox(toolboxPanel);
		addLabel(compilerPanel);

		validate();
	}

	/**
	 * Adds the specified <code>boardPanel</code> to this {@link PuzzlePanel}.
	 * 
	 * @param boardPanel
	 *            the panel for the board view.
	 */
	private void addBoard(IBoardPanel boardPanel) {
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.ipadx = 110;

		add((Component) boardPanel, constraints);
	}

	/**
	 * Adds the specified <code>toolboxPanel</code> to this {@link PuzzlePanel}.
	 * 
	 * @param solutionboxPanel
	 *            the panel for the solution-box view.
	 */
	private void addSolutionbox(ISolutionboxPanel solutionboxPanel) {
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;

		add((Component) solutionboxPanel, constraints);
	}

	/**
	 * Adds the specified <code>toolboxPanel</code> to this {@link PuzzlePanel}.
	 * 
	 * @param toolboxPanel
	 *            the panel for the tool-box view.
	 */
	private void addToolbox(IToolboxPanel toolboxPanel) {
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;

		add((Component) toolboxPanel, constraints);
	}

	private void addLabel(CompilerPanel compilerPanel) {
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		add(compilerPanel, constraints);
	}

}
