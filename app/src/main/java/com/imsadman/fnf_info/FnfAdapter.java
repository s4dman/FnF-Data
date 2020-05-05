package com.imsadman.fnf_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class FnfAdapter extends RecyclerView.Adapter<FnfAdapter.ViewHolder> {
    private Context mContext;
    private List<FnfModel> fnfModelList;

    public FnfAdapter(Context mContext, List<FnfModel> fnfModelList) {
        this.mContext = mContext;
        this.fnfModelList = fnfModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fnf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FnfModel index = fnfModelList.get(position);
        holder.name.setText((index.getName()));
        holder.dob.setText((index.getDob()));
        holder.phoneNumber.setText((index.getPhone_number()));
        holder.email.setText((index.getEmail()));
        holder.facebook.setText((index.getFacebook()));
        holder.instagram.setText((index.getInstagram()));
        holder.location.setText((index.getAddress()));
    }

    @Override
    public int getItemCount() {
        return fnfModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, dob, email, phoneNumber, facebook, instagram, location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.fnf_name);
            dob = itemView.findViewById(R.id.fnf_dob);
            phoneNumber = itemView.findViewById(R.id.fnf_phone_number);
            email = itemView.findViewById(R.id.fnf_email);
            facebook = itemView.findViewById(R.id.fnf_facebook);
            instagram = itemView.findViewById(R.id.fnf_instagram);
            location = itemView.findViewById(R.id.fnf_city);

        }
    }
}
