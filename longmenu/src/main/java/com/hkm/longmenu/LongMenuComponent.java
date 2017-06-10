package com.hkm.longmenu;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
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
    private FragmentManager fm;
    private int frame_layout;

    @SuppressLint("WrongViewCast")
    public void init(final Bind setting) {
        binding = setting;
        bound = ViewUtils.getScreenSize(getActivity());
        int padding_item = (int) setting.getIconPadding();

        int imageportion = (int) (setting.getItem_height() * setting.getPortions()[0]);
        int textportion = (int) (setting.getItem_height() * setting.getPortions()[1]);
        if (binding != null && container != null) {
            for (int i = 0; i < binding.getListMenu().size(); i++) {
                final ItemBase item = binding.getListMenu().get(i);
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
                if (binding.getTxtColor() != 0) {
                    textv.setTextColor(binding.getTxtColor());
                }
                if (binding.getFontFace() != null) {
                    textv.setTypeface(binding.getFontFace());
                }
                holder.setLayoutParams(new RelativeLayout.LayoutParams(setting.getWidth(), setting.getItem_height()));
                holder.requestLayout();
                onClickEvent(item, holder);
                container.addView(holder);
                if (i < binding.getListMenu().size() - 1 && setting.isEnabledSeparator())
                    container.addView(prepareSpaceV(10));
            }
            //container.setPadding(0, 50, 0, 0);
            int paddingDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, setting.topLogoMarginPx(), getCs().getResources().getDisplayMetrics());
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, paddingDp, 0, 0);
            toplogo.setLayoutParams(lp);
            toplogo.setMaxWidth(setting.getWidth());
            toplogo.setMaxHeight(setting.getWidth());
            toplogo.setImageResource(setting.getResIdCompanyLogo());
            toplogo.setBackgroundColor(Color.TRANSPARENT);
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

    private Context getCs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext();
        } else {
            return getActivity();
        }


    }

    private void onClickEvent(ItemBase item, RelativeLayout holder) {
        if (item instanceof MAitem) {
            final MAitem me = (MAitem) item;
            if (me.getClassIntent() != null) {
                holder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent f = new Intent(getActivity(), me.getClassIntent());
                        if (me.getTheExtraData() != null) {
                            f.putExtras(me.getTheExtraData());
                        }
                        getActivity().startActivity(f);
                    }
                });
            }
        } else if (item instanceof MFitem) {
            final MFitem me = (MFitem) item;
            if (me.getClassIntent() != null) {
                if (fm == null) {
                    /**
                     * error that not fm is setup in here
                     */
                } else {
                    holder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fm
                                    .beginTransaction()
                                    .replace(frame_layout, of(me), "CONTENT_MAIN")
                                    .commitAllowingStateLoss();
                        }
                    });
                }
            }
        }
    }

    private Fragment of(MFitem me) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Fragment.instantiate(getContext(), me.getClassIntent().getName(), me.getTheExtraData());
        } else {
            return Fragment.instantiate(getActivity().getApplication(), me.getClassIntent().getName(), me.getTheExtraData());
        }
    }

    public void setFragmentManager(int target_framelayout_id, FragmentManager mManager) {
        frame_layout = target_framelayout_id;
        fm = mManager;
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
