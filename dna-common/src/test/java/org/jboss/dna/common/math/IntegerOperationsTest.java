/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.dna.common.math;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import org.jboss.dna.common.math.IntegerOperations;
import org.junit.Before;
import org.junit.Test;

public class IntegerOperationsTest {

    private IntegerOperations ops = new IntegerOperations();

    @Before
    public void beforeEach() throws Exception {
    }

    @Test
    public void shouldReturnProperExponenentInScientificNotation() {
        assertEquals(0, ops.getExponentInScientificNotation(0));
        assertEquals(0, ops.getExponentInScientificNotation(1));
        assertEquals(0, ops.getExponentInScientificNotation(2));
        assertEquals(0, ops.getExponentInScientificNotation(9));
        assertEquals(1, ops.getExponentInScientificNotation(10));
        assertEquals(1, ops.getExponentInScientificNotation(20));
        assertEquals(1, ops.getExponentInScientificNotation(99));
        assertEquals(2, ops.getExponentInScientificNotation(100));
        assertEquals(2, ops.getExponentInScientificNotation(200));
        assertEquals(2, ops.getExponentInScientificNotation(999));
        assertEquals(3, ops.getExponentInScientificNotation(1000));
        assertEquals(3, ops.getExponentInScientificNotation(2000));
        assertEquals(3, ops.getExponentInScientificNotation(9999));
    }

    @Test
    public void shouldRoundNumbersGreaterThan10() {
        assertThat(ops.roundUp(-101, 0), is(-101));
        assertThat(ops.roundUp(-101, 1), is(-101));
        assertThat(ops.roundUp(-101, 1), is(-101));
        assertThat(ops.roundUp(-101, -1), is(-100));
        assertThat(ops.roundUp(-109, -1), is(-110));
        assertThat(ops.roundUp(101, 0), is(101));
        assertThat(ops.roundUp(101, 0), is(101));
        assertThat(ops.roundUp(101, 1), is(101));
        assertThat(ops.roundUp(101, 1), is(101));
        assertThat(ops.roundUp(109, -1), is(110));
        assertThat(ops.roundUp(101, -1), is(100));
    }

    @Test
    public void shouldKeepSignificantFigures() {
        assertThat(ops.keepSignificantFigures(0, 2), is(0));
        assertThat(ops.keepSignificantFigures(1201234, 5), is(1201200));
        assertThat(ops.keepSignificantFigures(1201254, 5), is(1201300));
        assertThat(ops.keepSignificantFigures(1201234, 4), is(1201000));
        assertThat(ops.keepSignificantFigures(1201234, 3), is(1200000));
        assertThat(ops.keepSignificantFigures(1201234, 2), is(1200000));
        assertThat(ops.keepSignificantFigures(1201234, 1), is(1000000));
        assertThat(ops.keepSignificantFigures(-1320, 2), is(-1300));
    }
}
