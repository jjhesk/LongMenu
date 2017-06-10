# LongMenu
Easy Setup the dynamic long menu in JAVA Android

why
==========
Look into some simple designed yet in slow performance webapp and it needs to be enhanced and rendered in native mode. 



setup
==========
Nothing need to think about and just call in a single line.

```xml
 <fragment
        android:id="@+id/menu"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:name="com.hkm.longmenu.LongMenuComponent" />

```
Get the componenet in the code
```java
   LongMenuComponent fragment_byID = (LongMenuComponent) getFragmentManager().findFragmentById(R.id.menu);

        Bind b = new Bind(80, this);
        b.setItemHeight(90);
  
        b.setIconPadding(0f);
        b.setWithSeparator(false);
        b.setResIdCompanyLogo(R.drawable.icoshlogo);
        b.setAddListMenu(new menuitem(R.drawable.home128, "Home"));
        b.setAddListMenu(new menuitem(R.drawable.diamond28, "Settings", MenuDishes.class));

        fragment_byID.init(b);
        
 ```
 
Gradle
===================
```gradle
compile  'com.hkm.ui:longmenu:0.2.2'
```
Overview
===================
![image](screen/device-2015-04-11-175843.png)![image](screen/device-2015-04-11-180023.png)
