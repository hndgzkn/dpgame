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
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.dpgame.main.controller.GameManager;
import org.dpgame.main.ui.IPanel;

/**
 * A panel that uses JAVA Swing and AWT libraries to view HTML text specified by
 * a URL.
 * 
 * @see IPanel
 * @see JPanel
 * @see JEditorPane
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
public class HTMLPanel extends JPanel implements IPanel, HyperlinkListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The main controller of the game.
	 */
	private GameManager controller;

	/**
	 * The panel that displays the HTML text.
	 */
	private JEditorPane htmlPane;

	/**
	 * Constructor for the {@link HTMLPanel} class.
	 * 
	 * @param controller
	 *            the {@link GameManager} of the game.
	 * @param url
	 *            the URL of the <em>HTML</em> page to be viewed.
	 * @throws IllegalArgumentException
	 *             if the specified URL is not valid.
	 * @throws NullPointerException
	 *             <ul>
	 *             <li>if the specified URL <code>String</code> is
	 *             <code>null</code> or</li>
	 *             <li>the specified <code>controller</code> is
	 *             <code>null</code>.</li>
	 *             </ul>
	 */
	public HTMLPanel(GameManager controller, String url)
			throws IllegalArgumentException, NullPointerException {
		if (controller == null)
			throw new NullPointerException("The controller cannot be null.");
		if (url == null)
			throw new NullPointerException("URL cannot be null");
		if (url.equals(""))
			throw new IllegalArgumentException(
					"Please enter a valid URL.\nURL:" + url);
		this.controller = controller;
		initPanel(url);
	}

	/**
	 * Displays the HTML text in the specified URL in a {@link JEditorPane}. The
	 * specified <code>String</code> must be a valid URL.
	 * 
	 * @param url
	 *            the URL of the <em>HTML</em> page to be viewed.
	 */
	private void initPanel(String filePath) {

		try {
			URL url = new URL("file", "", filePath);
			htmlPane = new JEditorPane(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		htmlPane.addHyperlinkListener(this);
		add(new JScrollPane(htmlPane));
		htmlPane.setEditable(false);
		htmlPane.setPreferredSize(new Dimension(340, 220));
	}

	/**
	 * Sets the text of the {@link HTMLPanel}.
	 * 
	 * @param url
	 *            the URL of the <em>HTML</em> page to be viewed.
	 */
	public void setPage(String url) throws IllegalArgumentException,
			NullPointerException {
		if (url == null)
			throw new NullPointerException("URL cannot be null");
		if (url.equals(""))
			throw new IllegalArgumentException(
					"Please enter a valid URL.\nURL:" + url);
		try {
			htmlPane.setPage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Updates the page when the user clicks on a link in the current page.
	 */
	public void hyperlinkUpdate(HyperlinkEvent event) {
		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			try {
				htmlPane.setPage(event.getURL());
			} catch (IOException ioe) {
				// Some warning to user
			}
		}
	}

}
