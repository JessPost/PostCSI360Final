package com.example.postcsi360final;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnPlaceOrder, btnViewOrder;
    List<CoffeeOrder> customerOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnViewOrder = (Button) findViewById(R.id.btnViewOrder);

        registerForContextMenu(btnPlaceOrder);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(v);
            }
        });

        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewReceipt();
            }
        });

        if (getIntent().getExtras() != null) {
            for (int i = 1; i <= 5; i++) {
                CoffeeOrder order = getIntent().getParcelableExtra("amer" + i);
                if (order != null) {
                    customerOrder.add(order);
                }
                order = getIntent().getParcelableExtra("latte" + i);
                if (order != null) {
                    customerOrder.add(order);
                }
                order = getIntent().getParcelableExtra("mocha" + i);
                if (order != null) {
                    customerOrder.add(order);
                }
            }
        }
    }
    private void viewReceipt() {
        Intent intent = new Intent(getApplicationContext(), ReceiptActivity.class);
        intent.putParcelableArrayListExtra("drinkOrderList", new ArrayList<>(customerOrder));
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.coffee_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_latte) {
            Intent intent = new Intent(getApplicationContext(), LatteActivity.class);
            startActivity(intent);
            return true;
        }
        else if (itemId == R.id.menu_mocha) {
            Intent intent = new Intent(getApplicationContext(), MochaActivity.class);
            startActivity(intent);
            return true;
        }
        else if (itemId == R.id.menu_americano) {
            Intent intent = new Intent(getApplicationContext(), AmericanoActivity.class);
            startActivity(intent);
            return true;
        }
        else {
            return super.onContextItemSelected(item);
        }
    }
}