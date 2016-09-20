package supermarket.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import supermarket.main.R;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.data.DataCity;

/**
 * Created by Mladen on 9/19/2016.
 */
public class SpinnerAdapter extends ArrayAdapter<DataCity> {

    private Context context;
    private ArrayList<DataCity> cityList;
    LayoutInflater inflater;
    private int resource;

    public SpinnerAdapter(Context context, int resource, ArrayList<DataCity> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.cityList = objects;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public DataCity getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent, false);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent, true);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent, boolean isDropDown) {
        View row = convertView;
        Holder holder = null;

        if (row == null) {
            if (isDropDown) {
                row = inflater.inflate(R.layout.simple_spinner_item2,parent,false);
            } else {
                row = inflater.inflate(resource, parent, false);
            }
            holder = new Holder();
            holder.textViewFont = (TextViewFont) row.findViewById(R.id.city);

            row.setTag(resource, holder);

        } else {
            holder = (Holder) row.getTag(resource);
        }

        holder.textViewFont.setText(cityList.get(position).name);

        return row;
    }

    public class Holder {
        public TextViewFont textViewFont;
    }
}
