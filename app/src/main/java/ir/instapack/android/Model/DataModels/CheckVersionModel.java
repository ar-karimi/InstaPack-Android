package ir.instapack.android.Model.DataModels;

import com.google.gson.annotations.SerializedName;

public class CheckVersionModel {

    @SerializedName("data")
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }


    public class Data {

        @SerializedName("lastVersion")
        private String lastVersion;

        @SerializedName("stableVersion")
        private String stableVersion;

        public void setLastVersion(String lastVersion) {
            this.lastVersion = lastVersion;
        }

        public String getLastVersion() {
            return lastVersion;
        }

        public void setStableVersion(String stableVersion) {
            this.stableVersion = stableVersion;
        }

        public String getStableVersion() {
            return stableVersion;
        }

    }
}