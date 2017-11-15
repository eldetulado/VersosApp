package com.example.oso.versosbiblicos;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolderDatos> {
    private List<Message> messageList;

    public MyAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.asignarDatos(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView content;
        TextView address;
        public ViewHolderDatos(View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.textoVerso);
            address = itemView.findViewById(R.id.textoInfoVerso);
        }

        public void asignarDatos(Message message) {
            content.setText(message.getVerseContent());
            address.setText(message.getVerseAddress());
        }
    }
}
