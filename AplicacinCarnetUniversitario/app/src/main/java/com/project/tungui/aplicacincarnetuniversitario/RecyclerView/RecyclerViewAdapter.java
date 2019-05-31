package com.project.tungui.aplicacincarnetuniversitario.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventosCarnet;
import com.project.tungui.aplicacincarnetuniversitario.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public List<EventosCarnet> listaEventos;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemCLick(int position);

    }

    public void setOnItemCLickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo, tipo, fecha;
        ImageView imgEvento;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.tv_titulo);
            tipo = (TextView) itemView.findViewById(R.id.tv_tipo);
            fecha = (TextView) itemView.findViewById(R.id.tv_fecha);
            imgEvento = (ImageView) itemView.findViewById(R.id.img_evento);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemCLick(position);
                        }
                    }
                }
            });
        }

    }

    public RecyclerViewAdapter(List<EventosCarnet> listaEventos) {
        this.listaEventos = listaEventos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_evento, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view, mListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.titulo.setText(listaEventos.get(i).getNombreEvento());
        viewHolder.tipo.setText(listaEventos.get(i).getTipo());
        viewHolder.fecha.setText(listaEventos.get(i).getFecha());
        viewHolder.imgEvento.setImageResource(listaEventos.get(i).getImgEvento());
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }
}
