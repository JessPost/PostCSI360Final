package com.example.postcsi360final;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ReceiptActivity extends AppCompatActivity {

    TextView orderDetails, subtotalCost, taxCost, totalCost;
    DecimalFormat df = new DecimalFormat("0.00");
    List<CoffeeOrder> customerOrderSubmitted = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receipt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        orderDetails = (TextView) findViewById(R.id.orderDetails);
        subtotalCost = (TextView) findViewById(R.id.subtotalCost);
        taxCost = (TextView) findViewById(R.id.taxCost);
        totalCost = (TextView) findViewById(R.id.totalCost);

        customerReceipt();
    }

    private void customerReceipt() {
        customerOrderSubmitted = getIntent().getParcelableArrayListExtra("drinkOrderList");

        if (customerOrderSubmitted != null && !customerOrderSubmitted.isEmpty()) {
            double taxRate = 0.10;
            double total = 0;
            double grandTotal;
            int orderNumber = 1;
            boolean selectionMade = false;

            for (CoffeeOrder coffee : customerOrderSubmitted) {
                if (coffee.getQuantity() > 0) {
                    selectionMade = true;
                    double itemTotal = coffee.getTotal() * coffee.getQuantity();

                    putOrderDetailsToReceipt(orderNumber, coffee, itemTotal);

                    total += itemTotal;
                    orderNumber++;
                }
            }

            if(selectionMade){
                double tax = taxRate * total;
                grandTotal = total + tax;

                subtotalCost.setText("Subtotal: $" + df.format(total));
                taxCost.setText("Tax (10%): $" + df.format(tax));
                totalCost.setText("Total: $" + df.format(grandTotal));
            }
            else {
                orderDetails.setText("Please select your coffee.");
            }
        }
        else {
            orderDetails.setText("Please select your coffee.");
        }
    }

    private void putOrderDetailsToReceipt(int orderNumber, CoffeeOrder coffee, double itemTotal) {
        orderDetails.append(orderNumber + ".)   " + coffee.getDescription() + " ( qty: "
                + coffee.getQuantity() + " )\n"
                + "         Total @ $" + df.format(coffee.getTotal()) + " each:\n"
                + "           $" + df.format(itemTotal) + "\n\n");
    }
}