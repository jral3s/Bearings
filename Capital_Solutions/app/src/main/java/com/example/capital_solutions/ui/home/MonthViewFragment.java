package com.example.capital_solutions.ui.home;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.graphics.Color;

import com.example.capital_solutions.Database.DatabaseHelper;
import com.example.capital_solutions.Database.DatabaseHelper2;
import com.example.capital_solutions.R;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MonthViewFragment extends Fragment {

    DatabaseHelper db1;
    DatabaseHelper2 db2;
    Button viewButton1, viewButton2, viewButton3;
    float w1f, w1c, w1o;
    float w2f, w2c, w2o;
    float w3f, w3c, w3o;
    int w1fC, w1cC, w1oC;
    int w2fC, w2cC, w2oC;
    int w3fC, w3cC, w3oC;


    BarChart stackedChart;

    int[] colorLegendArray = new int[]{Color.RED, Color.BLUE, Color.GREEN};
    int[] colorClassArray0 = new int[]{Color.RED, Color.BLUE, Color.RED};
    int[] colorClassArray1 = new int[]{Color.GREEN, Color.RED, Color.BLUE};
    int[] colorClassArray2 = new int[]{Color.GREEN, Color.BLUE, Color.GREEN};
    //int[] colorClassArray3 = new int[]{Color.GREEN, Color.BLUE, Color.GREEN};


    float budget = 360f;
    String[] categoryArray = new String[]{"Bad", "On Target", "Good"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_month_view, container, false);
        db1=new DatabaseHelper(getContext());
        db2=new DatabaseHelper2(getContext());

        viewButton1 = (Button)root.findViewById(R.id.week1Button);
        viewButton2 = (Button) root.findViewById(R.id.week2Button);
        viewButton3 = (Button) root.findViewById(R.id.week3Button);

        for(int i = 1; i < 8; i++){
            db2.deleteData(String.valueOf(i));
        }
        db2.insertData();
        db2.updateData();
        db2.updateData2();
        db2.updateData3();
        view1();
        view2();
        view3();
        Cursor res = db1.getAllData();

        res.moveToPosition(0);
        w1f = Float.valueOf(res.getString(1));
        w1c = Float.valueOf(res.getString(2));
        w1o = Float.valueOf(res.getString(3));
        res.moveToPosition(1);
        w2f = Float.valueOf(res.getString(1));
        w2c = Float.valueOf(res.getString(2));
        w2o = Float.valueOf(res.getString(3));
        res.moveToPosition(2);
        w3f = Float.valueOf(res.getString(1));
        w3c = Float.valueOf(res.getString(2));
        w3o = Float.valueOf(res.getString(3));

        stackedChart = root.findViewById(R.id.stackedbar);

        setColors();
        //colorClassArray0 = new int[]{Color.RED, Color.BLUE, Color.RED};
        colorClassArray0 = new int[]{w1oC, w1cC, w1fC};
        //colorClassArray1 = new int[]{Color.GREEN, Color.RED, Color.BLUE};
        colorClassArray1 = new int[]{w2oC, w2cC, w2fC};
        //colorClassArray2 = new int[]{Color.GREEN, Color.BLUE, Color.GREEN};
        colorClassArray2 = new int[]{w3oC, w3cC, w3fC};


        ArrayList<BarEntry> barEntries = new ArrayList<>();

        ArrayList<BarEntry> barEntries0 = new ArrayList<>();
        //barEntries0.add(new BarEntry(new float[]{170, 35, 220}, 0));
        barEntries0.add(new BarEntry(new float[]{w1o, w1c, w1f}, 0)); //Input fields backwards (whoops)

        ArrayList<BarEntry> barEntries1 = new ArrayList<>();
        //barEntries1.add(new BarEntry(new float[]{140, 70, 160}, 1));
        barEntries1.add(new BarEntry(new float[]{w2o, w2c, w2f}, 1));

        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
//        barEntries2.add(new BarEntry(new float[]{110, 38, 140}, 2));
        barEntries2.add(new BarEntry(new float[]{w3o, w3c, w3f}, 2));



        BarDataSet barDataSet0 = new BarDataSet(barEntries0, "");
        barDataSet0.setDrawValues(false);
        barDataSet0.setColors(colorClassArray0);
        barDataSet0.setBarSpacePercent(-8500f);

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "");
        barDataSet1.setDrawValues(false);
        barDataSet1.setColors(colorClassArray1);
        barDataSet1.setBarSpacePercent(-8500f);

        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "");
        barDataSet2.setDrawValues(false);
        barDataSet2.setColors(colorClassArray2);
        barDataSet2.setBarSpacePercent(-8500f);



        barEntries.addAll(barEntries0);
        barEntries.addAll(barEntries1);
        barEntries.addAll(barEntries2);

        BarData theData = new BarData();

        theData.addDataSet(barDataSet0);
        theData.addDataSet(barDataSet1);
        theData.addDataSet(barDataSet2);
        theData.addXValue("Week 1");
        theData.addXValue("Week 2");
        theData.addXValue("Week 3");
        theData.addXValue("Week 4");
        theData.setGroupSpace(11200);

        stackedChart.setData(theData);

        setFormat();


        return root;
    }

    private void setColors(){
        w1fC = pickColors(w1f, 'f');
        w2fC = pickColors(w2f, 'f');
        w3fC = pickColors(w3f, 'f');
        w1cC = pickColors(w1c, 'c');
        w2cC = pickColors(w2c, 'c');
        w3cC = pickColors(w3c, 'c');
        w1oC = pickColors(w1o, 'o');
        w2oC = pickColors(w2o, 'o');
        w3oC = pickColors(w3o, 'o');
    }

    private int pickColors(float $, char cat) {
        switch(cat){
            case 'f':
                if($ < 162){return Color.GREEN;}
                else if($ > 198){return Color.RED;}
                else return Color.BLUE;
                //break;
            case 'c':
                if($ < 32.4){return Color.GREEN;}
                else if($ > 39.6){return Color.RED;}
                else return Color.BLUE;
                //break;
            case 'o':
                if($ < 129.6){return Color.GREEN;}
                else if($ > 158.4){return Color.RED;}
                else return Color.BLUE;
                //break;
        }
        return 0;
    }


    public void view1() {
            viewButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor res= db2.getAllData();
                    if (res.getCount() == 0){
                        //show message
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Day: " + res.getString(0)+ "\n");
                        buffer.append("Food: $" + res.getString(1)+ "\n");
                        buffer.append("Clothing: $" + res.getString(2)+ "\n");
                        buffer.append("Other: $" + res.getString(3)+ "\n\n");
                    }

                    //Show all data
                    showMessage("Week 1", buffer.toString());
                }
            });
    }

    public void view2() {
            viewButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor res= db2.getAllData2();
                    if (res.getCount() == 0){
                        //show message
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Day: " + res.getString(0)+ "\n");
                        buffer.append("Food: $" + res.getString(1)+ "\n");
                        buffer.append("Clothing: $" + res.getString(2)+ "\n");
                        buffer.append("Other: $" + res.getString(3)+ "\n\n");
                    }

                    //Show all data
                    showMessage("Week 2", buffer.toString());
                }
            });
    }

    public void view3() {
            viewButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor res= db2.getAllData3();
                    if (res.getCount() == 0){
                        //show message
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Day: " + res.getString(0)+ "\n");
                        buffer.append("Food: $" + res.getString(1)+ "\n");
                        buffer.append("Clothing: $" + res.getString(2)+ "\n");
                        buffer.append("Other: $" + res.getString(3)+ "\n\n");
                    }

                    //Show all data
                    showMessage("Week 3", buffer.toString());
                }
            });
    }



    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void setFormat() {
        stackedChart.setScaleEnabled(true);
        stackedChart.setDragEnabled(true);



        YAxis leftAxis = stackedChart.getAxisLeft();
        LimitLine ll = new LimitLine(budget, "Budget");
        ll.setLineColor(Color.BLACK);
        ll.setLineWidth(4f);
        ll.setTextColor(Color.BLACK);
        ll.setTextSize(12f);
        leftAxis.addLimitLine(ll);


        XAxis xAxis = stackedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setTextSize(11f);


        YAxis left = stackedChart.getAxisLeft();
        left.setTextSize(11f);
        left.setDrawZeroLine(true);
        left.setZeroLineWidth(2f);
        stackedChart.getAxisRight().setEnabled(false);
        left.setAxisMinValue(0f);

        stackedChart.animateY(1500);

        Legend legend = stackedChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        legend.setCustom(colorLegendArray, categoryArray);

        stackedChart.setDescription("Top: Food \n\n\n\nMiddle: Clothes \n\n\n\nBottom: Other");
        stackedChart.setDescriptionPosition(650,90);
        stackedChart.setDescriptionTextSize(10f);
        stackedChart.invalidate();

    }

}
