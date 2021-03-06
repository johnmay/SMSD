package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.io.iterator.IIteratingChemObjectReader;
import org.openscience.cdk.io.iterator.IteratingSDFReader;
import org.openscience.cdk.isomorphism.UniversalIsomorphismTester;
import org.openscience.smsd.Substructure;

/**
 *
 * @author Asad
 */
public class SubstructureBenchMark {

    /**
     * @param args the command line arguments
     * @throws FileNotFoundException
     * @throws IOException
     * @throws CDKException  
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, CDKException {
        String queryFilePath = (args.length > 0) ? args[0] : "data/actives.sdf";
        String targetFilePath = (args.length > 1) ? args[1] : "data/all.sdf";
        File qFile = new File(queryFilePath);
        File tFile = new File(targetFilePath);

        IIteratingChemObjectReader qFileReader = read(qFile);

        if (qFileReader == null) {
            throw new IOException("Unknown input type ");
        }
        List<IAtomContainer> targets = new ArrayList<IAtomContainer>();
        IIteratingChemObjectReader tFileReader = read(tFile);
        while (tFileReader.hasNext()) {
            targets.add((IAtomContainer) tFileReader.next());
        }

        int counter = 0;
        while (qFileReader.hasNext()) {
            IAtomContainer query = (IAtomContainer) qFileReader.next();
            int smsdSolutionCount = 0;
            int uitSolutionCount = 0;
            long t0 = System.currentTimeMillis();
            for (IAtomContainer target : targets) {
                smsdSolutionCount += getSMSDSolutionCount(query, target);
            }
            long timeNow = System.currentTimeMillis();
            long smsdTime = (timeNow - t0);

//            tFileReader = read(tFile);
            long tUIT0 = System.currentTimeMillis();
            for (IAtomContainer target : targets) {
                uitSolutionCount += getUITSolutionCount(query, target);
            }
            timeNow = System.currentTimeMillis();
            long uitTime = timeNow - tUIT0;

            String out = String.format("%d SMSDt %d SMSDs %d UITt %d UITs %d ",
                    counter, smsdTime, smsdSolutionCount, uitTime, uitSolutionCount);
            System.out.println(out);
            counter++;
        }

    }

    private static int getSMSDSolutionCount(IAtomContainer queryMol, IAtomContainer target) throws CDKException {

        Substructure substructure = new Substructure(queryMol, target, true, false, false);

        if (substructure.isSubgraph()) {
            return 1;
        } else {
            return 0;
        }
//
//        IQuery query = null;
//        IMapper mapper = null;
//
//        query = new QueryCompiler(queryMol, true).compile();
//        mapper = new VFMapper(query);
//        if (mapper.hasMap(target)) {
//            return 1;
//        }
//
//        return 0;
    }

    private static int getUITSolutionCount(IAtomContainer query, IAtomContainer target) throws CDKException {
//       List bondMapping = UniversalIsomorphismTester.getSubgraphMaps(target, query);
//       List<List<RMap>> sol = UniversalIsomorphismTester.makeAtomsMapsOfBondsMaps(bondMapping, target, query);
        if (UniversalIsomorphismTester.isSubgraph(target, query)) {
            return 1;
        } else {
            return 0;
        }
//       return sol.size();
    }

    /**
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static IIteratingChemObjectReader read(File file) throws FileNotFoundException {

        FileReader in = new FileReader(file);
        return new IteratingSDFReader(
                in, DefaultChemObjectBuilder.getInstance());

    }
}
