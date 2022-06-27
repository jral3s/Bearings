package com.example.capital_solutions.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.capital_solutions.R;
import com.example.capital_solutions.ui.MapFragment;
import com.example.capital_solutions.ui.Settings.SettingsFragment;

import java.text.DecimalFormat;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    DecimalFormat df;

    //data vars
    String budget, expense, budgetLeft;
    String selected;
    String food, clothes, other;
    String foodLeft, clothesLeft, otherLeft;
    double dBudgetLeft, dExpense;
    double dFood, dClothes, dOther;
    double foodSpent, clothesSpent, otherSpent;


    //Updating UI Elements
    Button expenseButton;
    EditText expenseText;
    TextView moneyLeftText, catLeftText;
    //TextView foodLeftText, clothesLeftText, otherLeftText;
    Spinner spinner;
    ImageButton settingsButton, mapButton;

    //Income Data
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String BudgetKey = "budgetKey";
    public static final String FoodKey = "foodKey";
    public static final String ClothesKey = "clothesKey";
    public static final String OtherKey = "otherKey";

    //Money Remaining Data
    public static final String MoneyLeftKey = "moneyLeft";
    public static final String FoodSpentKey = "foodSpent";
    public static final String FoodLeftKey = "foodLeft";
    public static final String ClothesSpentKey = "clothesSpent";
    public static final String ClothesLeftKey = "clothesLeft";
    public static final String OtherSpentKey = "otherSpent";
    public static final String OtherLeftKey = "otherLeft";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        /*dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);*/

        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        //updateDate(root);

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        df = new DecimalFormat("#.##");

        expenseButton = (Button) root.findViewById(R.id.expenseButton);
        expenseText = (EditText) root.findViewById(R.id.expenseText);
        moneyLeftText = (TextView) root.findViewById(R.id.moneyLeftText);
        catLeftText = (TextView) root.findViewById(R.id.catLeftText);
        //foodLeftText = (TextView) root.findViewById(R.id.foodLeftText);
        //clothesLeftText = (TextView) root.findViewById(R.id.clothesLeftText);
        //otherLeftText = (TextView) root.findViewById(R.id.otherLeftText);
        spinner = (Spinner) root.findViewById(R.id.spinner);

        budget = sharedPref.getString(BudgetKey, "0");
        food = sharedPref.getString(FoodKey, "0");
        clothes = sharedPref.getString(ClothesKey, "0");
        other = sharedPref.getString(OtherKey, "0");

        budgetLeft = sharedPref.getString(MoneyLeftKey, budget);
        foodLeft = sharedPref.getString(FoodLeftKey, food);
        clothesLeft = sharedPref.getString(ClothesLeftKey, clothes);
        otherLeft = sharedPref.getString(OtherLeftKey, other);

        dBudgetLeft = Double.valueOf(budgetLeft);
        dFood = Double.valueOf(foodLeft);
        dClothes = Double.valueOf(clothesLeft);
        dOther = Double.valueOf(otherLeft);

        moneyLeftText.setText(budgetLeft);

        //foodLeftText.setText(foodLeft);
        //clothesLeftText.setText(clothesLeft);
        //otherLeftText.setText(otherLeft);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              selected = spinner.getSelectedItem().toString();
              switch (selected) {
                  case "Food":
                      catLeftText.setText(foodLeft);
                      if(Double.valueOf(foodLeft) <= 0){catLeftText.setTextColor(Color.RED);}
                      else catLeftText.setTextColor(Color.BLACK);
                      break;
                  case "Clothes":
                      catLeftText.setText(clothesLeft);
                      if(Double.valueOf(clothesLeft) <= 0){catLeftText.setTextColor(Color.RED);}
                      else catLeftText.setTextColor(Color.BLACK);
                      break;
                  case "Other":
                      catLeftText.setText(otherLeft);
                      if(Double.valueOf(otherLeft) <= 0){catLeftText.setTextColor(Color.RED);}
                      else catLeftText.setTextColor(Color.BLACK);
                      break;
              }
          }

          public void onNothingSelected(AdapterView<?> adapterView) {
             return;
          }

      });



        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*expense = expenseText.getText().toString();
                dExpense = Float.valueOf(expense);
                dMoneyLeft -= dExpense;
                budgetLeft = String.valueOf(dMoneyLeft);
                if (budgetLeft.contains(".0")){ budget += '0'; }
                moneyLeftText.setText(budgetLeft);*/
                selected = spinner.getSelectedItem().toString();
                switch(selected){
                    case "Food":
                        expense = expenseText.getText().toString();
                        dExpense = Double.valueOf(expense);
                        dBudgetLeft -= dExpense;
                        dFood -= dExpense;

                        foodLeft = df.format(dFood);
                        budgetLeft = df.format(dBudgetLeft);

                        //foodLeftText.setText(foodLeft);
                        catLeftText.setText(foodLeft);
                        moneyLeftText.setText(budgetLeft);

                        foodSpent = Double.valueOf(sharedPref.getString(FoodSpentKey, "0"));
                        foodSpent += dExpense;

                        editor.putString(MoneyLeftKey, budgetLeft);
                        editor.putString(FoodLeftKey, foodLeft);
                        editor.putString(FoodSpentKey, df.format(foodSpent));
                        editor.commit();
                        break;
                    case "Clothes":
                        expense = expenseText.getText().toString();
                        dExpense = Double.valueOf(expense);
                        dBudgetLeft -= dExpense;
                        dClothes -= dExpense;

                        clothesLeft = df.format(dClothes);
                        budgetLeft = df.format(dBudgetLeft);

                        //clothesLeftText.setText(clothesLeft);
                        catLeftText.setText(clothesLeft);
                        moneyLeftText.setText(budgetLeft);

                        clothesSpent = Double.valueOf(sharedPref.getString(ClothesSpentKey, "0"));
                        clothesSpent += dExpense;

                        editor.putString(MoneyLeftKey, budgetLeft);
                        editor.putString(ClothesLeftKey, clothesLeft);
                        editor.putString(ClothesSpentKey, df.format(clothesSpent));
                        editor.commit();
                        break;
                    case "Other":
                        expense = expenseText.getText().toString();
                        dExpense = Double.valueOf(expense);
                        dBudgetLeft -= dExpense;
                        dOther -= dExpense;

                        otherLeft = df.format(dOther);
                        budgetLeft = df.format(dBudgetLeft);

                        //otherLeftText.setText(otherLeft);
                        catLeftText.setText(otherLeft);
                        moneyLeftText.setText(budgetLeft);

                        otherSpent = Double.valueOf(sharedPref.getString(OtherSpentKey, "0"));
                        otherSpent += dExpense;

                        editor.putString(MoneyLeftKey, budgetLeft);
                        editor.putString(OtherLeftKey, otherLeft);
                        editor.putString(OtherSpentKey, df.format(otherSpent));
                        editor.commit();
                        break;
                }
            }
        });

        settingsButton = (ImageButton) root.findViewById(R.id.settingsButton);
        mapButton = (ImageButton) root.findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new MapFragment());
                fr.commit();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new SettingsFragment());
                fr.commit();
            }
        });

        return root;
    }


    /*public void updateDate(View v){
        Date currentDate = Calendar.getInstance().getTime();
        String date = String.valueOf(currentDate);
        date = date.substring(4, 10);
        //TextView dateView = v.findViewById(R.id.dateView);
        //dateView.setText(date);
    }*/
}