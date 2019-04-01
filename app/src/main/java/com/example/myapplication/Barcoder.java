package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class Barcoder {

    public String scan(Context context, Bitmap bitmap) {
        BarcodeDetector detector = new BarcodeDetector.Builder(context)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        Barcode thisCode = barcodes.valueAt(0);
        String result=thisCode.rawValue;
        System.out.println("REEEEEEEESUUULT"+result);
        return result;
    }

}