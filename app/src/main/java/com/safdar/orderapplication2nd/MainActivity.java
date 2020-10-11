package com.safdar.orderapplication2nd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int quantity = 2;
    public String whippedYesNo = "";
    public String chocolateYesNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void orderSummary(View view) {

        CheckBox whippedCream = findViewById(R.id.checkbox_whippedCream);
        CheckBox chocolate = findViewById(R.id.checkbox_extraChocolate);
        EditText useName = findViewById(R.id.etName);
        String name = useName.getText().toString();
        Boolean hasWhippedCream = whippedCream.isChecked();
        Boolean hasChocolate = chocolate.isChecked();

        if (hasWhippedCream == true) {
            whippedYesNo = "Yes";
        } else {
            whippedYesNo = "No";
        }

        /*whippedYesNo.contains("YES"); //true
        whippedYesNo.contains("Yes");*/
       /* whippedYesNo.equals("Yes"); //false
        whippedYesNo.equalsIgnoreCase("yes"); //case insensitive way to validate*/


        if (hasChocolate == true) {
            chocolateYesNo = "Yes";
        } else
            chocolateYesNo = "No";
        if (!name.isEmpty()) {
            String orderPrice = createOderSummary(quantity, hasWhippedCream, hasChocolate, name);
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "This is an order summary for Mr. " + name);
            intent.putExtra(Intent.EXTRA_TEXT, orderPrice);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            price(orderPrice);
            Toast.makeText(getApplicationContext(), "Order Successfully Placed!", Toast.LENGTH_SHORT).show();

        }else{
            useName.setError("Name Required!");
            Toast.makeText(getApplicationContext(), "Please Enter you name", Toast.LENGTH_SHORT).show();
        }
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.tv_Quantity);
        quantityTextView.setText("" + number);
    }

    private void price(String value) {
        TextView price = findViewById(R.id.tv_price);
        price.setText("" + value);
    }

    public void increment(View view) {
        quantity += 1;
        display(quantity);

    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity -= 1;
            display(quantity);
        }
    }

    private String createOderSummary(int orderQuantity, Boolean hasWhippedCream, Boolean hasChocolate, String userName) {

        CheckBox cream = findViewById(R.id.checkbox_whippedCream);
        CheckBox chocolate = findViewById(R.id.checkbox_extraChocolate);


        String orderPrice;
        orderPrice = "Name: " + userName;
        orderPrice += "\nQuantity: " + quantity;
        orderPrice += "\nDo you want Whipped Cream? " + (cream.isChecked() ? "yes" : "no");
        orderPrice += "\nDo you want chocolate? " + (chocolate.isChecked() ? "yes" : "no");

        /*if (!hasChocolate && !hasWhippedCream) {

            orderPrice += "\nTotal amount: $" + (number * 5) + "\nThanks!";

        } else if (hasWhippedCream && hasChocolate) {
            orderPrice += "\nTotal amount: $" + (number * 5 + 2 + 3) + "\nThanks!";

        } else if (hasWhippedCream && !hasChocolate) {
            orderPrice += "\nTotal amount: $" + (number * 5 + 2) + "\nThanks!";

        } else if (!hasWhippedCream && hasChocolate) {
            orderPrice += "\nTotal amount: $" + (number * 5 + 3) + "\nThanks!";
            Log.i("price", "" + orderPrice);
        }*/

        //always have descriptive meaning for variables
        int extraCreamPrice = cream.isChecked() ? 2 : 0;
        int extraChocolate = chocolate.isChecked() ? 3 : 0;
        orderPrice += "\nTotal amount: $" + ((orderQuantity * 5) + extraChocolate + extraCreamPrice) + "\nThanks!";


        return orderPrice;

    }

}