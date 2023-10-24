package ir.instapack.android.Model.DataModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchCategoriesModel {

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private Data data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public class Data {

        @SerializedName("categories")
        private List<CategoriesItem> categories;

        @SerializedName("sliders")
        private List<SlidersItem> sliders;

        public void setCategories(List<CategoriesItem> categories) {
            this.categories = categories;
        }

        public List<CategoriesItem> getCategories() {
            return categories;
        }

        public void setSliders(List<SlidersItem> sliders) {
            this.sliders = sliders;
        }

        public List<SlidersItem> getSliders() {
            return sliders;
        }


        public class CategoriesItem {

            @SerializedName("hasChild")
            private boolean hasChild;

            @SerializedName("name")
            private String name;

            @SerializedName("icon")
            private String icon;

            @SerializedName("id")
            private String id;

            public void setHasChild(boolean hasChild) {
                this.hasChild = hasChild;
            }

            public boolean isHasChild() {
                return hasChild;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getIcon() {
                return icon;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getId() {
                return id;
            }
        }

        public class SlidersItem {

            @SerializedName("link")
            private String link;

            @SerializedName("picture")
            private String picture;

            @SerializedName("target")
            private String target;

            public void setLink(String link) {
                this.link = link;
            }

            public String getLink() {
                return link;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getPicture() {
                return picture;
            }

            public void setTarget(String target) {
                this.target = target;
            }

            public String getTarget() {
                return target;
            }
        }
    }
}