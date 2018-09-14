package com.example.basicprogramming.volleydemoapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basicprogramming.volleydemoapp.R;
import com.example.basicprogramming.volleydemoapp.model.Users;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.HolderView> {

    private Context context;
    private ArrayList<Users> usersArrayList;
    private LayoutInflater inflater;

    public UserAdapter(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.user_list_layout, viewGroup, false);
        HolderView holderView = new HolderView(view);

        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView viewHolder, int i) {

        Users users = usersArrayList.get(i);

        viewHolder.f_name_tv.setText(users.getFirstName() + " " + users.getLastName());
        viewHolder.email_id_tv.setText(users.getEmailId());
        viewHolder.age_tv.setText("Age " + users.getAge());
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {

        private TextView f_name_tv, l_name_tv, email_id_tv, age_tv;

        public HolderView(@NonNull View itemView) {
            super(itemView);

            f_name_tv = itemView.findViewById(R.id.first_name_text_view);
            email_id_tv = itemView.findViewById(R.id.email_id_text_view);
            age_tv = itemView.findViewById(R.id.age_text_view);
        }

    }
}
