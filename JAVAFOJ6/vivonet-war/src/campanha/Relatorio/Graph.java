package campanha.Relatorio;

import br.com.vivo.fo.constantes.ConstantesCRM;

import com.vivo.servlets.graph.ChartProducer;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DefaultCategoryDataset;
import java.util.Map.Entry;
import org.jfree.chart.Legend;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.labels.CategoryLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.BarRenderer;
import org.jfree.chart.renderer.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DefaultPieDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.util.SortOrder;

public class Graph implements ChartProducer {

    JFreeChart createBarChart(String name, Iterator entries) {

        DefaultCategoryDataset  barDataSet = new DefaultCategoryDataset();
        Double                  iValor;

        while ( entries.hasNext() ) {
                Entry one = (Entry) (entries.next());

                if ( !((String)one.getKey()).equalsIgnoreCase("name") && 
                     !((String)one.getKey()).equalsIgnoreCase("font") ){

                     try { iValor = new Double( (String)one.getValue() ); } 
                     catch ( NumberFormatException nfe ) { iValor = new Double(ConstantesCRM.SZERO); }

                     barDataSet.addValue( iValor.doubleValue(), (String)one.getKey(), ConstantesCRM.SVAZIO );
                }
        }

        return ChartFactory.createBarChart3D( name, ConstantesCRM.SVAZIO, "Valores", barDataSet, 
                                              PlotOrientation.VERTICAL, true, 
                                              true, false );
    }

    JFreeChart createPieChart(String name, Iterator entries) {

        DefaultPieDataset       pieDataSet = new DefaultPieDataset();
        Double                  iValor;

        while ( entries.hasNext() ) {
                Entry one = (Entry) (entries.next());

                if ( !((String)one.getKey()).equalsIgnoreCase("name") && 
                     !((String)one.getKey()).equalsIgnoreCase("font") ){

                     try { iValor = new Double( (String)one.getValue() ); } 
                     catch ( NumberFormatException nfe ) { iValor = new Double(ConstantesCRM.SZERO); }

                     pieDataSet.setValue( (String)one.getKey(), iValor.doubleValue() );
                }
        }

        return ChartFactory.createPieChart3D( name, pieDataSet, true, true, false);
    }

    public JFreeChart createChart( Map params ) {

        String      name = (String)params.get("name");
        Iterator    entries = ((HashMap)params).entrySet().iterator();
        JFreeChart  chart = this.createBarChart((String)params.get("name"), entries);

        if ( name != null ) {

            String axisFontName = "SansSerif";
            int axisFontStyle = java.awt.Font.PLAIN;
            int axisFontSize = 10;

            String axisLabelFont = "SansSerif";
            int axisLabelFontStyle = java.awt.Font.PLAIN;
            int axisLabelFontSize = 9;

            String axisTickFontName = "SansSerif";
            int axisTickFontStyle = java.awt.Font.PLAIN;
            int axisTickFontSize = 10;

            String legendFontName = "SansSerif";
            int legendFontStyle = java.awt.Font.PLAIN;
            int legendFontSize = 6;
            try { legendFontSize = new Integer( (String)params.get("font") ).intValue(); } 
            catch (NumberFormatException nfe) {}
            int legendPosition = Legend.SOUTH;

            String titleFontName = "SansSerif";
            int titleFontStyle = java.awt.Font.PLAIN;
            int titleFontSize = 9;

            StandardLegend legend = (StandardLegend) chart.getLegend();

            java.awt.Font axisFont = new java.awt.Font(axisFontName, axisFontStyle, axisFontSize);
            java.awt.Font axisTickFont = new java.awt.Font(axisTickFontName, axisTickFontStyle, axisTickFontSize);
            java.awt.Font legendFont = new java.awt.Font(legendFontName, legendFontStyle, legendFontSize);
            java.awt.Font TitleFont = new java.awt.Font(titleFontName, titleFontStyle, titleFontSize);
            java.awt.Font labelFont = new java.awt.Font(axisLabelFont, axisLabelFontStyle, axisLabelFontSize);

            Plot plot = chart.getPlot();

            CategoryPlot catPlot = (CategoryPlot) plot;
            catPlot.setColumnRenderingOrder(SortOrder.ASCENDING);
            catPlot.getDomainAxis().setLabelFont(axisFont);
            catPlot.getRangeAxis().setLabelFont(axisFont);

            if (!name.equals("RELATÓRIO DE EFETIVIDADE") && !name.equals("RELATÓRIO DE GERENCIAMENTO")) 
                catPlot.getRangeAxis().setRange(0, 100);

            catPlot.getDomainAxis().setTickLabelFont(axisTickFont);
            catPlot.getRangeAxis().setTickLabelFont(axisTickFont);
            catPlot.getDomainAxis().setMaxCategoryLabelWidthRatio(100);

            TextTitle title = chart.getTitle();
            title.setFont(TitleFont);

            if ( legend != null ) {
                 legend.setItemFont(legendFont);
                 legend.setAnchor(legendPosition);
            }

            BarRenderer barrenderer = (BarRenderer)catPlot.getRenderer();
            barrenderer.setMaxBarWidth(0.200000000000000003D);
            barrenderer.setMinimumBarLength(0.200000000000000003D);

            CategoryItemRenderer renderer = catPlot.getRenderer();
            CategoryLabelGenerator generator = 
                        new StandardCategoryLabelGenerator("{2}", new DecimalFormat("0.00"));
            renderer.setBaseItemLabelsVisible(true);
            renderer.setBaseItemLabelFont(labelFont);
            renderer.setPositiveItemLabelPosition( 
                        new ItemLabelPosition( ItemLabelAnchor.OUTSIDE1, TextAnchor.CENTER_RIGHT  ) );
            renderer.setLabelGenerator(generator);
            renderer.setItemLabelsVisible(true);
        }

        return chart;
    }
}