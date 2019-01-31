package com.zxu.masterofpainting.fragment;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.ShowIngredientsActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.app.Activity.RESULT_OK;

public class TakePhotoFragment extends Fragment {
    private Handler handler = null;
    private Bitmap bitmap;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOSE_PHOTO = 2;
    private ImageView picture_Distinguish;
    private Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_takephoto,container,false);
        handler = new Handler();

        iniview(view);

        return view;
    }

    public void iniview(View view){
        picture_Distinguish = (ImageView) view.findViewById(R.id.things_image_view);
        picture_Distinguish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takePhoto();
                startActivity(new Intent(getContext(),ShowIngredientsActivity.class));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.setType("image/*");
                    startActivityForResult(intent, CHOSE_PHOTO); // 打开相册
                } else {
                    Toast.makeText(getContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver()
                                .openInputStream(imageUri));
//                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统
                        String imagePath = null;
                        Uri uri = data.getData();
                        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
                        if (DocumentsContract.isDocumentUri(getContext(), uri)) {
                            // 如果是document类型的Uri，则通过document id处理
                            String docId = DocumentsContract.getDocumentId(uri);
                            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                                String id = docId.split(":")[1]; // 解析出数字格式的id
                                String selection = MediaStore.Images.Media._ID + "=" + id;
                                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                                imagePath = getImagePath(contentUri, null);
                            }
                        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                            // 如果是content类型的Uri，则使用普通方式处理
                            imagePath = getImagePath(uri, null);
                        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                            // 如果是file类型的Uri，直接获取图片路径即可
                            imagePath = uri.getPath();
                        }
                        displayImage(imagePath); // 根据图片路径显示图片
                    } else {
                        //4.4版本以下
                        Uri uri = data.getData();
                        String imagePath = getImagePath(uri, null);
                        displayImage(imagePath);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void takePhoto(){
        // 创建File对象，用于存储拍照后的图片
        File outputImage = new File(getContext().getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(outputImage);
        } else {
            imageUri = FileProvider.getUriForFile(getContext(), "com.zxu.masterofpainting.fileprovider", outputImage);
        }
        // 启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
//        startActivity(new Intent(getContext(),CollectionFragment.class));
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                bitmap = getBitmap("http://bmob-cdn-22072.b0.upaiyun.com/2018/10/25/42f25db6400e3c2880e1fb79c60cfa10.png");
//                if (bitmap != null) {
//                    handler.post(runnableUi);
//                }
//            }
//        }).start();

    }

    //供用户从手机中选择图片
    public void chosePhoto() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1 );
        } else {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            startActivityForResult(intent, CHOSE_PHOTO);
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContext().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            //picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getContext(), "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    public  Bitmap getBitmap(String url) {
        URL imageURL = null;
        Bitmap bitmap = null;
        Log.e("inuni","URL = "+url);
        try {
            imageURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) imageURL
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
