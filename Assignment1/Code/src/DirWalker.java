import java.io.File;
import java.util.Date;

public class DirWalker {

	public void walk( String path, SimpleLogging logg ) {

        File root = new File( path );
        File[] list = root.listFiles();
        if (list == null) return;
        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath(),logg );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
            	/*
            	 * Parse csv files
            	 */
            	if(f.getAbsolutePath().contains(".csv")) {
            	SimpleCsvParser parser = new SimpleCsvParser();
            	parser.readCSV(f.getAbsolutePath(),logg);
            	}
                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
        
    }

    public static void main(String[] args) {
    	DirWalker fw = new DirWalker();
    	 SimpleLogging logg = SimpleLogging.getLogging();
         logg.getLogger();
         long startTime = System.currentTimeMillis();
		fw.walk("C:\\Sample_Data\\Sample_Data" ,logg);
		long endTime = System.currentTimeMillis();
        logg.init();
        logg.logMsg("Total time taken in seconds :"+(endTime-startTime)/1000);
        logg.logMsg("Total skipped rows :"+SimpleCsvParser.getSkippedRows());
        logg.logMsg("Total un-skipped rows :"+SimpleCsvParser.getCountedRows());
        logg.clearResource();
    }

}