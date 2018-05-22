package com.xxm.mmd.component_texmath;


import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xxm.mmd.component_texmath.core.AjLatexMath;
import com.xxm.mmd.component_texmath.core.Insets;
import com.xxm.mmd.component_texmath.core.TeXConstants;
import com.xxm.mmd.component_texmath.core.TeXFormula;
import com.xxm.mmd.component_texmath.core.TeXIcon;

public class ExampleFragment extends Fragment implements OnClickListener {

	public static Fragment newInstance(String latex,
			int tag) {
		ExampleFragment fragment = new ExampleFragment();
		fragment.setTag(tag);
		fragment.setFormula(latex);
		return fragment;
	}

	private ImageView mImageView;
	private String mLatex;
	private float mTextSize = 16;
	private int mTag;
	private EditText mSizeText;

	private void setFormula(String latex) {
		mLatex = latex;
	}

	private void setTag(int tag) {
		mTag = tag;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("latex", mLatex);
		outState.putFloat("textsize", mTextSize);
		outState.putInt("tag", mTag);
		super.onSaveInstanceState(outState);
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mLatex = savedInstanceState.getString("latex");
			mTextSize = savedInstanceState.getFloat("textsize");
			mTag = savedInstanceState.getInt("tag");
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.texmath_fragment_example, container, false);
		mImageView = (ImageView) layout.findViewById(R.id.logo);
		mSizeText = (EditText) layout.findViewById(R.id.size);
		layout.findViewById(R.id.set_textsize).setOnClickListener(this);
//		try {

			setformula();
//		} catch (Throwable throwable) {

//		}

		return layout;
	}

	private void setformula() {
		int w = getResources().getDisplayMetrics().widthPixels;
		int h = getResources().getDisplayMetrics().heightPixels;
		TeXFormula formula = new TeXFormula(mLatex);
		TeXIcon icon = formula.new TeXIconBuilder()
				.setStyle(TeXConstants.STYLE_DISPLAY)
				.setSize(mTextSize)
				.setWidth(TeXConstants.UNIT_PIXEL, w, TeXConstants.ALIGN_LEFT)
				.setIsMaxWidth(true)
				.setInterLineSpacing(TeXConstants.UNIT_PIXEL,
						AjLatexMath.getLeading(mTextSize)).build();
		icon.setInsets(new Insets(5, 5, 5, 5));

		Bitmap image = Bitmap.createBitmap(icon.getIconWidth(), icon.getIconHeight(),
				Config.ARGB_8888);

		Canvas g2 = new Canvas(image);
		g2.drawColor(Color.WHITE);
		icon.paintIcon(g2, 0, 0);

		Bitmap scaleimage = scaleBitmapAndKeepRation(image, h, w);

		mImageView.setImageBitmap(scaleimage);
	}

	public Bitmap scaleBitmapAndKeepRation(Bitmap targetBmp,
			int reqHeightInPixels, int reqWidthInPixels) {
		Bitmap bitmap = Bitmap.createBitmap(reqWidthInPixels,
				reqHeightInPixels, Config.ARGB_8888);
		Canvas g = new Canvas(bitmap);
		g.drawBitmap(targetBmp, 0, 0, null);
		targetBmp.recycle();
		return bitmap;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	};

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.set_textsize) {
			mTextSize = Integer.valueOf(mSizeText.getText().toString());
			setformula();
		}
	}
}