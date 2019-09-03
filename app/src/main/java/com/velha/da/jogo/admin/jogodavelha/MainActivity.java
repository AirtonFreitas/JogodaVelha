package com.velha.da.jogo.admin.jogodavelha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import static com.velha.da.jogo.admin.jogodavelha.R.*;
import static com.velha.da.jogo.admin.jogodavelha.R.drawable.*;


public class MainActivity extends Activity {
    private Button[] arrayButton = new Button[10];
    private String vez = "X";
    private int jogadas = 0;
    private String[] matriz = new String[10];
    private ImageView vezID;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        inicializaButtons();
        onClickButtons();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inicializaButtons();
                jogadas = 0;
            }
        });




    }

    private void inicializaButtons(){
        arrayButton[1] = (Button) findViewById(id.btn1);
        arrayButton[2] = (Button) findViewById(id.btn2);
        arrayButton[3] = (Button) findViewById(id.btn3);
        arrayButton[4] = (Button) findViewById(id.btn4);
        arrayButton[5] = (Button) findViewById(id.btn5);
        arrayButton[6] = (Button) findViewById(id.btn6);
        arrayButton[7] = (Button) findViewById(id.btn7);
        arrayButton[8] = (Button) findViewById(id.btn8);
        arrayButton[9] = (Button) findViewById(id.btn9);







        vezID = (ImageView) findViewById(id.vezID);

        resetButton = (Button) findViewById(id.resetID);


    }

    private void onClickButtons(){
        for (int x=1; x<10; x++){
            final int finalX = x;
            arrayButton[finalX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jogada(finalX);
                    if(arrayButton[finalX].getText().toString() == "X"){
                        arrayButton[finalX].setBackgroundResource(drawable.x);
                    }else{
                        arrayButton[finalX].setBackgroundResource(bolinha);
                    }



                }
            });
            matriz[x] = "";
        }

    }
    private void jogada(int x){
        if(matriz[x] == ""){
            matriz[x] = vez;
            jogadas++;
            if(vez == "X"){
                vez = "O";
                alteraImagemVez(vez);
            }else{
                vez = "X";
                alteraImagemVez(vez);
            }

        }
        exibirButons();
        verifica();
    }
    private void alteraImagemVez(String vez){
        if (this.vez == "X") {
            vezID.setBackgroundResource(drawable.x);
        }else{
            vezID.setBackgroundResource(drawable.bolinha);
        }

    }


    private void exibirButons(){
        for (int x = 1; x<10; x++){
            arrayButton[x].setText( matriz[x]);
            }
    }

    private void verifica(){
        if(matriz[1].equals(matriz[2]) && matriz[1].equals(matriz[3]) && matriz[1].toString() != ""){
            ganhador(matriz[1]);
            return;
        }
        if(matriz[1].equals(matriz[4]) && matriz[1].equals(matriz[7]) && matriz[1].toString() != ""){
            ganhador(matriz[1]);
            return;
        }
        if(matriz[2].equals(matriz[5]) && matriz[2].equals(matriz[8]) && matriz[2].toString() != ""){
            ganhador(matriz[2]);
            return;
        }
        if(matriz[3].equals(matriz[6]) && matriz[3].equals(matriz[9]) && matriz[3].toString() != ""){
            ganhador(matriz[3]);
            return;
        }
        if(matriz[1].equals(matriz[5]) && matriz[1].equals(matriz[9]) && matriz[1].toString() != ""){
            ganhador(matriz[1]);
            return;
        }
        if(matriz[3].equals(matriz[5]) && matriz[3].equals(matriz[7]) && matriz[3].toString() != ""){
            ganhador(matriz[3]);
            return;
        }
        if(matriz[7].equals(matriz[8]) && matriz[7].equals(matriz[9]) && matriz[7].toString() != ""){
            ganhador(matriz[7]);
            return;
        }
        if(matriz[4].equals(matriz[5]) && matriz[4].equals(matriz[6]) && matriz[4].toString() != ""){
            ganhador(matriz[4]);
            return;
        }
        if (jogadas == 9){
            ganhador("");
            return;
        }
    }


    private void ganhador(String ganhador){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Resultado");
        if(ganhador.equals("")){
            builder.setMessage("Empate");
        }else{
            if (ganhador.equals("X")){
                builder.setMessage("\"X\" é o vencedor");

            }else{
                builder.setMessage("\"O\" é o vencedor");
            }
        }
        builder.setPositiveButton("Novo Jogo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                jogadas = 0;
                for( int x = 1; x<10; x++){
                    matriz[x] = "";
                    arrayButton[x].setBackgroundResource(0);
                    vezID.setBackgroundResource(drawable.x);

                }
                exibirButons();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}



