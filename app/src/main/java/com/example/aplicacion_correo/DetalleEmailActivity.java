package com.example.aplicacion_correo;


import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleEmailActivity extends AppCompatActivity {
    private TextView senderTextView, subjectTextView, contentTextView, dateTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_email);
        Email email = getIntent().getParcelableExtra("email");

        // Obtener referencias a los TextView del layout
        senderTextView = findViewById(R.id.senderTextView);
        subjectTextView = findViewById(R.id.subjectTextView);
        contentTextView = findViewById(R.id.contentTextView);
        dateTextView = findViewById(R.id.dateTextView);

        // Obtener datos del Intent
        String sender = getIntent().getStringExtra("sender");
        String subject = getIntent().getStringExtra("subject");
        String content = getIntent().getStringExtra("content");
        String date = getIntent().getStringExtra("date");

        // Mostrar datos en los TextView
        senderTextView.setText(sender);
        subjectTextView.setText(subject);
        contentTextView.setText(content);
        dateTextView.setText(date);

        Button responderButton = findViewById(R.id.responderButton);
        responderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para responder al correo electrónico
                Toast.makeText(DetalleEmailActivity.this, "Correo Respondido", Toast.LENGTH_SHORT).show();
            }
        });


        Button reenviarButton = findViewById(R.id.reenviarButton);
        reenviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para reenviar el correo electrónico
                Toast.makeText(DetalleEmailActivity.this, "Correo Reenviado", Toast.LENGTH_SHORT).show();
            }
        });

        Button archivarButton = findViewById(R.id.archivarButton);
        archivarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para archivar el correo electrónico
                Toast.makeText(DetalleEmailActivity.this, "Correo Archivado", Toast.LENGTH_SHORT).show();
            }
        });

        Button eliminarButton = findViewById(R.id.eliminarButton);
        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para eliminar el correo electrónico
                Toast.makeText(DetalleEmailActivity.this, "Correo Eliminado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("isRead", true);
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();
    }
}