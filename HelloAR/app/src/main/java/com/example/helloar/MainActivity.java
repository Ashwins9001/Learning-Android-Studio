package com.example.helloar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {
    private ArFragment arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        //lambda function to pass in func directly alongside params w/out naming
        //of data type: HitResult, Plane, MotionEvent
        //HitResults are collection of collision points b/w model and camera-gen scene surfaces
        //HitResult can be either feature points or a plane
        //MotionEvents indicate obj movement in scene
        //arFragment is section of UI that sets off defn method on Tap
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            //Constant adjustments to planes and feature point intersections in ARCore
            //Anchor pins pos for virtual obj to be gen to maintain universal coordinate axis
            //Derived from hitResult
            Anchor anchor = hitResult.createAnchor();
            //Scenes are based on a tree-like structure, with each renderable as a node
            //Create parent-child relations allows children to move, scale, rotate w parent
            //Pass builder current context and parse .sfb for model information (ex., colors)
            //Build model, pass renderable obj to func which if returns error
            //Creates alert dialog via lambda func
            ModelRenderable.builder()
                    .setSource(this, Uri.parse("model.sfb"))
                    .build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                    .exceptionally(throwable -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(throwable.getMessage()).show();
                        return null;
                    });
        });
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        //AnchorNode positions based on ARCore anchor, obj will be placed there
        AnchorNode anchorNode = new AnchorNode(anchor);
        //pos cannot be changed on anchorNode after set
        //transformable node can be scaled, moved using gestures via transform system
        //ArFragment automatically calls onTouch(HitTestResult, MotionEvent) to detect gestures
        //Transformation system used to detect gestures, encased w/ in ArFragment
        TransformableNode transformNode = new TransformableNode(arFragment.getTransformationSystem());
        transformNode.setParent(anchorNode);
        transformNode.setRenderable(modelRenderable);
        //user taps fixed plane for anchor, model superimposed as child of it, can be transformed
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformNode.select();
    }
}
