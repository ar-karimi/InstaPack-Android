package ir.instapack.android.services.searchUser.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.instapack.android.databinding.ItemSearchUserBinding;
import ir.instapack.android.services.searchUser.dataModels.SearchUserModel;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder> {

    private SearchUserViewHolder.OnItemClickListener listener;
    private List<SearchUserModel.ListItem.User> users;

    public SearchUserAdapter(List<SearchUserModel.ListItem.User> users, SearchUserViewHolder.OnItemClickListener listener) {
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSearchUserBinding binding = ItemSearchUserBinding.inflate(inflater, parent, false);
        return new SearchUserViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUserViewHolder holder, int position) {
        holder.bindUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class SearchUserViewHolder extends RecyclerView.ViewHolder {

        private ItemSearchUserBinding binding;
        private OnItemClickListener listener;

        public SearchUserViewHolder(ItemSearchUserBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void bindUser(SearchUserModel.ListItem.User user) {
            binding.setUser(user);
            binding.setListener(listener);

            binding.userProfilePic.setImageURI(Uri.parse(user.getProfilePicUrl()));




            binding.executePendingBindings();

        }

        public interface OnItemClickListener {
            void onItemClicked(SearchUserModel.ListItem.User user);
        }
    }

}
