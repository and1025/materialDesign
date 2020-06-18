
package com.example.carrosmaterial;
import android.content.Intent;
import android.os.Bundle;

import com.example.carrosmaterial.AdaptadorCarro;
import com.example.carrosmaterial.AgregarCarro;
import com.example.carrosmaterial.Carro;
import com.example.carrosmaterial.Datos;
import com.example.carrosmaterial.DetalleCarro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements AdaptadorCarro.OnCarroClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(MainActivity.this);

        FloatingActionButton fab;
        RecyclerView lstCarros;
        final ArrayList<Carro> carros;
        LinearLayoutManager llm;
        final AdaptadorCarro adapter;

        // Cambio 1
        DatabaseReference databaseReference;
        String db = "Carros";
        lstCarros = findViewById(R.id.lstCarros);
        //cambio 2
        carros = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorCarro(carros, this);
        llm.setOrientation(RecyclerView.VERTICAL);
        lstCarros.setLayoutManager(llm);
        lstCarros.setAdapter(adapter);
        fab = findViewById(R.id.btnAgregar);

        //Cambio 3
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                carros.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Carro c = snapshot.getValue(Carro.class);
                        carros.add(c);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setCarros(carros);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void agregar(View v){
        Intent intent;
        intent = new Intent(MainActivity.this, AgregarCarro.class);
        startActivity(intent);
        //finish();
    }


    @Override
    public void onCarroClick(Carro c) {
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();

        bundle.putString("id", c.getId());
        bundle.putString("placa", c.getPlaca());
        bundle.putString("marca", c.getMarca());
        bundle.putString("color", c.getColor());

        intent = new Intent(MainActivity.this, DetalleCarro.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
        //finish();
    }
}
