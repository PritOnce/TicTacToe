package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textVictoria;
    Integer[] botones;
    int[] tablero = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };

    int state = 0;
    int fichasColocadas = 0;

    int turno = 1;
    int[] posicionGanadora = new int[]{-1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textVictoria = findViewById(R.id.textView);
        textVictoria.setVisibility(View.INVISIBLE);
        botones = new Integer[]{
                R.id.b1, R.id.b2, R.id.b3,
                R.id.b4, R.id.b5, R.id.b6,
                R.id.b7, R.id.b8, R.id.b9
        };
    }

    public void setFicha(View v){
        if(state == 0){
            turno = 1;
            int numBoton = Arrays.asList(botones).indexOf(v.getId());
            if(tablero[numBoton] == 0){
                v.setBackgroundResource(R.drawable.o);
                tablero[numBoton] = 1;
                fichasColocadas += 1;
                state = checkState();
                finishGame();
                if(state == 0){
                    turno = -1;
                    bot();
                    fichasColocadas += 1;
                    state = checkState();
                    finishGame();
                }
            }
        }
    }

    public void bot(){
        Random random = new Random();
        int posicion = random.nextInt(tablero.length);

        while(tablero[posicion] != 0){
            posicion = random.nextInt(tablero.length);
        }
        Button button = findViewById(botones[posicion]);
        button.setBackgroundResource(R.drawable.x);
        tablero[posicion] = -1;
    }

    public void finishGame(){
        int fichaVictoria = R.drawable.o_victoria;
        if(state == 1 || state == -1){
            if(state == 1){
                textVictoria.setVisibility(View.VISIBLE);
            }else {
                textVictoria.setVisibility(View.VISIBLE);
                textVictoria.setText("YOU LOSE");
                textVictoria.setTextColor(Color.RED);
                fichaVictoria = R.drawable.x_victoria;
            }
            for(int i = 0; i < posicionGanadora.length; i++){
                Button button = findViewById(botones[posicionGanadora[i]]);
                button.setBackgroundResource(fichaVictoria);
            }
        } else if (state == 2) {
            textVictoria.setVisibility(View.VISIBLE);
            textVictoria.setText("HAS EMPATADO");
        }
    }

    public int checkState(){
        int newState = 0;
        if(Math.abs(tablero[0] + tablero[1] + tablero[2]) == 3){
            posicionGanadora = new int[]{0, 1, 2};
            newState = 1*turno;
        } else if (Math.abs(tablero[3] + tablero[4] + tablero[5]) == 3) {
            posicionGanadora = new int[]{3, 4, 5};
            newState = 1*turno;
        } else if (Math.abs(tablero[6] + tablero[7] + tablero[8]) == 3) {
            posicionGanadora = new int[]{6, 7, 8};
            newState = 1*turno;
        } else if (Math.abs(tablero[0] + tablero[4] + tablero[8]) == 3) {
            posicionGanadora = new int[]{0, 4, 8};
            newState = 1*turno;
        } else if (Math.abs(tablero[0] + tablero[3] + tablero[6]) == 3) {
            posicionGanadora = new int[]{0, 3, 6};
            newState = 1*turno;
        } else if (Math.abs(tablero[1] + tablero[4] + tablero[7]) == 3) {
            posicionGanadora = new int[]{1, 4, 7};
            newState = 1*turno;
        } else if (Math.abs(tablero[2] + tablero[4] + tablero[6]) == 3) {
            posicionGanadora = new int[]{2, 4, 6};
            newState = 1*turno;
        } else if (Math.abs(tablero[2] + tablero[5] + tablero[8]) == 3) {
            posicionGanadora = new int[]{2, 5, 8};
            newState = 1*turno;
        } else if (fichasColocadas == 9) {
            newState = 2;
        }
        return newState;
    }
}