package supermarket.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import supermarket.main.R;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.data.DataProduct;

/**
 * Created by Mladen on 9/22/2016.
 */
public class FinalBuyingAdapter extends RecyclerView.Adapter<FinalBuyingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DataProduct> products;

    public FinalBuyingAdapter(Context context, ArrayList<DataProduct> products) {
        this.context = context;
        this.products = products;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_final_buying, parent, false);

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

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextViewFont name;
        public TextViewFont price;
        public TextViewFont amount;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextViewFont) itemView.findViewById(R.id.name);
            price = (TextViewFont) itemView.findViewById(R.id.price);
            amount = (TextViewFont) itemView.findViewById(R.id.amount);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
