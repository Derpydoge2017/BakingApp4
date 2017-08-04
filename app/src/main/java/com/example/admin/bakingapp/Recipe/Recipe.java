package com.example.admin.bakingapp.Recipe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 20-May-17.
 */

public class Recipe implements Parcelable {

    private Integer recipeId;
    private String recipeName;
    private String recipeImageURL;

    public Recipe() {

    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer id) {
        this.recipeId = id;
    }


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String name) {
        this.recipeName = name;
    }


    public String getRecipeImageURL() {
        return recipeImageURL;
    }

    public void setRecipeImageURL(String url) {
        this.recipeImageURL = url;
    }


    protected Recipe(Parcel in) {
        recipeId = in.readInt();
        recipeName = in.readString();
        recipeImageURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(recipeId);
        dest.writeString(recipeName);
        dest.writeString(recipeImageURL);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };


}
