package com.example.dell.benahapp.gallery;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.benahapp.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements Serializable{

    private Context context;
    private ArrayList<Item> itemList;

    public ItemAdapter(Context context, ArrayList<Item> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.item_cardview_layout, parent, false);

        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item item = itemList.get(position);
        Picasso.with(context)
                .load(item.img)
                .placeholder(R.drawable.rogue)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.ivImg);

        holder.tvText.setText(item.text);

        holder.ivImg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Detail.class);
                intent.putExtra("item", item);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.loveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                item.loves++;
            }
        });

        holder.hateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                item.hates++;
            }
        });

    }

    @Override
    public int getItemCount() {

        if(itemList != null)
            return itemList.size();

        return 0;
    }


    //ViewHolder Class
    public static class ItemViewHolder extends RecyclerView.ViewHolder implements Serializable{

        public CardView cvItem;
        public ImageView ivImg;
        public TextView tvText;
        public Button loveButton;
        public Button hateButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cvItem = (CardView) itemView.findViewById(R.id.cvItem);
            ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
            loveButton = (Button) itemView.findViewById(R.id.loveB);
            hateButton = (Button) itemView.findViewById(R.id.hateB);
        }
    }
}
