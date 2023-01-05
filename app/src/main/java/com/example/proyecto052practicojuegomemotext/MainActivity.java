package com.example.proyecto052practicojuegomemotext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView[][] mat = new ImageView[4][4];

    private int [][] table = {
            {1,1,2,2},
            {3,3,4,4},
            {5,5,6,6},
            {7,7,8,8}};

    private boolean [][] destapado = new boolean[4][4];


    private Handler manejador = new Handler(Looper.myLooper());

    private boolean activo = true;

    private int npresion;

    private int primerox=-1;
    private int primeroy=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mat [0][0] = findViewById(R.id.iv1);
        mat [0][1] = findViewById(R.id.iv2);
        mat [0][2] = findViewById(R.id.iv3);
        mat [0][3] = findViewById(R.id.iv4);
        mat [1][0] = findViewById(R.id.iv5);
        mat [1][1] = findViewById(R.id.iv6);
        mat [1][2] = findViewById(R.id.iv7);
        mat [1][3] = findViewById(R.id.iv8);
        mat [2][0] = findViewById(R.id.iv9);
        mat [2][1] = findViewById(R.id.iv10);
        mat [2][2] = findViewById(R.id.iv11);
        mat [2][3] = findViewById(R.id.iv12);
        mat [3][0] = findViewById(R.id.iv13);
        mat [3][1] = findViewById(R.id.iv14);
        mat [3][2] = findViewById(R.id.iv15);
        mat [3][3] = findViewById(R.id.iv16);

        mezclarTablero();

    }

    private void mezclarTablero() {
        for (int f = 0; f<-1;f++){
            int x1 = (int) (Math.random()*4);
            int y1 = (int) (Math.random()*4);
            int x2 = (int) (Math.random()*4);
            int y2 = (int) (Math.random()*4);
            int aux = table[x1][y1];
            table [x1][y1] = table [x2][y2];
            table [x2][y2] = aux;
        }
    }

    public void onClickPresion(View view) {
        if (activo){
            npresion++;
            if (npresion==2)
                if (view==mat[primerox][primeroy]){
                    npresion--;
                    return;
                }
            verificarImagenPosicionada(view);
                if (npresion==2)
                    congelar5seg();
        }
    }

    private void congelar5seg() {
        npresion = 0;
        activo = false;
        primerox = -1;
        primeroy = -1;
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                activo = true;
                for (int i=0; i<4; i++)
                    for (int f=0; f<4; f++)
                        if (destapado[i] [f])
                            destapar(mat[i][f], table[i][f]);
                        else
                            mat[i][f].setImageResource(R.drawable.oculto);
            }
        },900);
    }

    private void verificarImagenPosicionada(View view) {

        for (int i=0; i<4; i++)
            for (int c=0; c<4; c++)
                if (mat[i][c] == view){
                    destapar(mat[i][c], table[i][c]);
                    if (npresion==1){
                        primerox = i;
                        primeroy = c;
                        return;
                    }else {
                        if (table[i][c]==table[primerox][primeroy]){
                            destapado[i][c] = true;
                            destapado[primerox][primeroy] = true;
                            verificarSiGano();
                            npresion=0;
                            return;
                        }
                    }
                }
    }

    private void verificarSiGano() {
        int destapadas = 0;
        for (int i=0; i < 4; i++)
            for (int f =0; f<4; f++)
                if (destapado[i][f])
                    destapadas++;
                if (destapadas==16)
                    Toast.makeText(this, "Ha ganado", Toast.LENGTH_SHORT).show();

                finish();
    }

    private void destapar(ImageView imageView, int i) {
        switch (i){
            case 1:
                imageView.setImageResource(R.drawable.imagen1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.imagen2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.imagen3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.imagen4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.imagen5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.imagen6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.imagen7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.imagen8);
                break;
        }
    }
}