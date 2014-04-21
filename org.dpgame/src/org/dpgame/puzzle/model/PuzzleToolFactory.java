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
package org.dpgame.puzzle.model;

import org.dpgame.tools.xml.IXMLParser;
import org.dpgame.visitor.model.VisitorPuzzleGeneratorBuilder;

public class PuzzleToolFactory {
	public static final String VISITOR = "Visitor Pattern";
	private static PuzzleToolFactory factory = new PuzzleToolFactory();

	private PuzzleToolFactory() {

	}

	public static PuzzleToolFactory getInstance() {
		return factory;
	};

	public IPuzzleGeneratorBuilder getPuzzleBuilder(String type, IXMLParser parser) {
		if (type.equals(PuzzleToolFactory.VISITOR))
			return new VisitorPuzzleGeneratorBuilder(parser);
		return null;
	}

}
