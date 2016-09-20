package supermarket.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;

import supermarket.main.R;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.data.DataProduct;

/**
 * Created by Mladen on 9/20/2016.
 */
public class CartRecyclerViewAdapret {

    private Context context;
    private ArrayList<String> products;
    private AdapterView.OnItemClickListener onItemClickListener;




    public class ViewHolder extends RecyclerView.ViewHolder  {

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
            minus = (ImageView) itemView.findViewById(R.id.minus);

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

    }
}
