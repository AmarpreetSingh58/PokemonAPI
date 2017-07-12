package com.amarpreetsinghprojects.pokemonapi;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView name,weight,height,title;
    RecyclerView typeRCV;
    ImageView avatar;
    ArrayList<Types> typeArrayList = new ArrayList<>();
    PokeList pokeList;
    TypeAdapter adapter;
    int rank=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView)findViewById(R.id.name);
        title = (TextView)findViewById(R.id.title);
        weight = (TextView)findViewById(R.id.weight);
        height = (TextView)findViewById(R.id.height);
        avatar = (ImageView)findViewById(R.id.avatar);

        FloatingActionButton search = (FloatingActionButton)findViewById(R.id.searchbutton);
        final FloatingActionButton leftArrow = (FloatingActionButton)findViewById(R.id.leftbutton);
        FloatingActionButton rightArrow = (FloatingActionButton)findViewById(R.id.rightbutton);
        typeRCV = (RecyclerView)findViewById(R.id.typesRCV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new TypeAdapter(typeArrayList);
        typeRCV.setLayoutManager(layoutManager);
        typeRCV.setAdapter(adapter);

        networkCall(rank);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView f = (CardView) getLayoutInflater().inflate(R.layout.dialog_layout,null);
                final EditText searchedit = (EditText)f.findViewById(R.id.searchETV);
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Pokemon").setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                rank = Integer.parseInt(searchedit.getText().toString());
                                networkCall(rank);
                            }
                        })
                        .setView(f)
                        .show();
            }
        });
        if (rank <=1){
            leftArrow.setVisibility(View.GONE);
        }

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rank--;
                if (rank<=1){
                    leftArrow.setVisibility(View.GONE);

                }
                networkCall(rank);
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rank==1){
                    leftArrow.setVisibility(View.VISIBLE);
                }
                rank++;
                networkCall(rank);
            }
        });

    }

    public void setContents(PokeList contents){

        name.setText(contents.getName());
        title.setText(contents.getName());
        height.setText(contents.getHeight());
        weight.setText(contents.getWeight());
        Picasso.with(this).load(pokeList.getSprites().getFront_default()).resize(1000,1000).into(avatar);
        adapter.notifyDataSetChanged();


    }

    public void networkCall(int urlappend){
        String url = "http://pokeapi.co/api/v2/pokemon/"+urlappend+"/";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Toast.makeText(MainActivity.this,"Connection Failure",Toast.LENGTH_LONG);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                pokeList = gson.fromJson(result,PokeList.class);



                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        typeArrayList.clear();
                        typeArrayList.addAll(pokeList.getTypes());
                        setContents(pokeList);
                    }
                });

            }
        }
        );
    }
}
