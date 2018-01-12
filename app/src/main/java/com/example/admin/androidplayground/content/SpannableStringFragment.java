package com.example.admin.androidplayground.content;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.androidplayground.R;
import com.example.admin.androidplayground.databinding.FragmentSpannableStringBinding;
import com.example.admin.androidplayground.model.Menu;

public class SpannableStringFragment extends Fragment {

    FragmentSpannableStringBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_spannable_string,container,false);
        applySpannableStringStyle();
        Bundle bundle = this.getArguments();
        String title="sbdjd";
        String content = null;
        Menu x =new Menu();
        if(bundle!=null){
            title = bundle.getString("title");
           content = bundle.getString("content");
        }
        binding.tvTitle.setText(title);
        binding.tvContent.setText(content);
        return  binding.getRoot();
    }

    public void applySpannableStringStyle() {
        Spannable style;
        int textLength = binding.large.getText().length();
        int docuTextLength = binding.androidDoc.getText().length();
        //int startText = position.indexOf(textLength)+1;
//        int startText = textLength - ;
      //  int endText = textLength - 2;
        int docuEndText = docuTextLength;
        int startText = 0;
        int endText = textLength;
        style = new SpannableString(binding.large.getText());
        style.setSpan(new RelativeSizeSpan(2f), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.large.setText(style);

        style = new SpannableString(binding.bold.getText());
        style.setSpan(new StyleSpan(Typeface.BOLD), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.bold.setText(style);

        style = new SpannableString(binding.underline.getText());
        style.setSpan(new UnderlineSpan(), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.underline.setText(style);

        style = new SpannableString(binding.italic.getText());
        style.setSpan(new StyleSpan(Typeface.ITALIC), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.italic.setText(style);

        style = new SpannableString(binding.strikethrough.getText());
        style.setSpan(new StrikethroughSpan(), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.strikethrough.setText(style);

        style = new SpannableString(binding.coloured.getText());
        style.setSpan(new ForegroundColorSpan(Color.CYAN), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.coloured.setText(style);

        style = new SpannableString(binding.highlighted.getText());
        style.setSpan(new BackgroundColorSpan(Color.CYAN), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.highlighted.setText(style);

        style = new SpannableString(binding.superscript.getText());
        style.setSpan(new SuperscriptSpan(), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.superscript.setText(style);

        style = new SpannableString(binding.subscript.getText());
        style.setSpan(new SubscriptSpan(), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.subscript.setText(style);

        style = new SpannableString(binding.androidDoc.getText());
        style.setSpan(new URLSpan("https://developer.android.com/reference/android/text/SpannableString.html"), startText, docuEndText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.androidDoc.setText(style);
        binding.androidDoc.setMovementMethod(LinkMovementMethod.getInstance());

        style = new SpannableString(binding.url.getText());
        style.setSpan(new URLSpan("https://developer.android.com"), startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.url.setText(style);
        binding.url.setMovementMethod(LinkMovementMethod.getInstance());//enable response when clicked

        style = new SpannableString(binding.clickable.getText());
        style.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Item clicked", Toast.LENGTH_SHORT).show();

            }
        }, startText, endText, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.clickable.setText(style);
        binding.clickable.setMovementMethod(LinkMovementMethod.getInstance());//enable response when clicked

    }
}
