package supermarket.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import supermarket.main.R;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.data.DataProduct;

/**
 * Created by Mladen on 9/20/2016.
 */
public class CartRecyclerViewAdapret extends RecyclerView.Adapter<CartRecyclerViewAdapret.ViewHolder> {

    private Context context;
    private ArrayList<DataProduct> products;
    private AdapterView.OnItemClickListener onItemClickListener;

    public CartRecyclerViewAdapret(Context context, ArrayList<DataProduct> products, AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.products = products;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_cart, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataProduct product = products.get(position);

        TextViewFont name = holder.name;
        name.setText(product.name);

        TextViewFont price = holder.price;
        price.setText(product.price);

        TextViewFont amount = holder.amount;
        amount.setText(product.amount + "");

        ImageView imageView = holder.image;
        Glide.with(context).load(product.thumb126)
                .fitCenter()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextViewFont name;
        public TextViewFont price;
        public TextViewFont amount;
        public ImageView image;
        public ImageView plus, minus;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextViewFont) itemView.findViewById(R.id.name);
            price = (TextViewFont) itemView.findViewById(R.id.price);
            amount = (TextViewFont) itemView.findViewById(R.id.amount);
            image = (ImageView) itemView.findViewById(R.id.image);
            plus = (ImageView) itemView.findViewById(R.id.plus);
            plus.setId(R.id.plus);
            minus = (ImageView) itemView.findViewById(R.id.minus);
            minus.setId(R.id.minus);

            plus.setOnClickListener(this);
            minus.setOnClickListener(this);

        }

        public int getItemId(int position) {
            return Integer.parseInt(products.get(position).id);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.plus) {
                DataContainer.addToCart(Integer.parseInt(products.get(getAdapterPosition()).id));
                notifyDataSetChanged();
            }else if(v.getId()==R.id.minus){
                DataContainer.removeFromCart(products.get(getAdapterPosition()).id);
                notifyDataSetChanged();
            }
        }
    }
}
