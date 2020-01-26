package backbencers.nub.dailycostcalc.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import backbencers.nub.dailycostcalc.C0374R;

public class HelpFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Help");
        return inflater.inflate(C0374R.layout.fragment_help, container, false);
    }
}
