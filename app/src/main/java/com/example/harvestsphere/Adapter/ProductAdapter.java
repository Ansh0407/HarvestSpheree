package com.example.harvestsphere.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harvestsphere.PaymentActivity;
import com.example.harvestsphere.R;
import com.example.harvestsphere.model.CartItem;
import com.example.harvestsphere.model.CropModel;
import com.example.harvestsphere.model.FertilizerModel;
import com.example.harvestsphere.model.PesticideModel;
import com.example.harvestsphere.model.ToolModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CROP = 0;
    private static final int TYPE_FERTILIZER = 1;
    private static final int TYPE_PESTICIDE = 2;
    private static final int TYPE_TOOL = 3;

    private List<Object> itemList;
    private Context context;
    private List<CartItem> cartItems = new ArrayList<>();

    public ProductAdapter(List<Object> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof CropModel) {
            return TYPE_CROP;
        } else if (itemList.get(position) instanceof FertilizerModel) {
            return TYPE_FERTILIZER;
        } else if (itemList.get(position) instanceof PesticideModel) {
            return TYPE_PESTICIDE;
        } else if (itemList.get(position) instanceof ToolModel) {
            return TYPE_TOOL;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case TYPE_CROP:
                return new CropViewHolder(inflater.inflate(R.layout.item_crop, parent, false));
            case TYPE_FERTILIZER:
                return new FertilizerViewHolder(inflater.inflate(R.layout.item_fertilizer, parent, false));
            case TYPE_PESTICIDE:
                return new PesticideViewHolder(inflater.inflate(R.layout.item_pesticide, parent, false));
            case TYPE_TOOL:
                return new ToolViewHolder(inflater.inflate(R.layout.item_tool, parent, false));
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object item = itemList.get(position);
        if (holder instanceof CropViewHolder) {
            ((CropViewHolder) holder).bind((CropModel) item);
        } else if (holder instanceof FertilizerViewHolder) {
            ((FertilizerViewHolder) holder).bind((FertilizerModel) item);
        } else if (holder instanceof PesticideViewHolder) {
            ((PesticideViewHolder) holder).bind((PesticideModel) item);
        } else if (holder instanceof ToolViewHolder) {
            ((ToolViewHolder) holder).bind((ToolModel) item);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class CropViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, season, soilType, description, quantityText;
        private ImageView imageView;
        private Button addButton, decreaseButton, increaseButton;
        private int quantity = 0;

        CropViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.crop_image);
            name = itemView.findViewById(R.id.crop_name);
            price = itemView.findViewById(R.id.crop_price);
            season = itemView.findViewById(R.id.crop_season);
            soilType = itemView.findViewById(R.id.crop_soil_type);
            description = itemView.findViewById(R.id.crop_description);
            quantityText = itemView.findViewById(R.id.quantity_text);
            addButton = itemView.findViewById(R.id.add_to_cart_button);
            decreaseButton = itemView.findViewById(R.id.decrease_quantity);
            increaseButton = itemView.findViewById(R.id.increase_quantity);

            decreaseButton.setOnClickListener(v -> {
                if (quantity > 0) {
                    quantity--;
                    updateQuantity();
                }
            });

            increaseButton.setOnClickListener(v -> {
                quantity++;
                updateQuantity();
            });

            addButton.setOnClickListener(v -> {
                Toast.makeText(context, name.getText() + " added to cart. Quantity: " + quantity, Toast.LENGTH_SHORT).show();
                addToCart((CropModel) itemList.get(getAdapterPosition()), quantity);
            });
        }

        void bind(final CropModel crop) {
            name.setText(crop.getName());
            price.setText("₹" + crop.getPricePerKg() + " per kg");
            season.setText(crop.getSeason());
            soilType.setText(crop.getSoilType());
            description.setText(crop.getDescription());
            Picasso.get().load(crop.getImage()).into(imageView);

            quantity = 0;
            updateQuantity();
        }

        private void updateQuantity() {
            quantityText.setText(String.valueOf(quantity));
        }
    }

    class FertilizerViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, description, quantityText;
        private ImageView imageView;
        private Button addButton, decreaseButton, increaseButton;
        private int quantity = 0;

        FertilizerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fertilizer_image);
            name = itemView.findViewById(R.id.fertilizer_name);
            price = itemView.findViewById(R.id.fertilizer_price);
            description = itemView.findViewById(R.id.fertilizer_description);
            quantityText = itemView.findViewById(R.id.quantity_text);
            addButton = itemView.findViewById(R.id.add_to_cart_button);
            decreaseButton = itemView.findViewById(R.id.decrease_quantity);
            increaseButton = itemView.findViewById(R.id.increase_quantity);

            decreaseButton.setOnClickListener(v -> {
                if (quantity > 0) {
                    quantity--;
                    updateQuantity();
                }
            });

            increaseButton.setOnClickListener(v -> {
                quantity++;
                updateQuantity();
            });

            addButton.setOnClickListener(v -> {
                Toast.makeText(context, name.getText() + " added to cart. Quantity: " + quantity, Toast.LENGTH_SHORT).show();
                addToCart((FertilizerModel) itemList.get(getAdapterPosition()), quantity);
            });
        }

        void bind(final FertilizerModel fertilizer) {
            name.setText(fertilizer.getName());
            price.setText("₹" + fertilizer.getPricePerKg() + " per kg");
            description.setText(fertilizer.getDescription());
            Picasso.get().load(fertilizer.getImage()).into(imageView);

            quantity = 0;
            updateQuantity();
        }

        private void updateQuantity() {
            quantityText.setText(String.valueOf(quantity));
        }
    }

    class PesticideViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, description, quantityText;
        private ImageView imageView;
        private Button addButton, decreaseButton, increaseButton;
        private int quantity = 0;

        PesticideViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pesticide_image);
            name = itemView.findViewById(R.id.pesticide_name);
            price = itemView.findViewById(R.id.pesticide_price);
            description = itemView.findViewById(R.id.pesticide_description);
            quantityText = itemView.findViewById(R.id.quantity_text);
            addButton = itemView.findViewById(R.id.add_to_cart_button);
            decreaseButton = itemView.findViewById(R.id.decrease_quantity);
            increaseButton = itemView.findViewById(R.id.increase_quantity);

            decreaseButton.setOnClickListener(v -> {
                if (quantity > 0) {
                    quantity--;
                    updateQuantity();
                }
            });

            increaseButton.setOnClickListener(v -> {
                quantity++;
                updateQuantity();
            });

            addButton.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), name.getText() + " added to cart. Quantity: " + quantity, Toast.LENGTH_SHORT).show();
                addToCart((PesticideModel) itemList.get(getAdapterPosition()), quantity);
            });
        }

        void bind(final PesticideModel pesticide) {
            name.setText(pesticide.getName());
            price.setText("₹" + String.format("%.2f", pesticide.getPricePerLiter()) + " per liter");
            description.setText(pesticide.getDescription());
            Picasso.get().load(pesticide.getImage()).into(imageView);

            quantity = 0;
            updateQuantity();
        }

        private void updateQuantity() {
            quantityText.setText(String.valueOf(quantity));
        }
    }

    class ToolViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price, description, quantityText;
        private ImageView imageView;
        private Button addButton, decreaseButton, increaseButton;
        private int quantity = 0;

        ToolViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tool_image);
            name = itemView.findViewById(R.id.tool_name);
            price = itemView.findViewById(R.id.tool_price);
            description = itemView.findViewById(R.id.tool_description);
            quantityText = itemView.findViewById(R.id.quantity_text);
            addButton = itemView.findViewById(R.id.add_to_cart_button);
            decreaseButton = itemView.findViewById(R.id.decrease_quantity);
            increaseButton = itemView.findViewById(R.id.increase_quantity);

            decreaseButton.setOnClickListener(v -> {
                if (quantity > 0) {
                    quantity--;
                    updateQuantity();
                }
            });

            increaseButton.setOnClickListener(v -> {
                quantity++;
                updateQuantity();
            });

            addButton.setOnClickListener(v -> {
                Toast.makeText(context, name.getText() + " added to cart. Quantity: " + quantity, Toast.LENGTH_SHORT).show();
                addToCart((ToolModel) itemList.get(getAdapterPosition()), quantity);
            });
        }

        void bind(final ToolModel tool) {
            name.setText(tool.getName());
            price.setText("₹" + tool.getPrice() + " per unit");
            description.setText(tool.getDescription());
            Picasso.get().load(tool.getImage()).into(imageView);

            quantity = 0;
            updateQuantity();
        }

        private void updateQuantity() {
            quantityText.setText(String.valueOf(quantity));
        }
    }

    private void addToCart(Object product, int quantity) {
        String productName;
        String productImage;

        if (product instanceof CropModel) {
            CropModel crop = (CropModel) product;
            productName = crop.getName();
            productImage = crop.getImage();
        } else if (product instanceof FertilizerModel) {
            FertilizerModel fertilizer = (FertilizerModel) product;
            productName = fertilizer.getName();
            productImage = fertilizer.getImage();
        } else if (product instanceof PesticideModel) {
            PesticideModel pesticide = (PesticideModel) product;
            productName = pesticide.getName();
            productImage = pesticide.getImage();
        } else if (product instanceof ToolModel) {
            ToolModel tool = (ToolModel) product;
            productName = tool.getName();
            productImage = tool.getImage();
        } else {
            throw new IllegalArgumentException("Unknown product type");
        }

        // Add item to cart
        cartItems.add(new CartItem(productName, productImage, quantity));

        // Launch payment activity
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("cartItems", new ArrayList<>(cartItems));
        context.startActivity(intent);
    }
}