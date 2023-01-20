package com.tm221260.contactslistassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.tm221260.contactslistassignment.R;
import java.util.ArrayList;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ContactsModal> contactsModalArrayList;

    public ContactRVAdapter(Context context, ArrayList<ContactsModal> contactsModalArrayList) {
        this.context = context;
        this.contactsModalArrayList = contactsModalArrayList;
    }

    @NonNull
    @Override
    public ContactRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactRVAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.contacts_rv_item, parent, false));

    }

    public void filterList(ArrayList<ContactsModal> filterllist) {
        contactsModalArrayList = filterllist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRVAdapter.ViewHolder holder, int position) {
        ContactsModal modal = contactsModalArrayList.get(position);
        holder.contactTV.setText(modal.getUserName());
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();

        TextDrawable drawable2 = TextDrawable.builder().beginConfig()
                .width(100)
                .height(100)
                .endConfig()
                .buildRound(modal.getUserName().substring(0, 1), color);
        holder.contactIV.setImageDrawable(drawable2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ContactDetailActivity.class);
                i.putExtra("name", modal.getUserName());
                i.putExtra("contact", modal.getContactNumber());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView contactIV;
        private TextView contactTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactIV = itemView.findViewById(R.id.idIVContact);
            contactTV = itemView.findViewById(R.id.idTVContactName);
        }
    }
}
