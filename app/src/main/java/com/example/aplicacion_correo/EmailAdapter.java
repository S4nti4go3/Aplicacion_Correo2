package com.example.aplicacion_correo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;
import androidx.core.content.ContextCompat;
import android.app.Activity;


public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder> {
    private List<Email> emailList;
    private Context context;
    private OnItemClickListener listener;
    private static final int DETAIL_REQUEST_CODE = 1;
    private int selectedPosition = 1;


    public void setSelectedPosition(int position) {
        selectedPosition = position;

    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        public void onItemClick(int position);

    }
    public void onItemClick(int position) {
        // Actualizar la posición seleccionada y aplicar el nuevo estilo
        setSelectedPosition(position);
    }

    public EmailAdapter(Context context, List<Email> emailList) {
        this.context = context;
        this.emailList = emailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Email email = emailList.get(position);
        holder.senderTextView.setText(email.getSender());
        holder.subjectTextView.setText(email.getSubject());
        holder.contentTextView.setText(email.getContent());
        holder.dateTextView.setText(email.getDate());

        // Configurar el listener de clics en el itemView para abrir la actividad DetalleEmailActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
                    Intent intent = new Intent(context, DetalleEmailActivity.class);
                    intent.putExtra("sender", email.getSender());
                    intent.putExtra("subject", email.getSubject());
                    intent.putExtra("content", email.getContent());
                    intent.putExtra("date", email.getDate());

                    ((Activity) context).startActivityForResult(intent, DETAIL_REQUEST_CODE);
                }
            }
        });

        // Configurar el listener de clics en el itemView para cambiar el estilo del mensaje
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getAdapterPosition());
                    listener.onItemClick(position);

                    // Cambiar el estilo del mensaje al hacer clic
                    holder.senderTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
                    holder.subjectTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
                    holder.contentTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
                    holder.dateTextView.setTextColor(ContextCompat.getColor(context, R.color.dark_gray));
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_item_background));

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return emailList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        TextView subjectTextView;
        TextView contentTextView;
        TextView dateTextView;
        CircleImageView circleImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.senderTextView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            circleImageView = itemView.findViewById(R.id.myCircleImageView);
        }
    }

    public void resetItemStyle() {
        selectedPosition = 1; // Reiniciar la posición seleccionada
    }

}