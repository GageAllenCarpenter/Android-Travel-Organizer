package edu.wgu.capstone.view.dialog;

import androidx.fragment.app.DialogFragment;

public interface DialogFactory<T extends DialogListener> {
    DialogFragment createDialogFragment(T listener);
    DialogFragment editDialogFragment(T listener);
    DialogFragment shareDialogFragment(T listener);
}