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

public class MochaActivity extends AppCompatActivity {

    Button btnMochaSendOrder, btnMochaCancelOrder;
    EditText bwQty, marbleQty, webQty, zebraQty, whipQty;
    int bwAmount, marbleAmount, webAmount, zebraAmount, whipAmount;
    double bwPrice = 7.00,
            marblePrice = 6.00,
            webPrice = 9.00,
            zebraPrice = 8.00,
            whipPrice = 5.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mocha);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnMochaSendOrder = (Button) findViewById(R.id.btnMochaSendOrder);
        btnMochaCancelOrder = (Button) findViewById(R.id.btnMochaCancelOrder);
        bwQty = (EditText) findViewById(R.id.qtyBW);
        marbleQty = (EditText) findViewById(R.id.qtyMarble);
        webQty = (EditText) findViewById(R.id.qtyWeb);
        zebraQty = (EditText) findViewById(R.id.qtyZebra);
        whipQty = (EditText) findViewById(R.id.qtyWhipped);

        btnMochaCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnMochaSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bwAmount = Integer.parseInt(bwQty.getText().toString());
                marbleAmount = Integer.parseInt(marbleQty.getText().toString());
                webAmount = Integer.parseInt(webQty.getText().toString());
                zebraAmount = Integer.parseInt(zebraQty.getText().toString());
                whipAmount = Integer.parseInt(whipQty.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.putExtra("mocha1", new CoffeeOrder("Black & White Mocha", bwAmount, bwPrice));
                intent.putExtra("mocha2", new CoffeeOrder("Marble Mocha", marbleAmount, marblePrice));
                intent.putExtra("mocha3", new CoffeeOrder("Web Mocha", webAmount, webPrice));
                intent.putExtra("mocha4", new CoffeeOrder("Zebra Mocha", zebraAmount, zebraPrice));
                intent.putExtra("mocha5", new CoffeeOrder("Whipped Mocha", whipAmount, whipPrice));

                startActivity(intent);
            }
        });
    }
}