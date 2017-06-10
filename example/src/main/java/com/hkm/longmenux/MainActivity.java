package com.hkm.longmenux;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hkm.longmenu.Bind;
import com.hkm.longmenu.LongMenuComponent;
import com.hkm.longmenu.MAitem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LongMenuComponent fragment_byID = (LongMenuComponent) getFragmentManager().findFragmentById(R.id.menu);

        Bind b = new Bind(80, this);
        b.setItemHeight(90);
        b.setPattern(Bind.Pattern.DEFAULTBLACK);
        b.setIconPadding(0f);
        b.setWithSeparator(false);
        b.setResIdCompanyLogo(R.drawable.icon_sio);
        b.setAddListMenu(new MAitem(R.drawable.home128, "Home"));
        b.setAddListMenu(new MAitem(R.drawable.helpquestionmark128, "Help"));
        b.setAddListMenu(new MAitem(R.drawable.paperplane128, "Paper"));
        b.setAddListMenu(new MAitem(R.drawable.settings128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.gift128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.roundbubbleheart128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.wifi128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.user128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.diamond28, "Settings", MenuDishes.class));
        b.setAddListMenu(new MAitem(R.drawable.controllerconsole128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.paperplane128, "Paper"));
        b.setAddListMenu(new MAitem(R.drawable.settings128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.gift128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.roundbubbleheart128, "Settings"));
        b.setAddListMenu(new MAitem(R.drawable.wifi128, "Settings"));
        b.setRenderTextColor(ContextCompat.getColor(getApplication(), R.color.whitenam));
        fragment_byID.init(b);

        //Fragment currentFragment = fragmentManager.findFragmentByTag("fragmentTag");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
