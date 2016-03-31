package com.aurum.activities;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ExhibitionMainListModel
{



	@SerializedName("cid")
	public String cid;

	@SerializedName("cnm")
	public String cnm;

	@SerializedName("cimg")
	public String cimg;

	@SerializedName("total_subct")
	public String total_subct;
	
	@SerializedName("product")
	public ArrayList<ExhibitionSubListModel> arraySubList;
	
	

}
