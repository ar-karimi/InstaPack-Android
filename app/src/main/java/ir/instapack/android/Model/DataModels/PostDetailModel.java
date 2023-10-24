package ir.instapack.android.Model.DataModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PostDetailModel{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}


	public class Data{

		@SerializedName("comments")
		private Comments comments;

		@SerializedName("description")
		private String description;

		@SerializedName("categories")
		private List<Object> categories;

		@SerializedName("title")
		private String title;

		@SerializedName("picture")
		private String picture;

		public void setComments(Comments comments){
			this.comments = comments;
		}

		public Comments getComments(){
			return comments;
		}

		public void setDescription(String description){
			this.description = description;
		}

		public String getDescription(){
			return description;
		}

		public void setCategories(List<Object> categories){
			this.categories = categories;
		}

		public List<Object> getCategories(){
			return categories;
		}

		public void setTitle(String title){
			this.title = title;
		}

		public String getTitle(){
			return title;
		}

		public void setPicture(String picture){
			this.picture = picture;
		}

		public String getPicture(){
			return picture;
		}


		public class Comments{

			@SerializedName("data")
			private List<Object> data;

			public void setData(List<Object> data){
				this.data = data;
			}

			public List<Object> getData(){
				return data;
			}
		}
	}
}