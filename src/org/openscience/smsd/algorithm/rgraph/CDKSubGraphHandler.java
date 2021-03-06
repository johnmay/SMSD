/* Copyright (C) 2006-2011  Syed Asad Rahman <asad@ebi.ac.uk>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR sourceAtom PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.smsd.algorithm.rgraph;

import java.util.*;
import org.openscience.cdk.annotations.TestClass;
import org.openscience.cdk.annotations.TestMethod;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.graph.ConnectivityChecker;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.openscience.cdk.isomorphism.matchers.IQueryAtomContainer;
import org.openscience.smsd.AtomAtomMapping;
import org.openscience.smsd.helper.FinalMappings;
import org.openscience.smsd.helper.MoleculeInitializer;
import org.openscience.smsd.interfaces.IResults;

/**
 * This class acts as a handler class for CDKMCS algorithm
 * {@link org.openscience.cdk.smsd.algorithm.cdk.CDKMCS}.
 *
 * @cdk.module smsd @cdk.githash
 *
 * @author Syed Asad Rahman <asad@ebi.ac.uk>
 */
@TestClass("org.openscience.cdk.smsd.algorithm.cdk.CDKMCSHandlerTest")
public class CDKSubGraphHandler extends MoleculeInitializer implements IResults {

//    //~--- fields -------------------------------------------------------------
    private final IAtomContainer source;
    private final IAtomContainer target;
    private boolean rOnPFlag = false;
    private List<AtomAtomMapping> allAtomMCS = null;
    private List<Map<Integer, Integer>> allMCS = null;
    private final boolean shouldMatchRings;
    private final boolean shouldMatchBonds;

    //~--- constructors -------------------------------------------------------
    /*
     * Creates a new instance of MappingHandler
     */
    /**
     *
     * @param source
     * @param target
     * @param shouldMatchBonds
     * @param shouldMatchRings
     */
    public CDKSubGraphHandler(IAtomContainer source, IAtomContainer target, boolean shouldMatchBonds, boolean shouldMatchRings) {
        this.source = source;
        this.target = target;
        this.shouldMatchRings = shouldMatchRings;
        this.shouldMatchBonds = shouldMatchBonds;
        this.allAtomMCS = new ArrayList<AtomAtomMapping>();
        this.allMCS = new ArrayList<Map<Integer, Integer>>();
        if (this.shouldMatchRings) {
            try {
                initializeMolecule(source);
                initializeMolecule(target);
            } catch (CDKException ex) {
            }
        }
        isSubgraph();
    }

    /**
     *
     * @param source
     * @param target
     */
    public CDKSubGraphHandler(IQueryAtomContainer source, IQueryAtomContainer target) {
        this.source = source;
        this.target = target;
        this.shouldMatchRings = true;
        this.shouldMatchBonds = true;
        this.allAtomMCS = new ArrayList<AtomAtomMapping>();
        this.allMCS = new ArrayList<Map<Integer, Integer>>();
        if (shouldMatchRings) {
            try {
                initializeMolecule(source);
                initializeMolecule(target);
            } catch (CDKException ex) {
            }
        }
        isSubgraph();
    }

    /**
     * {@inheritDoc}
     *
     */
    private boolean isSubgraph() {

        CDKRMapHandler rmap = new CDKRMapHandler();

        try {

            if ((source.getAtomCount() == target.getAtomCount()) && source.getBondCount() == target.getBondCount()) {
                rOnPFlag = true;
                rmap.calculateIsomorphs(source, target, shouldMatchBonds, shouldMatchRings);

            } else if (source.getAtomCount() > target.getAtomCount() && source.getBondCount() != target.getBondCount()) {
                rOnPFlag = true;
                rmap.calculateSubGraphs(source, target, shouldMatchBonds, shouldMatchRings);

            } else {
                rOnPFlag = false;
                rmap.calculateSubGraphs(target, source, shouldMatchBonds, shouldMatchRings);
            }

            setAllMapping();
            setAllAtomMapping();

        } catch (CDKException e) {
            rmap = null;
//            System.err.println("WARNING: graphContainer: most probably time out error ");
        }

        return !getFirstAtomMapping().isEmpty();
    }

    /**
     *
     * @param mol
     * @param mcss
     * @param shouldMatchBonds
     * @param shouldMatchRings
     * @return IAtomContainer Set
     * @throws CDKException
     */
    protected IAtomContainerSet getUncommon(IAtomContainer mol, IAtomContainer mcss, boolean shouldMatchBonds, boolean shouldMatchRings) throws CDKException {
        ArrayList<Integer> atomSerialsToDelete = new ArrayList<Integer>();

        List<List<CDKRMap>> matches = CDKMCS.getSubgraphAtomsMaps(mol, mcss, shouldMatchBonds, shouldMatchRings);
        List<CDKRMap> mapList = matches.get(0);
        for (Object o : mapList) {
            CDKRMap rmap = (CDKRMap) o;
            atomSerialsToDelete.add(rmap.getId1());
        }

        // at this point we have the serial numbers of the bonds to delete
        // we should get the actual bonds rather than delete by serial numbers
        ArrayList<IAtom> atomsToDelete = new ArrayList<IAtom>();
        for (Integer serial : atomSerialsToDelete) {
            atomsToDelete.add(mol.getAtom(serial));
        }

        // now lets get rid of the bonds themselves
        for (IAtom atom : atomsToDelete) {
            mol.removeAtomAndConnectedElectronContainers(atom);
        }

        // now we probably have a set of disconnected components
        // so lets get a set of individual atom containers for
        // corresponding to each component
        return ConnectivityChecker.partitionIntoMolecules(mol);
    }

    //~--- get methods --------------------------------------------------------
    private synchronized void setAllMapping() {

        //int count_final_sol = 1;
        //System.out.println("Output of the final FinalMappings: ");
        try {
            List<Map<Integer, Integer>> sol = FinalMappings.getInstance().getFinalMapping();
            int counter = 0;
            for (Map<Integer, Integer> final_solution : sol) {
                TreeMap<Integer, Integer> atomMappings = new TreeMap<Integer, Integer>();
                for (Map.Entry<Integer, Integer> Solutions : final_solution.entrySet()) {

                    int IIndex = Solutions.getKey().intValue();
                    int JIndex = Solutions.getValue().intValue();

                    if (rOnPFlag) {
                        atomMappings.put(IIndex, JIndex);
                    } else {
                        atomMappings.put(JIndex, IIndex);
                    }
                }
                if (!allMCS.contains(atomMappings)) {
                    allMCS.add(counter++, atomMappings);
                }
            }

        } catch (Exception ex) {
            ex.getCause();
        }

    }

    private synchronized void setAllAtomMapping() {
        List<Map<Integer, Integer>> sol = allMCS;

        int counter = 0;
        for (Map<Integer, Integer> final_solution : sol) {
            AtomAtomMapping atomMappings = new AtomAtomMapping(source, target);
            for (Map.Entry<Integer, Integer> Solutions : final_solution.entrySet()) {

                int IIndex = Solutions.getKey().intValue();
                int JIndex = Solutions.getValue().intValue();

                IAtom sourceAtom = null;
                IAtom targetAtom = null;

                sourceAtom = source.getAtom(IIndex);
                targetAtom = target.getAtom(JIndex);
                atomMappings.put(sourceAtom, targetAtom);

            }
            allAtomMCS.add(counter++, atomMappings);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    @TestMethod("testGetAllAtomMapping")
    public List<AtomAtomMapping> getAllAtomMapping() {
        return Collections.unmodifiableList(allAtomMCS);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    @TestMethod("testGetFirstAtomMapping")
    public AtomAtomMapping getFirstAtomMapping() {
        if (allAtomMCS.iterator().hasNext()) {
            return allAtomMCS.iterator().next();
        }
        return new AtomAtomMapping(source, target);
    }
}
