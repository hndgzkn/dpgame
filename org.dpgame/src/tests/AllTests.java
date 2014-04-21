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
package tests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.org.dpgame.puzzle.model.basics.JUnit_HVPath;
import tests.org.dpgame.puzzle.model.basics.JUnit_PathIterator;
import tests.org.dpgame.puzzle.model.basics.JUnit_Square;
import tests.org.dpgame.puzzle.model.basics.JUnit_Step;
import tests.org.dpgame.puzzle.model.components.JUnit_Board;
import tests.org.dpgame.visitor.model.components.JUnit_VisitorSolutionCompiler;

/**
 * A Junit test suite to run all JUnit tests under the tests directory.
 * 
 * @author Hande Özaygen
 * @version 1.1.0
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	JUnit_Board.class,
	JUnit_HVPath.class, 
	JUnit_PathIterator.class,
	JUnit_Square.class,
	JUnit_Step.class, 
	JUnit_VisitorSolutionCompiler.class
//	JUnit_Catcher.class, 
//	JUnit_VisitorPuzzle.class, 
//	JUnit_Solutionbox.class,
//	JUnit_Toolbox.class,
//	JUnit_RotatorStep.class, 
//	JUnit_IgnoredStep.class,
	})
public class AllTests {

}
