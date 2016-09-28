package supermarket.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import supermarket.main.R;
import supermarket.main.constant.Constant;
import supermarket.main.customComponents.TextViewFont;
import supermarket.main.data.data.DataCategory;

/**
 * Created by cubesschool2 on 9/28/16.
 */
public class MenuAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<DataCategory> mListDataHeader;
    LayoutInflater inflater;

    public MenuAdapter(Context context, List<DataCategory> listHeader){
        mContext = context;
        mListDataHeader = listHeader;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getGroupCount() {
        return mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListDataHeader.get(groupPosition).subcategories.size();
    }

    @Override
    public DataCategory getGroup(int groupPosition) {
        return mListDataHeader.get(groupPosition);
    }

    @Override
    public DataCategory getChild(int groupPosition, int childPosition) {
        return mListDataHeader.get(groupPosition).subcategories.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return Long.parseLong(mListDataHeader.get(groupPosition).id);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return Long.parseLong(mListDataHeader.get(groupPosition).subcategories.get(childPosition).id);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        String title = getGroup(groupPosition).name;

        convertView = inflater.inflate(R.layout.item_menu_parent, null);

        TextViewFont tvTitle = (TextViewFont) convertView.findViewById(R.id.title);

        tvTitle.setText(title);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText =  getChild(groupPosition, childPosition).name;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.menu_item_child, null);
        }

        TextViewFont txtListChild = (TextViewFont) convertView
                .findViewById(R.id.name);

        txtListChild.setText(childText);
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
