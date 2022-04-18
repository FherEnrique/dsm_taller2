package com.test.dsm_lab2.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.dsm_lab2.R;
import com.test.dsm_lab2.models.Consumable;
import com.test.dsm_lab2.services.ShoppingService;

import java.util.ArrayList;

public class ConsumableAdapter extends RecyclerView.Adapter<ConsumableAdapter.ViewHolder> {
    private final ShoppingService shoppingService;
    private final Boolean showCartButton;
    private final ArrayList<Consumable> consumables;
    private final Runnable runnableOnRemove;

    public ConsumableAdapter(ArrayList<Consumable> consumables, ShoppingService shoppingService, Boolean showCartButton, Runnable runnableOnRemove) {
        this.consumables = consumables;
        this.shoppingService = shoppingService;
        this.showCartButton = showCartButton;
        this.runnableOnRemove = runnableOnRemove;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.consumable_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Consumable consumable = consumables.get(position);
        holder.getConsumableName().setText(consumable.getName());
        holder.getConsumablePrice().setText("$" + consumable.getPrice());
        Picasso.get().load(consumable.getImage()).into(holder.getConsumableImage());
        Button removeElement = holder.getRemoveFromCart();

        if (showCartButton) {
            holder.getAddToCart().setOnClickListener(view -> {
                Toast.makeText(view.getContext(), R.string.cart_text, Toast.LENGTH_LONG).show();
                shoppingService.addItem(consumable);
            });
        } else {
            holder.getAddToCart().setVisibility(View.GONE);
            removeElement.setVisibility(View.VISIBLE);
            removeElement.setOnClickListener(view -> {
                Toast.makeText(view.getContext(), R.string.remove_item_cart, Toast.LENGTH_LONG).show();
                shoppingService.removeItemAt(position);
                if (runnableOnRemove != null) {
                    runnableOnRemove.run();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return consumables.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView consumableName;
        private final TextView consumablePrice;
        private final ImageView consumableImage;
        private final Button addToCart;
        private final Button removeFromCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            consumableName = itemView.findViewById(R.id.consumableName);
            consumableImage = itemView.findViewById(R.id.consumableImage);
            consumablePrice = itemView.findViewById(R.id.consumablePrice);
            addToCart = itemView.findViewById(R.id.addToCart);
            removeFromCart = itemView.findViewById(R.id.removeFromCart);
        }

        public TextView getConsumableName() {
            return consumableName;
        }

        public TextView getConsumablePrice() {
            return consumablePrice;
        }

        public ImageView getConsumableImage() {
            return consumableImage;
        }

        public Button getAddToCart() {
            return addToCart;
        }

        public Button getRemoveFromCart() {
            return removeFromCart;
        }
    }
}
