package com.example.admin.androidplayground.content;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.androidplayground.R;
import com.example.admin.androidplayground.adapter.MenuAdapter;
import com.example.admin.androidplayground.model.Menu;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView profile,profile2;
    ArrayList<Menu>list = new ArrayList<>();

    private  int Gallery = 1;
    private int Camera = 2;
    private  static final String directory = "/profile_pics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_home_menu);
        context = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        profile = (ImageView)findViewById(R.id.iv_profilepic);
        profile2 = (ImageView)findViewById(R.id.iv_profilepic2);
        mLayoutManager = new GridLayoutManager(context,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        list.add(new Menu(getString(R.string.spannable),getString(R.string.about_spannable)));
        list.add(new Menu(getString(R.string.mvp),getString(R.string.about_mvp)));
        list.add(new Menu(getString(R.string.shortcuts),getString(R.string.about_shortcuts)));
        list.add(new Menu(getString(R.string.addProPic),getString(R.string.about_profile_pictures)));
        mAdapter = new MenuAdapter(context, list,new MenuAdapter.OnItemClickListener() {
            @Override
            public void onPostClick(long id) {
                Toast.makeText(context, "Item "+ id, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Option");
        String[]pictureDialogItems ={
                "Choose from gallery",
                "Use Camera"};
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        fromGallery();
                        break;
                    case 1:
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                == PackageManager.PERMISSION_DENIED){
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, Camera);}
                        takePhoto();
                        break;
                }
            }
        });
        pictureDialog.show();
        }


    private void takePhoto() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,Camera);
    }

    private void fromGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent,Gallery);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }
        if (requestCode == Gallery) {
            ClipData clipData = data.getClipData();

            if (data != null && clipData == null) {

                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    profile.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {

                if (clipData != null) {
                    ArrayList<Uri> uris = new ArrayList<>();
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Uri uri = item.getUri();
                        uris.add(uri);
                    }

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uris.get(0));
                        profile.setImageBitmap(bitmap);
                        Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uris.get(1));
                        profile2.setImageBitmap(bitmap2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                Toast.makeText(MainActivity.this, "Something happened " + clipData.getItemCount(), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == Camera && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profile.setImageBitmap(imageBitmap);
        }
    }






    //????????
    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
    }




