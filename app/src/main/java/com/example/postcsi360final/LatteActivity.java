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

public class LatteActivity extends AppCompatActivity {
    Button btnLatteSendOrder, btnLatteCancelOrder;
    EditText cinnamonQty, coconutQty, hazelnutQty, pslQty, vanillaQty;
    int cinnamonAmount, coconutAmount, hazelnutAmount, pslAmount, vanillaAmount;
    double cinnamonPrice = 8.00,
            coconutPrice = 9.00,
            hazelnutPrice = 5.00,
            pslPrice = 7.00,
            vanillaPrice = 4.00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_latte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLatteSendOrder = (Button) findViewById(R.id.btnLatteSendOrder);
        btnLatteCancelOrder = (Button) findViewById(R.id.btnLatteCancelOrder);
        cinnamonQty = (EditText) findViewById(R.id.qtyCinnamon);
        coconutQty = (EditText) findViewById(R.id.qtyCoconut);
        hazelnutQty = (EditText) findViewById(R.id.qtyHazelnut);
        pslQty = (EditText) findViewById(R.id.qtyPSL);
        vanillaQty = (EditText) findViewById(R.id.qtyVanilla);

        btnLatteCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnLatteSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cinnamonAmount = Integer.parseInt(cinnamonQty.getText().toString());
                coconutAmount = Integer.parseInt(coconutQty.getText().toString());
                hazelnutAmount = Integer.parseInt(hazelnutQty.getText().toString());
                pslAmount = Integer.parseInt(pslQty.getText().toString());
                vanillaAmount = Integer.parseInt(vanillaQty.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("latte1", new CoffeeOrder("Cinnamon Latte", cinnamonAmount, cinnamonPrice));
                intent.putExtra("latte2", new CoffeeOrder("Coconut Latte", coconutAmount, coconutPrice));
                intent.putExtra("latte3", new CoffeeOrder("Hazelnut Latte", hazelnutAmount, hazelnutPrice));
                intent.putExtra("latte4", new CoffeeOrder("PSL", pslAmount, pslPrice));
                intent.putExtra("latte5", new CoffeeOrder("Vanilla Latte", vanillaAmount, vanillaPrice));

                startActivity(intent);
            }
        });
    }
}