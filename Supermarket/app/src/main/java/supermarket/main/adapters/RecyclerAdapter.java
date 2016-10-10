package supermarket.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import supermarket.main.R;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.container.DataContainer;
import supermarket.main.data.data.DataProduct;

/**
 * Created by cubesschool2 on 9/19/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static boolean enabled = false;
    private Context context;
    private ArrayList<DataProduct> products;
    private AdapterView.OnItemClickListener onItemClickListener;

    public RecyclerAdapter(Context context, ArrayList<DataProduct> list, AdapterView.OnItemClickListener onItemClickListener){
        this.products = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_product, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataProduct product = products.get(position);

        TextViewFont title = holder.title;
        title.setText(product.name);

        TextViewFont price = holder.price;
        price.setText(product.price);

        ImageView imageView = holder.image;
        Glide.with(context).load(product.thumb330)
                .fitCenter()
                .into(imageView);

        RelativeLayout layoutStar = holder.layoutStar;
        layoutStar.bringToFront();
        ImageView star = holder.star;
        star.bringToFront();
        if(product.favorit){
            star.setImageResource(R.drawable.ic_star);
        }else {
            star.setImageResource(R.drawable.ic_star_outline);
        }
    }

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return Long.parseLong(products.get(position).id);
    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextViewFont title;
        public TextViewFont price;
        public ImageView image;
        public ImageView add;
        public ImageView star;
        public RelativeLayout layoutStar;


        public ViewHolder(View itemView) {
            super(itemView);

            layoutStar = (RelativeLayout) itemView.findViewById(R.id.layout_star);
            layoutStar.setId(R.id.layout_star);
            star = (ImageView) itemView.findViewById(R.id.star);
            star.setId(R.id.star);
            title = (TextViewFont) itemView.findViewById(R.id.product_title);
            price = (TextViewFont) itemView.findViewById(R.id.product_price);
            image = (ImageView) itemView.findViewById(R.id.image);
            image.setId(R.id.image);
            add = (ImageView) itemView.findViewById(R.id.add);
            add.setId(R.id.add);
            image.setOnClickListener(this);
            add.setOnClickListener(this);
            layoutStar.setOnClickListener(this);
            star.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(null, v, getAdapterPosition(), v.getId());
        }
    }
}
