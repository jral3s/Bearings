package com.example.capital_solutions.ui.Settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.capital_solutions.R;
import com.example.capital_solutions.ui.home.HomeViewModel;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class SettingsFragment extends Fragment {

    Button updateButton, saveButton, bankButton;
    TextView budgetText, foodText, clothesText, otherText;
    EditText incomeEdit, housingEdit, transportationEdit, savingsEdit;
    EditText foodEdit, clothesEdit, otherEdit;
    String income, housing, transportation, savings, budget;
    String food, clothes, other;
    double dincome, dhousing, dtransportation, dsavings, dbudget, expenses;
    double pfood, pclothes, pother, dfood, dclothes, dother;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String IncomeKey = "incomeKey";
    public static final String HousingKey = "housingKey";
    public static final String TransportationKey = "transportationKey";
    public static final String SavingsKey = "savingsKey";
    public static final String BudgetKey = "budgetKey";
    public static final String FoodKey = "foodKey";
    public static final String ClothesKey = "clothesKey";
    public static final String OtherKey = "otherKey";

    //Money Remaining Data
    DecimalFormat df;

    public static final String MoneyLeftKey = "moneyLeft";
    public static final String FoodSpentKey = "foodSpent";
    public static final String FoodLeftKey = "foodLeft";
    public static final String ClothesSpentKey = "clothesSpent";
    public static final String ClothesLeftKey = "clothesLeft";
    public static final String OtherSpentKey = "otherSpent";
    public static final String OtherLeftKey = "otherLeft";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        updateButton = (Button) root.findViewById(R.id.updateButton);
        saveButton = (Button) root.findViewById(R.id.saveButton);
        bankButton = (Button) root.findViewById(R.id.bankButton);

        budgetText = (TextView) root.findViewById(R.id.budgetText);
        foodText = (TextView) root.findViewById(R.id.foodText);
        clothesText = (TextView) root.findViewById(R.id.clothesText);
        otherText = (TextView) root.findViewById(R.id.otherText);

        incomeEdit = (EditText) root.findViewById(R.id.incomeEdit);
        housingEdit = (EditText) root.findViewById(R.id.housingEdit);
        transportationEdit = (EditText) root.findViewById(R.id.transportationEdit);
        savingsEdit = (EditText) root.findViewById(R.id.savingsEdit);

        foodEdit = (EditText) root.findViewById(R.id.foodEdit);
        clothesEdit = (EditText) root.findViewById(R.id.clothesEdit);
        otherEdit = (EditText) root.findViewById(R.id.otherEdit);

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        df = new DecimalFormat("#.##");

        setUp(sharedPref);

        bankButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showMessage("Connect to Bank Account", "Coming Soon!");
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(isEmpty(incomeEdit) || isEmpty(housingEdit) || isEmpty(transportationEdit) || isEmpty(savingsEdit))) {
                    //get inputs
                    income = incomeEdit.getText().toString();
                    housing = housingEdit.getText().toString();
                    transportation = transportationEdit.getText().toString();
                    savings = savingsEdit.getText().toString();

                    //convert to doubles
                    dincome = Double.valueOf(income);
                    dhousing = Double.valueOf(housing);
                    dtransportation = Double.valueOf(transportation);
                    dsavings = Double.valueOf(savings);

                    //do calculations
                    expenses = dhousing + dtransportation;
                    dbudget = (dincome - expenses - dsavings)/4; //NOW IS WEEKLY BUDGET

                    //convert back to string
                    budget = df.format(dbudget);

                    //send output
                    budgetText.setText(budget);
                }

                if(!(isEmpty(foodEdit) || isEmpty(clothesEdit) || isEmpty(otherEdit))){
                    //get inputs
                    food = foodEdit.getText().toString();
                    clothes = clothesEdit.getText().toString();
                    other = otherEdit.getText().toString();

                    //convert to doubles
                    dfood = Double.valueOf(food);
                    dclothes = Double.valueOf(clothes);
                    dother = Double.valueOf(other);

                    //turn into probabilities
                    pfood = dfood * 0.01;
                    pclothes = dclothes * 0.01;
                    pother = dother * 0.01;

                    //multiply income by p*** to get cash
                    dfood = pfood * dbudget;
                    dclothes = pclothes * dbudget;
                    dother = pother * dbudget;

                    //convert back to string
                    food = df.format(dfood);
                    clothes = df.format(dclothes);
                    other = df.format(dother);

                    //send output
                    foodText.setText(food);
                    clothesText.setText(clothes);
                    otherText.setText(other);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stores budgeting info
                editor.putString(IncomeKey, income);
                editor.putString(HousingKey, housing);
                editor.putString(TransportationKey, transportation);
                editor.putString(SavingsKey, savings);
                editor.putString(BudgetKey, budget);
                editor.putString(FoodKey, food);
                editor.putString(ClothesKey, clothes);
                editor.putString(OtherKey, other);

                //reset all other info
                editor.putString(MoneyLeftKey, budget);
                editor.putString(FoodLeftKey, food);
                editor.putString(ClothesLeftKey, clothes);
                editor.putString(OtherLeftKey, other);
                editor.putString(FoodSpentKey, "0");
                editor.putString(ClothesSpentKey, "0");
                editor.putString(OtherSpentKey, "0");
                editor.commit();
            }
        });

        return root;
    }

    private boolean isEmpty(EditText etText) {
        //https://stackoverflow.com/questions/6290531/check-if-edittext-is-empty
        return etText.getText().toString().trim().length() == 0;
    }

    public void setUp(SharedPreferences sp){

        String i,h,t,s,b,f,c,o; //string for each of the TextViews, i = income, etc
        i = sp.getString(IncomeKey, "0");
        h = sp.getString(HousingKey, "0");
        t = sp.getString(TransportationKey, "0");
        s = sp.getString(SavingsKey, "0");
        b = sp.getString(BudgetKey, "0");
        f = sp.getString(FoodKey, "0");
        c = sp.getString(ClothesKey, "0");
        o = sp.getString(OtherKey, "0");

        income = i;
        housing = h;
        transportation = t;
        savings = s;
        budget = b;
        clothes = c;
        other = o;

        dincome = Double.valueOf(income);
        dhousing = Double.valueOf(housing);
        dtransportation = Double.valueOf(transportation);
        dsavings = Double.valueOf(savings);
        dbudget = Double.valueOf(budget);

        budgetText.setText(b);
        foodText.setText(f);
        clothesText.setText(c);
        otherText.setText(o);
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

