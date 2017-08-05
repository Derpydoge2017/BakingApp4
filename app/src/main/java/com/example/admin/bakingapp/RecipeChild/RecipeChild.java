package com.example.admin.bakingapp.RecipeChild;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.admin.bakingapp.Data.RecipeContract;
import com.example.admin.bakingapp.NetworkUtils;
import com.example.admin.bakingapp.R;
import com.example.admin.bakingapp.Recipe.Recipe;
import com.example.admin.bakingapp.RecipeChild.Ingredients.Ingredient;
import com.example.admin.bakingapp.RecipeChild.Ingredients.IngredientAdapter;
import com.example.admin.bakingapp.RecipeChild.Ingredients.IngredientJSONData;
import com.example.admin.bakingapp.RecipeChild.Instructions.Instruction;
import com.example.admin.bakingapp.RecipeDisplay.RecipeDisplayChildFragment;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 12-Jul-17.
 */

public class RecipeChild extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Flag determines if this is a one or two pane layout
    private boolean isTwoPane = false;

    private Recipe mRecipe;

    private TextView RecipeName;
    private String name;

    String RECIPE_BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private Context context;

    private RecyclerView mIngredientRV;

    public static final String STEP_DETAILS = "step_details";

    private IngredientAdapter mIngredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        // Call this to determine which layout we are in (tablet or phone)
        determinePaneLayout();

        //Get EXTRA from intent and attach to Fragment as Argument

        if (savedInstanceState == null) {
            mRecipe = getIntent().getParcelableExtra("android.intent.extra.TITLE");
            if (isTwoPane) {

                Context context = this;
                mIngredientRV = (RecyclerView) findViewById(R.id.ingredient_rv);
                GridLayoutManager gridIngredientManager = new GridLayoutManager(context, 1);
                mIngredientRV.setLayoutManager(gridIngredientManager);
                mIngredientAdapter = new IngredientAdapter();
                mIngredientRV.setAdapter(mIngredientAdapter);

                loadIngredientData();

                RecipeChildFragmentTablet tabletChildFragment = new RecipeChildFragmentTablet();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentInstructionList, tabletChildFragment).commit();

            } else {

                RecipeChildFragment recipeChildFragment = new RecipeChildFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.recipeChildContainer, recipeChildFragment).commit();

            }

        }

    }

    private void determinePaneLayout() {

        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.fragmentDetail);

        // If there is a second pane for details
        if (fragmentItemDetail != null) {
            isTwoPane = true;
        }

    }


    private void loadIngredientData() {
        new IngredientQueryTask().execute();
        showIngredientDataView();
    }

    public class IngredientQueryTask extends AsyncTask<String, Void, ArrayList<Ingredient>> {

        @Override
        protected ArrayList doInBackground(String... params) {
            URL recipeSearchUrl = NetworkUtils.buildUrl(RECIPE_BASE_URL);
            try {
                String jsonRecipeResponse = NetworkUtils
                        .getResponseFromHttpUrl(recipeSearchUrl);

                ArrayList simpleJsonIngredientData = IngredientJSONData
                        .getIngredientDataStringsFromJson(context, jsonRecipeResponse);


                return simpleJsonIngredientData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Ingredient> ingredientData) {
            mIngredientAdapter.setIngredientData(ingredientData);
        }

    }


    private void showIngredientDataView() {
        /* Make sure the recipe data is visible */
        mIngredientRV.setVisibility(View.VISIBLE);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
