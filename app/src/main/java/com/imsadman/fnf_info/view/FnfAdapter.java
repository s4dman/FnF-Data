package com.imsadman.fnf_info.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imsadman.fnf_info.R;
import com.imsadman.fnf_info.repository.model.FnfEntity;

import java.util.List;

class FnfAdapter extends RecyclerView.Adapter<FnfAdapter.ViewHolder> {
    private Context mContext;
    private List<FnfEntity> fnfModelList;

    public FnfAdapter(Context mContext, List<FnfEntity> fnfModelList) {
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

        FnfEntity index = fnfModelList.get(position);
        holder.name.setText((index.getName()));
        holder.dob.setText((index.getDob()));
        holder.phoneNumber.setText((index.getPhone_number()));
        holder.email.setText((index.getEmail()));
        holder.facebook.setText((index.getFacebook()));
        holder.instagram.setText((index.getInstagram()));
        holder.location.setText((index.getAddress()));

        holder.facebook.setOnClickListener(view -> {
            Uri uri = Uri.parse("https://www.facebook.com/" + index.getFacebook());
            Intent fbIntent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(fbIntent);
        });

        holder.instagram.setOnClickListener(view -> {
            Uri uri = Uri.parse("https://www.instagram.com/" + index.getInstagram());
            Intent instaIntent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(instaIntent);
        });
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
