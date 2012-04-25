/* Copyright 2009-2012 David Hadka
 *
 * This file is part of the MOEA Framework.
 *
 * The MOEA Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * The MOEA Framework is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the MOEA Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.moeaframework.problem.misc;

import org.moeaframework.core.CoreUtils;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;

/**
 * The Fonseca (2) problem.
 * <p>
 * Properties:
 * <ul>
 *   <li>Connected Pareto set
 *   <li>Concave Pareto front
 * </ul>
 * <p>
 * References:
 * <ol>
 *   <li>Van Veldhuizen, D. A (1999).  "Multiobjective Evolutionary Algorithms: 
 *       Classifications, Analyses, and New Innovations."  Air Force Institute
 *       of Technology, Ph.D. Thesis, Appendix B.
 * </ol>
 */
public class Fonseca2 extends AbstractProblem {
	
	/**
	 * Constructs the Fonseca (2) problem with {@code 3} decision variables.
	 */
	public Fonseca2() {
		this(3);
	}

	/**
	 * Constructs the Fonseca (2) problem with the specified number of decision
	 * variables.
	 * 
	 * @param numberOfVariables the number of decision variables
	 */
	public Fonseca2(int numberOfVariables) {
		super(numberOfVariables, 2);
	}

	@Override
	public void evaluate(Solution solution) {
		double[] x = CoreUtils.castVariablesToDoubleArray(solution);
		double f1 = 0.0;
		double f2 = 0.0;
		
		for (int i=0; i<numberOfVariables; i++) {
			f1 += Math.pow(x[i] - 1.0/Math.sqrt(numberOfVariables), 2.0);
			f2 += Math.pow(x[i] + 1.0/Math.sqrt(numberOfVariables), 2.0);
		}
		
		f1 = 1.0 - Math.exp(-f1);
		f2 = 1.0 - Math.exp(-f2);
		
		solution.setObjective(0, f1);
		solution.setObjective(1, f2);
	}

	@Override
	public Solution newSolution() {
		Solution solution = new Solution(numberOfVariables, 2);

		for (int i=0; i<numberOfVariables; i++) {
			solution.setVariable(i, new RealVariable(-4.0, 4.0));
		}

		return solution;
	}

}
