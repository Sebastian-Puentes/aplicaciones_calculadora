package com.example.proyectocalculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultado;
    private String input = "";
    private double num1 = 0, num2 = 0;
    private char operador = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.resultado);
    }

    public void botonNumeroClick(View view) {
        Button boton = (Button) view;
        String textoBoton = boton.getText().toString();

        // Evitar que se ingresen múltiples ceros al principio
        if (input.equals("0")) {
            input = textoBoton;
        } else {
            input += textoBoton;
        }

        resultado.setText(input);
    }

    public void botonOperadorClick(View view) {
        Button boton = (Button) view;
        if (!input.isEmpty()) {
            num1 = Double.parseDouble(input);
            operador = boton.getText().toString().charAt(0);

            // Mostrar num1, operador y un espacio en el TextView
            resultado.setText(num1 + " " + operador + " ");

            // Reiniciar input para capturar num2
            input = "";
        }
    }

    public void puntoDecimalClick(View view) {
        if (!input.contains(".")) {
            input += ".";
            resultado.setText(input);
        }
    }

    public void botonIgualClick(View view) {
        if (!input.isEmpty()) {
            num2 = Double.parseDouble(input);
            double resultadoCalculado = realizarOperacion(num1, num2, operador);
            input = String.valueOf(resultadoCalculado);

            if (Double.isNaN(resultadoCalculado)) {
                resultado.setText("ERROR");
            } else {
                resultado.setText(input);
            }
        }
    }

    // Función para realizar las operaciones
    private double realizarOperacion(double num1, double num2, char operador) {
        switch (operador) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '×':
                return num1 * num2;
            case '÷':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN; // Manejo de división por cero
                }
            case '^':
                return calcularPotencia(num1, (int) num2);
            default:
                return num2;
        }
    }

    private double calcularPotencia(double base, int exponente) {
        if (exponente == 0) {
            return 1;
        } else if (exponente < 0) {
            return 1 / (base * calcularPotencia(base, -exponente - 1));
        } else {
            return base * calcularPotencia(base, exponente - 1);
        }
    }

    public void botonResetearClick(View view) {
        num1 = 0;
        num2 = 0;
        operador = ' ';
        input = "";
        resultado.setText("0");
    }
}
