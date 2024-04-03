package com.example.aplicacion_correo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    private static final int DETAIL_REQUEST_CODE = 1; // Código de solicitud para abrir DetalleEmailActivity
    private RecyclerView recyclerView;
    private EmailAdapter emailAdapter;
    private List<Email> emailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al TextView donde se muestra el estado del mensaje

        // Inicializar la lista de correos electrónicos con datos de ejemplo
        emailList = new ArrayList<>();
        emailList.add(new Email(1, "Juan Pérez", "Reunión de Equipo", "Hola equipo, recordatorio de nuestra reunión mañana a las 10 AM.", "2024-03-20"));
        emailList.add(new Email(2, "María López", "Actualización de Proyecto", "Aquí está la última actualización sobre el progreso de nuestro proyecto.", "2024-03-19"));
        emailList.add(new Email(3, "Pedro García", "Confirmación de Pedido", "Confirmamos la recepción de su pedido. Pronto estará en camino.", "2024-03-18"));
        emailList.add(new Email(4, "Ana Martínez", "Recordatorio de Pago", "Le recordamos que su pago vence mañana. Por favor, realice el pago a tiempo.", "2024-03-17"));
        emailList.add(new Email(5, "Luis Hernández", "Invitación a Conferencia", "Le invitamos cordialmente a nuestra conferencia el próximo jueves.", "2024-03-16"));
        emailList.add(new Email(6, "Carolina Rodríguez", "Felicitaciones por el Logro", "Felicitaciones por el logro obtenido. ¡Gran trabajo!", "2024-03-15"));
        emailList.add(new Email(7, "Javier Sánchez", "Solicitud de Información", "Por favor, envíenos la información solicitada para continuar con el proyecto.", "2024-03-14"));
        emailList.add(new Email(8, "Elena Castro", "Entrega de Proyecto", "Estamos listos para entregar el proyecto. Por favor, confirme la fecha.", "2024-03-13"));
        emailList.add(new Email(9, "Diego Flores", "Solicitud de Reunión", "Necesitamos reunirnos para discutir los detalles del próximo evento.", "2024-03-12"));
        // Agregar más correos electrónicos según sea necesario

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewEmails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar y configurar el adaptador
        emailAdapter = new EmailAdapter(this, emailList);
        recyclerView.setAdapter(emailAdapter);

        // Configurar el OnClickListener para abrir la DetalleEmailActivity al hacer clic en un correo
        emailAdapter.setOnItemClickListener(new EmailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Abrir la DetalleEmailActivity con la información del correo seleccionado
                Email email = emailList.get(position);
                Intent intent = new Intent(MainActivity.this, DetalleEmailActivity.class);
                intent.putExtra("sender", email.getSender());
                intent.putExtra("subject", email.getSubject());
                intent.putExtra("content", email.getContent());
                intent.putExtra("date", email.getDate());
                startActivityForResult(intent, DETAIL_REQUEST_CODE);
            }
        });
    }
    private int contadorNotificacionesNoLeidas = 0; // Almacenar el contador de notificaciones no leídas

    @Override
    protected void onResume() {
        super.onResume();
        actualizarContadorNotificaciones();
    }

    private void actualizarContadorNotificaciones() {
        // Contar el número de correos no leídos
        contadorNotificacionesNoLeidas = contarCorreosNoLeidos();
        // Mostrar un Toast con el valor del contador actualizado
        mostrarContadorNotificaciones();
    }

    // Método para contar los correos no leídos
    private int contarCorreosNoLeidos() {
        int contador = 0;
        for (Email email : emailList) {
            if (!email.isRead()) {
                contador++;
            }
        }
        return contador;
    }

    // Método para mostrar el contador de notificaciones no leídas
    private void mostrarContadorNotificaciones() {
        // Mostrar un Toast con el valor del contador de notificaciones no leídas
        Toast.makeText(this, "Notificaciones no leídas: " + contadorNotificacionesNoLeidas, Toast.LENGTH_SHORT).show();
    }
}