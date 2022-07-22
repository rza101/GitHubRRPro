package com.rhezarijaya.githubrrpro.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchResponse implements Parcelable {

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("incomplete_results")
	private boolean incompleteResults;

	@SerializedName("items")
	private List<UserDetail> items;

	protected SearchResponse(Parcel in) {
		totalCount = in.readInt();
		incompleteResults = in.readByte() != 0;
	}

	public static final Creator<SearchResponse> CREATOR = new Creator<SearchResponse>() {
		@Override
		public SearchResponse createFromParcel(Parcel in) {
			return new SearchResponse(in);
		}

		@Override
		public SearchResponse[] newArray(int size) {
			return new SearchResponse[size];
		}
	};

	public int getTotalCount(){
		return totalCount;
	}

	public boolean isIncompleteResults(){
		return incompleteResults;
	}

	public List<UserDetail> getItems(){
		return items;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(totalCount);
		dest.writeByte((byte) (incompleteResults ? 1 : 0));
	}

	@NonNull
	@Override
	public String toString() {
		return "SearchResponse{" +
				"totalCount=" + totalCount +
				", incompleteResults=" + incompleteResults +
				", items=" + items +
				'}';
	}
}