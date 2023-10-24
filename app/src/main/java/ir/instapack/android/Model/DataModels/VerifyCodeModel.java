package ir.instapack.android.Model.DataModels;

import com.google.gson.annotations.SerializedName;

public class VerifyCodeModel{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}


	public class Data{

		@SerializedName("api_token")
		private String apiToken;

		public void setApiToken(String apiToken){
			this.apiToken = apiToken;
		}

		public String getApiToken(){
			return apiToken;
		}
	}
}