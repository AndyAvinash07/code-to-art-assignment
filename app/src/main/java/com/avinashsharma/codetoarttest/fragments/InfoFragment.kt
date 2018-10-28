package com.avinashsharma.codetoarttest.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avinashsharma.codetoarttest.MainActivity
import com.avinashsharma.codetoarttest.R

class InfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View = inflater.inflate(R.layout.info_fragment, container, false)
        retainInstance = true
        (activity as MainActivity).supportActionBar?.title = getString(R.string.information)
        return view
    }

}
