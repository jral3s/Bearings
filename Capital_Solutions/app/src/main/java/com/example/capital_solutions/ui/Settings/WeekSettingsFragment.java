package com.example.capital_solutions.ui.Settings;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.capital_solutions.Database.DatabaseHelper;
import com.example.capital_solutions.R;

public class WeekSettingsFragment extends Fragment {
    Button addButton, deleteButton, viewButton, updateButton;
    EditText weekEdit, foodEdit, clothesEdit, otherEdit;
    DatabaseHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_week_settings, container, false);

        weekEdit = (EditText) root.findViewById(R.id.weekEdit);
        foodEdit = (EditText) root.findViewById(R.id.foodEdit);
        clothesEdit = (EditText) root.findViewById(R.id.clothesEdit);
        otherEdit = (EditText) root.findViewById(R.id.otherEdit);

        addButton = (Button) root.findViewById(R.id.addButton);
        deleteButton = (Button) root.findViewById(R.id.deleteButton);
        viewButton = (Button) root.findViewById(R.id.viewButton);
        updateButton = (Button) root.findViewById(R.id.updateButton);

        db = new DatabaseHelper(getContext());
        AddData();
        ViewAll();
        UpdateData();
        DeleteData();

        /*notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
    }

    public void DeleteData(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                /*int deletedRows = */  db.deleteData(weekEdit.getText().toString());
                        /*if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();*/
                                            }
                                        }
        );
    }

    public void AddData(){
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.insertData(weekEdit.getText().toString(), foodEdit.getText().toString(),
                                clothesEdit.getText().toString(),
                                otherEdit.getText().toString());
                        /*if (isInserted == true) {
                            //Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        }
                        else {
                            //Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }*/
                    }
                }
        );
    }

    public void UpdateData(){
        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = db.updatedata(weekEdit.getText().toString(),
                                foodEdit.getText().toString(),
                                clothesEdit.getText().toString(), otherEdit.getText().toString());
                        /*if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();*/

                    }
                }
        );
    }

    public void ViewAll() {
        viewButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res= db.getAllData();
                        if (res.getCount() == 0){
                            //show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
//                        res.moveToPosition(1);
//                        buffer.append("Week: " + res.getString(0)+ "\n");
//                        buffer.append("Food: $" + res.getString(1)+ "\n");
//                        buffer.append("Clothes: $" + res.getString(2)+ "\n");
//                        buffer.append("Other: $" + res.getString(3)+ "\n\n");

                        while (res.moveToNext()) {
                            buffer.append("Week: " + res.getString(0)+ "\n");
                            buffer.append("Food: $" + res.getString(1)+ "\n");
                            buffer.append("Clothes: $" + res.getString(2)+ "\n");
                            buffer.append("Other: $" + res.getString(3)+ "\n\n");
                        }

                        //Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}

