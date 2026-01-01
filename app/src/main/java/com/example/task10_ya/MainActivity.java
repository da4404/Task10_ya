package com.example.task10_ya;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Your Name
 * @version 1.0
 * @since 1/1/2026
 * The main activity for the calculator application.
 */
public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener
{

    EditText etNum1;
    EditText etNum2;
    TextView tVoper;

    /**
     * Called when the activity is first created.
     * <p>
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.edit1);
        etNum2 = findViewById(R.id.edit2);
        tVoper = findViewById(R.id.text1);
        tVoper.setOnCreateContextMenuListener(this);

    }

    /**
     * Called when the context menu for a view is about to be shown.
     * <p>
     * Use this method to add items to the context menu.
     *
     * @param menu The context menu that is being built
     * @param v The view for which the context menu is being built
     * @param menuInfo Extra information about the item for which the context menu should be shown. This information will vary depending on the class of v.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("+");
        menu.add("-");
        menu.add("*");
        menu.add("/");
    }

    /**
     * This hook is called whenever an item in a context menu is selected.
     * <p>
     * The default implementation simply returns false to allow normal context menu processing to proceed.
     *
     * @param item The context menu item that was selected.
     * @return boolean Return false to allow normal context menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        String st = item.getTitle().toString();
        String str1 = etNum1.getText().toString();
        String str2 = etNum2.getText().toString();
        double num1 = 0;
        double num2 = 0;
        double res = 0;
            if (!isValidInput())
            {
                return true;
            }
            else
            {
                num1 = Double.parseDouble(str1);
                num2 = Double.parseDouble(str2);

                if (st.equals("+"))
                {

                    res = num1 + num2;

                } else if (st.equals("-"))
                {

                    res = num1 - num2;

                } else if (st.equals("*"))
                {
                    res = num1 * num2;
                }
                else if (st.equals("/"))
                {
                    if (num2 == 0)
                    {
                        tVoper.setText("dont do thattt");
                        return true;
                    }
                    res = num1 / num2;
                }
            }
            tVoper.setText(String.valueOf(formatNiceResult(res)));
        return super.onContextItemSelected(item);
    }

    /**
     * Formats a double to a string, with special formatting for large and small numbers.
     * <p>
     * This method handles scientific notation for very large/small values and removes decimals for integers.
     *
     * @param num The number to format.
     * @return String The formatted representation of the number.
     */
    public String formatNiceResult(double num)
    {
        if (num == 0)
        {
            return "0";
        }
        double absVal = Math.abs(num);
        if (absVal >= 10000000 || absVal <= 0.001)
        {
            return String.format("%.3E", num);
        }
        else if (num == (long) num)
        {
            return String.format("%d", (long) num);
        }
        else
        {
            return String.format("%.3f", num);
        }
    }

    /**
     * Validates the input in the EditText fields.
     * <p>
     *
     * @return boolean Return true if the input is valid, false otherwise.
     */
    public boolean isValidInput()
    {
        String str1 = etNum1.getText().toString();
        String str2 = etNum2.getText().toString();
        if (str1.isEmpty() || str2.isEmpty())
        {
            return false;
        }

        if (str1.equals(".") || str1.equals("-") || str2.equals(".") || str2.equals("-"))
        {
            return false;
        }
        return true;
    }
}