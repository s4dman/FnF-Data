package com.imsadman.fnf_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imsadman.fnf_info.database.FnfEntity;

import java.util.List;

class FnfAdapter extends RecyclerView.Adapter<FnfAdapter.ViewHolder> {
    private Context mContext;
    private List<FnfEntity> mFnfEntities;

    public FnfAdapter(Context mContext, List<FnfEntity> mFnfEntities) {
        this.mContext = mContext;
        this.mFnfEntities = mFnfEntities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fnf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FnfEntity index = mFnfEntities.get(position);
        holder.firstName.setText((index.getName()));
        holder.lastName.setText((index.getDob()));
        holder.email.setText((index.getEmail()));
        holder.phoneNumber.setText((index.getPhoneNumber()));
        holder.facebook.setText((index.getFacebook()));
        holder.instagram.setText((index.getInstagram()));
        holder.address.setText((index.getAddress()));
        holder.postalCode.setText((index.getPostalCode()));
        holder.city.setText((index.getCity()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, email, phoneNumber, facebook, instagram, address, postalCode, city;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.fnf_first_name);
            lastName = itemView.findViewById(R.id.fnf_last_name);
            email = itemView.findViewById(R.id.fnf_email);
            phoneNumber = itemView.findViewById(R.id.fnf_phone_number);
            facebook = itemView.findViewById(R.id.fnf_facebook);
            instagram = itemView.findViewById(R.id.fnf_instagram);
            address = itemView.findViewById(R.id.fnf_address);
            postalCode = itemView.findViewById(R.id.fnf_postal_code);
            city = itemView.findViewById(R.id.fnf_city);

        }
    }
}
