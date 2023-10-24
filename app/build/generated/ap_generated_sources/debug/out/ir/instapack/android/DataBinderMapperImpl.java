package ir.instapack.android;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import ir.instapack.android.databinding.ActivityMainBindingImpl;
import ir.instapack.android.databinding.ActivitySearchUserBindingImpl;
import ir.instapack.android.databinding.FragmentComingSoonBindingImpl;
import ir.instapack.android.databinding.FragmentGetCreditBindingImpl;
import ir.instapack.android.databinding.FragmentHomeBindingImpl;
import ir.instapack.android.databinding.FragmentProfileBindingImpl;
import ir.instapack.android.databinding.FragmentTutorialBindingImpl;
import ir.instapack.android.databinding.ItemSearchUserBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_ACTIVITYSEARCHUSER = 2;

  private static final int LAYOUT_FRAGMENTCOMINGSOON = 3;

  private static final int LAYOUT_FRAGMENTGETCREDIT = 4;

  private static final int LAYOUT_FRAGMENTHOME = 5;

  private static final int LAYOUT_FRAGMENTPROFILE = 6;

  private static final int LAYOUT_FRAGMENTTUTORIAL = 7;

  private static final int LAYOUT_ITEMSEARCHUSER = 8;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(8);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(ir.instapack.android.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ir.instapack.android.R.layout.activity_search_user, LAYOUT_ACTIVITYSEARCHUSER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ir.instapack.android.R.layout.fragment_coming_soon, LAYOUT_FRAGMENTCOMINGSOON);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ir.instapack.android.R.layout.fragment_get_credit, LAYOUT_FRAGMENTGETCREDIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ir.instapack.android.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ir.instapack.android.R.layout.fragment_profile, LAYOUT_FRAGMENTPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ir.instapack.android.R.layout.fragment_tutorial, LAYOUT_FRAGMENTTUTORIAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ir.instapack.android.R.layout.item_search_user, LAYOUT_ITEMSEARCHUSER);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSEARCHUSER: {
          if ("layout/activity_search_user_0".equals(tag)) {
            return new ActivitySearchUserBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_search_user is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCOMINGSOON: {
          if ("layout/fragment_coming_soon_0".equals(tag)) {
            return new FragmentComingSoonBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_coming_soon is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTGETCREDIT: {
          if ("layout/fragment_get_credit_0".equals(tag)) {
            return new FragmentGetCreditBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_get_credit is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILE: {
          if ("layout/fragment_profile_0".equals(tag)) {
            return new FragmentProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTUTORIAL: {
          if ("layout/fragment_tutorial_0".equals(tag)) {
            return new FragmentTutorialBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_tutorial is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSEARCHUSER: {
          if ("layout/item_search_user_0".equals(tag)) {
            return new ItemSearchUserBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_search_user is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(3);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    result.add(new carbon.DataBinderMapperImpl());
    result.add(new ir.mhd.ninjadialog.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(6);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "data");
      sKeys.put(2, "listener");
      sKeys.put(3, "viewModel");
      sKeys.put(4, "user");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(8);

    static {
      sKeys.put("layout/activity_main_0", ir.instapack.android.R.layout.activity_main);
      sKeys.put("layout/activity_search_user_0", ir.instapack.android.R.layout.activity_search_user);
      sKeys.put("layout/fragment_coming_soon_0", ir.instapack.android.R.layout.fragment_coming_soon);
      sKeys.put("layout/fragment_get_credit_0", ir.instapack.android.R.layout.fragment_get_credit);
      sKeys.put("layout/fragment_home_0", ir.instapack.android.R.layout.fragment_home);
      sKeys.put("layout/fragment_profile_0", ir.instapack.android.R.layout.fragment_profile);
      sKeys.put("layout/fragment_tutorial_0", ir.instapack.android.R.layout.fragment_tutorial);
      sKeys.put("layout/item_search_user_0", ir.instapack.android.R.layout.item_search_user);
    }
  }
}
