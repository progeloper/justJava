package com.example.android.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity = 0;
    int perCup = 5;
    int price;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
        String summary;
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        calculatePrice(hasWhippedCream, hasChocolate);
        EditText name = (EditText) findViewById(R.id.name_editText);
        String customerName = name.getText().toString();
        if (quantity == 0){
            summary = "Free";
        }
        else
            summary = createOrderSummary(customerName, price, hasWhippedCream, hasChocolate);

        displayMessage(summary);
    }

    /**
     * This method calculates the price
     */
    private void calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        if(hasChocolate || hasWhippedCream)
            perCup += 1;
        else
            perCup = 5;
        price = perCup * quantity;
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        quantity += 1;
         display(quantity);
    }

    /**
     * This method is called when the decrement button is clicked
     */
    public void decrement(View view) {
        quantity -= 1;
        if (quantity <= 0){
            quantity = 0;
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price value on the screen
     * DEPRECATED
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(
                R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the price of the order
     */
    private void displayMessage(String message){
        TextView messageTextView = (TextView) findViewById( R.id.order_summary_text_view);
        messageTextView.setText(message);
    }

    /**
     * This method creates the order summary when called
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate){
        String summary;
        summary = name + "\nQuantity: " + quantity + "\nWhipped Cream: " + hasWhippedCream + "\nChocolate: " + hasChocolate + "\nTotal: $" + price + "\nThank You!";
        return summary;
    }
}