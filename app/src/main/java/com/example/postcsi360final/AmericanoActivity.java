package com.example.postcsi360final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AmericanoActivity extends AppCompatActivity {

    Button btnAmerSendOrder, btnAmerCancelOrder;
    EditText decafQty, traditionalQty, icedQty, smallQty, shakenQty;
    int decafAmount, traditionalAmount, icedAmount, smallAmount, shakenAmount;
    double decafPrice = 2.00,
            traditionalPrice = 3.00,
            icedPrice = 4.00,
            smallPrice = 5.00,
            shakenPrice = 10.00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_americano);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAmerSendOrder = (Button) findViewById(R.id.btnAmerSendOrder);
        btnAmerCancelOrder = (Button) findViewById(R.id.btnAmerCancelOrder);
        decafQty = (EditText) findViewById(R.id.qtyDecaf);
        traditionalQty = (EditText) findViewById(R.id.qtyTraditional);
        icedQty = (EditText) findViewById(R.id.qtyIced);
        smallQty = (EditText) findViewById(R.id.qtySmall);
        shakenQty = (EditText) findViewById(R.id.qtyShaken);


        btnAmerCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnAmerSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decafAmount = Integer.parseInt(decafQty.getText().toString());
                traditionalAmount = Integer.parseInt(traditionalQty.getText().toString());
                icedAmount = Integer.parseInt(icedQty.getText().toString());
                smallAmount = Integer.parseInt(smallQty.getText().toString());
                shakenAmount = Integer.parseInt(shakenQty.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.putExtra("amer1", new CoffeeOrder("Decaf Americano", decafAmount, decafPrice));
                intent.putExtra("amer2", new CoffeeOrder("Traditional Americano", traditionalAmount, traditionalPrice));
                intent.putExtra("amer3", new CoffeeOrder("Iced Americano", icedAmount, icedPrice));
                intent.putExtra("amer4", new CoffeeOrder("Small Americano", smallAmount, smallPrice));
                intent.putExtra("amer5", new CoffeeOrder("Shaken Americano", shakenAmount, shakenPrice));

                startActivity(intent);
            }
        });
    }
}