package com.dy.listviewdemo;

import android.animation.StateListAnimator;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by dy on 2016/12/21 13:44.
 */

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private SparseArray<String> mStringSparseArray;

    public MyAdapter(Context context) {
        this.mContext = context;
        mStringSparseArray = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        EditTextHolder editTextHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_edittext, parent, false);
            editTextHolder = new EditTextHolder();
            editTextHolder.mEditText = (EditText) convertView.findViewById(R.id.editText);
            editTextHolder.mMyTextWatcher = new MyTextWatcher(position, mStringSparseArray);
            editTextHolder.mEditText.addTextChangedListener(editTextHolder.mMyTextWatcher);

            convertView.setTag(editTextHolder);
        } else {
            editTextHolder = (EditTextHolder) convertView.getTag();

            editTextHolder.updatePosition(position);
        }

        System.out.println(position);

        editTextHolder.mEditText.setText(mStringSparseArray.get(position));

        return convertView;
    }

    static class EditTextHolder {
        EditText mEditText;
        MyTextWatcher mMyTextWatcher;

        public void updatePosition(int position) {
            mMyTextWatcher.updatePosition(position);
        }
    }

    static class MyTextWatcher implements TextWatcher {
        private int position;
        private SparseArray<String> sparseArray;

        public void updatePosition(int position) {
            this.position = position;
        }

        public MyTextWatcher(int position, SparseArray<String> sparseArray) {
            this.position = position;
            this.sparseArray = sparseArray;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            sparseArray.put(position, s.toString());
        }
    }
}
