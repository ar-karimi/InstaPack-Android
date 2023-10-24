package ir.instapack.android.databinding;
import ir.instapack.android.R;
import ir.instapack.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemSearchUserBindingImpl extends ItemSearchUserBinding implements ir.instapack.android.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.user_profile_pic, 3);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemSearchUserBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ItemSearchUserBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (com.facebook.drawee.view.SimpleDraweeView) bindings[3]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.userId.setTag(null);
        this.userName.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new ir.instapack.android.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.user == variableId) {
            setUser((ir.instapack.android.services.searchUser.dataModels.SearchUserModel.ListItem.User) variable);
        }
        else if (BR.listener == variableId) {
            setListener((ir.instapack.android.services.searchUser.adapters.SearchUserAdapter.SearchUserViewHolder.OnItemClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUser(@Nullable ir.instapack.android.services.searchUser.dataModels.SearchUserModel.ListItem.User User) {
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }
    public void setListener(@Nullable ir.instapack.android.services.searchUser.adapters.SearchUserAdapter.SearchUserViewHolder.OnItemClickListener Listener) {
        this.mListener = Listener;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.listener);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        ir.instapack.android.services.searchUser.dataModels.SearchUserModel.ListItem.User user = mUser;
        ir.instapack.android.services.searchUser.adapters.SearchUserAdapter.SearchUserViewHolder.OnItemClickListener listener = mListener;
        java.lang.String userFullName = null;
        java.lang.String userUsername = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (user != null) {
                    // read user.fullName
                    userFullName = user.getFullName();
                    // read user.username
                    userUsername = user.getUsername();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userId, userUsername);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userName, userFullName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // user
        ir.instapack.android.services.searchUser.dataModels.SearchUserModel.ListItem.User user = mUser;
        // listener != null
        boolean listenerJavaLangObjectNull = false;
        // listener
        ir.instapack.android.services.searchUser.adapters.SearchUserAdapter.SearchUserViewHolder.OnItemClickListener listener = mListener;



        listenerJavaLangObjectNull = (listener) != (null);
        if (listenerJavaLangObjectNull) {



            listener.onItemClicked(user);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): listener
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}