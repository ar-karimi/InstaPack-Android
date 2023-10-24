package ir.instapack.android.services.searchUser.dataModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SearchUserModel {

    @SerializedName("list")
    private List<ListItem> list;

    @SerializedName("status")
    private String status;

    public List<ListItem> getList() {
        return list;
    }

    public String getStatus() {
        return status;
    }


    public class ListItem {

        @SerializedName("user")
        private User user;

        public User getUser() {
            return user;
        }


        public class User {

            @SerializedName("full_name")
            private String fullName;

            @SerializedName("pk")
            private long pk;

            @SerializedName("profile_pic_url")
            private String profilePicUrl;

            @SerializedName("username")
            private String username;

            public String getFullName() {
                return fullName;
            }

            public long getPk() {
                return pk;
            }

            public String getProfilePicUrl() {
                return profilePicUrl;
            }

            public String getUsername() {
                return username;
            }
        }
    }
}