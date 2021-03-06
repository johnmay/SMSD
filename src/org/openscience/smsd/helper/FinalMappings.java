/**
 *
 * Copyright (C) 2006-2011  Syed Asad Rahman <asad@ebi.ac.uk>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute iterator and/or
 * modify iterator under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 *
 * This program is distributed in the hope that iterator will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.smsd.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.openscience.cdk.annotations.TestClass;
import org.openscience.cdk.annotations.TestMethod;
import org.openscience.smsd.interfaces.IFinalMapping;

/**
 * Class that stores raw mapping(s) after each algorithm is executed.
 * @cdk.module smsd
 * @cdk.githash
 * @author Syed Asad Rahman <asad@ebi.ac.uk>
 */
@TestClass("org.openscience.cdk.smsd.helper.FinalMappingsTest")
public class FinalMappings implements IFinalMapping {

    private static List<Map<Integer, Integer>> mappings = null;
    private static FinalMappings instance = null;

    protected FinalMappings() {
        mappings = Collections.synchronizedList(new ArrayList<Map<Integer, Integer>>());
    }

    /**
     * Stores mapping solutions
     * @return instance of this object
     */
    @TestMethod("testGetInstance")
    public synchronized static FinalMappings getInstance() {
        if (instance == null) {
            instance = new FinalMappings();
        }
        return instance;
    }

    /**
     *  {@inheritDoc}
     *
     */
    @Override
    @TestMethod("testAdd")
    public synchronized void add(Map<Integer, Integer> mapping) {
        mappings.add(mapping);
    }

    /**
     * {@inheritDoc}
     * @param list list of mappings
     */
    @Override
    @TestMethod("testSet")
    public final synchronized void set(List<Map<Integer, Integer>> list) {
        this.clear();
        mappings.addAll(list);
    }

    /**
     *  {@inheritDoc}
     *
     */
    @Override
    @TestMethod("testGetIterator")
    public synchronized Iterator<Map<Integer, Integer>> getIterator() {
        Iterator<Map<Integer, Integer>> iterator = mappings.iterator();
        return iterator;
    }

    /**
     *  {@inheritDoc}
     *
     */
    @Override
    @TestMethod("testClear")
    public synchronized void clear() {
        this.mappings.clear();
    }

    /**
     *  {@inheritDoc}
     *
     */
    @Override
    @TestMethod("testGetFinalMapping")
    public synchronized List<Map<Integer, Integer>> getFinalMapping() {
        return Collections.synchronizedList(new ArrayList<Map<Integer, Integer>>(mappings));
    }

    /**
     *  {@inheritDoc}
     *
     */
    @Override
    @TestMethod("testGetSize")
    public synchronized int getSize() {
        return mappings.size();
    }
}
