/**
 * Fichier : LeaWorkflow.java
 * Package : com.sfr.lea.batch
 *
 * Année   : 2009
 * Projet  : LeaBatch
 * Auteur  : Jean-Francois Houle (ldcg28882)
 */
package org.kitfox.report;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;

/**
 * @author Jean-Francois Houle (ldcg28882)
 */
public class ReportTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ReportTest reportTest = new ReportTest();
        reportTest.process();
    }

    @SuppressWarnings("unchecked")
    public void process() {
        IReportEngine engine = setup();


        try {
            InputStream is = this.getClass().getResourceAsStream("Report.rptdesign");
            IReportRunnable design = engine.openReportDesign(is);
            IRunAndRenderTask task = engine.createRunAndRenderTask(design);
            // Set parent classloader for engine
            task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY,
                    this.getClass().getClassLoader());

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("reportId", 385804L);
            task.setParameterValues(params);

            //Setup rendering to HTML
            EXCELRenderOption options = new EXCELRenderOption();
            //options.setOutputFormat("xls_spudsoft");
            options.setOutputFormat("xlsx");
            options.setOption(IRenderOption.EMITTER_ID, "uk.co.spudsoft.birt.emitters.excel.XlsxEmitter");
            options.setOption(IRenderOption.HTML_PAGINATION, Boolean.TRUE );
            options.setOption( "ExcelEmitter.DEBUG", Boolean.TRUE);
            //options.setOption("ExcelEmitter.RemoveBlankRows", Boolean.TRUE);
            options.setOutputFileName("c:/temp/Test.xlsx");
            //Setting this to true removes html and body tags

            task.setRenderOption(options);

            task.validateParameters();

            //run and render report
            task.run();
            task.close();
        } catch(Throwable e) {
            e.printStackTrace();
        }

        destroy(engine);
        System.exit(0);
    }

    private void destroy(IReportEngine engine) {
        // destroy the engine.
        engine.destroy();
        Platform.shutdown();
    }

    private IReportEngine setup() {
        try{
            final EngineConfig config = new EngineConfig( );
            //delete the following line if using BIRT 3.7 or later

            config.setEngineHome("C:\\birt-runtime-4_5_0\\ReportEngine");
            config.setLogConfig("c:/temp", Level.ALL);

            Platform.startup( config );  //If using RE API in Eclipse/RCP application this is not needed.
            IReportEngineFactory factory = (IReportEngineFactory) Platform
                    .createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
            return factory.createReportEngine( config );

        }catch( Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
