package com.hkm.longmenu;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by hesk on 4/11/2015.
 */
public class LongMenuComponent<Text extends TextView> extends Fragment {
    private Bind binding;
    private LinearLayout container;
    private RelativeLayout holdercontainer;
    private ImageView toplogo;
    private Point bound;
    private ScrollView longcontainerscoller;

    @SuppressLint("WrongViewCast")
    public void init(final Bind setting) {
        binding = setting;
        bound = ViewUtils.getScreenSize(getActivity());
        int padding_item = (int) setting.getIconPadding();

        int imageportion = (int) (setting.getItem_height() * setting.getPortions()[0]);
        int textportion = (int) (setting.getItem_height() * setting.getPortions()[1]);
        if (binding != null && container != null) {
            for (int i = 0; i < binding.getListMenu().size(); i++) {
                final menuitem item = binding.getListMenu().get(i);
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                RelativeLayout holder;
                if (setting.getResLayoutItem() > Bind.UNSET) {
                    holder = (RelativeLayout) inflater.inflate(setting.getResLayoutItem(), null, false);
                } else {
                    holder = (RelativeLayout) inflater.inflate(R.layout.itemmenu, null, false);
                }
                holder.setBackgroundResource(setting.getPattern());
                ImageView image = (ImageView) holder.findViewById(R.id.itemimage);
                Text textv = (Text) holder.findViewById(R.id.menuname);
                // image.setMaxWidth(setting.getWidth());
                image.setImageResource(item.getResIdCompanyLogo());
                image.setMinimumHeight(imageportion);
                image.setMaxHeight(imageportion);
                image.setAdjustViewBounds(true);
                image.setPadding(padding_item, padding_item, padding_item, padding_item);
                textv.setText(item.getMenuDisplayName());
                textv.setMinimumHeight(textportion);
                textv.setMaxHeight(textportion);

                holder.setLayoutParams(new RelativeLayout.LayoutParams(setting.getWidth(), setting.getItem_height()));
                holder.requestLayout();
                if (item.getClassIntent() != null) {
                    holder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent f = new Intent(getActivity(), item.getClassIntent());
                            if (item.getTheExtraData() != null) {
                                f.putExtras(item.getTheExtraData());
                            }
                            getActivity().startActivity(f);
                        }
                    });
                }
                container.addView(holder);
                if (i < binding.getListMenu().size() - 1 && setting.isEnabledSeparator())
                    container.addView(prepareSpaceV(10));
            }

            toplogo.setMaxWidth(setting.getWidth());
            toplogo.setMaxHeight(setting.getWidth());
            toplogo.setImageResource(setting.getResIdCompanyLogo());
            toplogo.setAdjustViewBounds(true);


            // container.setLayoutParams(new RelativeLayout.LayoutParams(setting.getWidth(), bound.y - setting.getWidth()));
            longcontainerscoller.setLayoutParams(new RelativeLayout.LayoutParams(setting.getWidth(), bound.y - setting.getWidth()));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) longcontainerscoller.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.company_logo);
            longcontainerscoller.setSmoothScrollingEnabled(true);
            longcontainerscoller.setVerticalScrollBarEnabled(false);
            container.requestLayout();
        } else {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Right", "onCreateView()");
        //holdercontainer
        return inflater.inflate(R.layout.longmenu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("TAG", "onViewCreated");
        toplogo = (ImageView) view.findViewById(R.id.company_logo);
        holdercontainer = (RelativeLayout) view.findViewById(R.id.holder);
        longcontainerscoller = (ScrollView) view.findViewById(R.id.longcontainerscoller);
        container = (LinearLayout) view.findViewById(R.id.longcontainer);
    }

    private void setSpacebackground(View item) {
        item.setBackgroundResource(R.drawable.separator);
    }

    private View prepareSpaceV(int dp) {
        LinearLayout.LayoutParams separator = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dipToPixels(getActivity(), dp));
        //border set
        View borderVertical = new View(getActivity());
        borderVertical.setLayoutParams(separator);
        borderVertical.setBackgroundResource(R.drawable.separator);
        return borderVertical;
    }

    private View prepareSpaceH(int dp) {
        LinearLayout.LayoutParams separator = new LinearLayout.LayoutParams(ViewUtils.dipToPixels(getActivity(), dp), ViewGroup.LayoutParams.MATCH_PARENT);
        //border set
        View borderVertical = new View(getActivity());
        borderVertical.setLayoutParams(separator);
        return borderVertical;
    }
}
