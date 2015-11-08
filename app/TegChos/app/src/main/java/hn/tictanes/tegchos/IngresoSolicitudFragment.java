package hn.tictanes.tegchos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class IngresoSolicitudFragment extends Fragment {

    public IngresoSolicitudFragment() {
    }

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_SELECT_PHOTO = 0;
    private int PICK_IMAGE_REQUEST = 1;
    protected Uri imageUri;
    private String photoPath;
    private View rootView;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "tegchos";

    private Uri fileUri; // file url to store image/video
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ingreso_solicitud, container, false);

        Button btnEnviar = (Button) (rootView.findViewById(R.id.btnEnviarSolicitud));
        ImageButton imgButton = (ImageButton) (rootView.findViewById(R.id.imgButton));

        EditText etNombre = (EditText) (rootView.findViewById(R.id.etNombre));
        EditText etDireccion = (EditText) (rootView.findViewById(R.id.etDireccion));
        EditText etTelefono = (EditText) (rootView.findViewById(R.id.etTelefono));
        EditText etCorreo = (EditText) (rootView.findViewById(R.id.etCorreo));
        EditText etObservacion = (EditText) (rootView.findViewById(R.id.etObservacion));
        try {
            JSONObject joEnvio = new JSONObject();
            joEnvio.put("nombre", etNombre.getText());
            joEnvio.put("direccion",etDireccion.getText());
            joEnvio.put("telefono",etTelefono.getText());
            joEnvio.put("correo",etCorreo.getText());
            joEnvio.put("observacion",etObservacion.getText());

            

        } catch (JSONException e) {
            e.printStackTrace();
        }


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                                // start the image capture Intent
                                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

//                                String state = Environment.getExternalStorageState();
//                                if (Environment.MEDIA_MOUNTED.equals(state))
//                                {
//                                    long captureTime = System.currentTimeMillis();
//                                    photoPath = Environment.getExternalStorageDirectory()
//                                            + "/DCIM/Camera/Point" + captureTime + ".jpg";
//                                    try
//                                    {
//                                        Intent intent =
//                                                new Intent("android.media.action.IMAGE_CAPTURE");
//                                        File photo = new File(photoPath);
//                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
//                                        startActivityForResult
//                                                (Intent.createChooser(intent, "Capture Image"), REQUEST_CAMERA);
//
//                                        if (photoPath!=null) {
//                                            File imgFile = new File(photoPath);
//                                            if (imgFile.exists()) ;
//                                            {
//                                                Bitmap myBitmap =
//                                                        BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                                                ImageButton myImage = (ImageButton) rootView.findViewById(R.id.imgButton);
//                                                int nh = (int) ( myBitmap.getHeight() * (512.0 / myBitmap.getWidth()) );
//                                                Bitmap scaled = Bitmap.createScaledBitmap(myBitmap, 512, nh, true);
//                                                myImage.setImageBitmap(scaled);
////                                                ((ImageButton) rootView.findViewById(R.id.imgButton)).setImageBitmap(myBitmap);
//                                            }
//                                        }
//                                    }
//                                    catch (Exception e)
//                                    {
//                                        e.printStackTrace();
//                                    }
//                                }
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
//                                Intent ii = new Intent(Intent.ACTION_PICK) ;
//                                        ii.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                                MediaStore.Images.Media.CONTENT_TYPE) ;
//                                        startActivityForResult(ii,REQUEST_SELECT_PHOTO);
//                                if (photoPath!=null) {
//                                    File imgFile = new File(photoPath);
//                                    if (imgFile.exists()) ;
//                                    {
//                                        Bitmap myBitmap =
//                                                BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                                        ImageButton myImage = (ImageButton) rootView.findViewById(R.id.imgButton);
//                                        //myImage.setImageBitmap(myBitmap);
//                                        ((ImageButton) rootView.findViewById(R.id.imgButton)).setImageBitmap(myBitmap);
//                                    }
//                                }
                                Intent intent2 = new Intent();
// Show only images, no videos or anything else
                                intent2.setType("image/*");
                                intent2.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                                startActivityForResult(Intent.createChooser(intent2, "Select Picture"), PICK_IMAGE_REQUEST);
                                break;
                        }
                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Seleccione una opcion...")
                        .setPositiveButton("Tomar Foto", dialogClickListener)
                        .setNegativeButton("Galeria", dialogClickListener)
                        .setTitle("Cargar foto")
                        .show();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                    Toast.makeText(getActivity(), "Enviando...", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                ImageView imageView = (ImageView) rootView.findViewById(R.id.imgButton);
                imageView.setImageResource(android.R.color.transparent);
                imageView.setBackgroundResource(0);
                imageView.setImageBitmap(scaled);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getActivity(),
                        "No quiso esoger Nada", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getActivity(),
                        "No se pudo escger nada", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    /*
     * Display image from a path to ImageView
     */
    private void previewCapturedImage() {
        try {
            // hide video preview
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imgButton);
            imageView.setBackgroundResource(0);
            imageView.setImageResource(android.R.color.transparent);
            imageView.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath());
            int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
            imageView.setImageBitmap(scaled);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }
}
