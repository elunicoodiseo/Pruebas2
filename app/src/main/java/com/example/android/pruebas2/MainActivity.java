package com.example.android.pruebas2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;
    TextView quantityTextView;
    //TextView priceTextView;
    CheckBox whipped_cream;
    CheckBox chocolate;
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        //priceTextView = (TextView) findViewById(R.id.price_text_view);
        quantity = Integer.parseInt(quantityTextView.getText().toString());
        whipped_cream = (CheckBox) findViewById(R.id.whipped_cream);
        chocolate = (CheckBox) findViewById(R.id.chocolate);
        nameField = (EditText) findViewById(R.id.name);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int price = 5;
        String priceMessage = "";
        String name = nameField.getText().toString().trim();
        if(name.length()>0) {
            priceMessage += getString(R.string.order_summary_name,name)+"\n";
        }
        if (whipped_cream.isChecked()) {
            priceMessage += "\n"+getString(R.string.order_summary_whipped_cream,whipped_cream.isChecked());
            price += 1;
        }
        if (chocolate.isChecked()) {
            priceMessage += "\n"+getString(R.string.order_summary_chocolate,chocolate.isChecked());
            price += 2;
        }
        priceMessage += "\n"+getString(R.string.order_summary_quantity,quantity);
        priceMessage += "\n"+getString(R.string.order_summary_price,quantity*price);
        priceMessage += "\n"+getString(R.string.thank_you);

        String subject = "Order for: " + name,body=priceMessage;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    /*
    private void display() {
        quantityTextView.setText("" + quantity);
    }*/

    /**
     * This method displays the given price on the screen.
     */
    /*
    private void displayPrice(int price) {
        int number = quantity * price;
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    /**
     * This method displays the given price increment.
     */
    public void increment(View view) {
        //int number = 1;
        if(quantity+1<=100) {
            quantity++;
            //display();
        }else{
            Toast.makeText(this,"You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
        /*TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int numAct = Integer.parseInt(quantityTextView.getText().toString())+number;
        quantityTextView.setText("" + numAct);*/
    }

    /**
     * This method displays the given price decrement.
     */

    public void decrement(View view) {
        if(quantity-1>=1) {
            quantity--;
            //display();
        }else{
            Toast.makeText(this,"You cannot have few than 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * This method displays the given text on the screen.
     */
    /*
    private void displayMessage(String message) {
        priceTextView.setText(message);
    }*/
}