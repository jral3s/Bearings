package com.example.capital_solutions.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.capital_solutions.R;

import java.text.DecimalFormat;
import java.util.Calendar;

public class NotificationsFragment extends Fragment {


    TextView moneyLeftText, foodLeftText, clothesLeftText, otherLeftText, dayText;
    ProgressBar moneyProgress, foodProgress, clothesProgress, otherProgress;

    //Data Vars
    String budget, expense, budgetLeft;
    String food, clothes, other;
    String foodLeft, clothesLeft, otherLeft;
    double dBudget, dFood, dClothes, dOther;
    double dBudgetL, dFoodL, dClothesL, dOtherL;
    double pMoney, pFood, pClothes, pOther;
    int day;

    //Income Data
    public static final String MyPREFERENCES = "MyPrefs";
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
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final SharedPreferences sharedPref = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        DecimalFormat df = new DecimalFormat("#.##");

        moneyLeftText = (TextView) root.findViewById(R.id.moneyLeftText);
        foodLeftText = (TextView) root.findViewById(R.id.foodLeftText);
        clothesLeftText = (TextView) root.findViewById(R.id.clothesLeftText);
        otherLeftText = (TextView) root.findViewById(R.id.otherLeftText);
        dayText = (TextView) root.findViewById(R.id.dayText);

        moneyProgress = (ProgressBar) root.findViewById(R.id.moneyProgress);
        foodProgress = (ProgressBar) root.findViewById(R.id.foodProgress);
        clothesProgress = (ProgressBar) root.findViewById(R.id.clothesProgress);
        otherProgress = (ProgressBar) root.findViewById(R.id.otherProgress);

        budget = sharedPref.getString(BudgetKey, "0");
        food = sharedPref.getString(FoodKey, "0");
        clothes = sharedPref.getString(ClothesKey, "0");
        other = sharedPref.getString(OtherKey, "0");

        budgetLeft = sharedPref.getString(MoneyLeftKey, budget);
        foodLeft = sharedPref.getString(FoodLeftKey, food);
        clothesLeft = sharedPref.getString(ClothesLeftKey, clothes);
        otherLeft = sharedPref.getString(OtherLeftKey, other);

        dBudget = Double.valueOf(budget);
        dFood = Double.valueOf(food);
        dClothes = Double.valueOf(clothes);
        dOther = Double.valueOf(other);

        dBudgetL = Double.valueOf(budgetLeft);
        dFoodL = Double.valueOf(foodLeft);
        dClothesL = Double.valueOf(clothesLeft);
        dOtherL = Double.valueOf(otherLeft);

        pMoney = (1 - dBudgetL / dBudget) * 100;
        pFood = (1 - dFoodL / dFood) * 100;
        pClothes = (1 - dClothesL / dClothes) * 100;
        pOther = (1 - dOtherL / dOther) * 100;

        String moneySpent = df.format(dBudget-dBudgetL);
        String foodSpent = df.format(dFood-dFoodL);
        String clothesSpent = df.format(dClothes-dClothesL);
        String otherSpent = df.format(dOther-dOtherL);

        moneyProgress.setProgress((int) pMoney);
        foodProgress.setProgress((int) pFood);
        clothesProgress.setProgress((int) pClothes);
        otherProgress.setProgress((int) pOther);
        //moneyProgress.setProgressTintList(ColorStateList.valueOf(Color.BLUE)); //Error Based of Min API, make sure API is 21+
        setColor(moneyProgress, pMoney);
        setColor(foodProgress, pFood);
        setColor(clothesProgress, pClothes);
        setColor(otherProgress, pOther);

        setTextColor(moneyLeftText, pMoney);
        setTextColor(foodLeftText, pFood);
        setTextColor(clothesLeftText, pClothes);
        setTextColor(otherLeftText, pOther);


        String pMoneyT = df.format(pMoney);
        String pFoodT = df.format(pFood);
        String pClothesT = df.format(pClothes);
        String pOtherT = df.format(pOther);

        moneyLeftText.setText("$" + moneySpent + " / $" + budget + " ($" + budgetLeft + ")");
        foodLeftText.setText("$" + foodSpent + " / $" + food + " ($" + foodLeft + ")");
        clothesLeftText.setText("$" + clothesSpent + " / $" + clothes + " ($" + clothesLeft + ")");
        otherLeftText.setText("$" + otherSpent + " / $" + other + " ($" + otherLeft + ")");

        Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_WEEK);
        dayText.setText(String.valueOf(8 - day));


        return root;
    }

    private void setColor(ProgressBar pb, double p) { //Progress Bar and Progress
        //Error Based of Min API, make sure API is 21+, IGNORE
        if (p > 75) {pb.setProgressTintList(ColorStateList.valueOf(Color.RED)); }
        else if (p < 25) {pb.setProgressTintList(ColorStateList.valueOf(Color.GREEN));}
        else pb.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
    }
    private void setTextColor(TextView tv, double p){
        if (p > 100) {tv.setTextColor(Color.RED);}
        else if (p == 0) {tv.setTextColor(Color.GREEN);}
        else tv.setTextColor(Color.BLACK);
    }
}