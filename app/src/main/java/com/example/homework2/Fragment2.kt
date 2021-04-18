package com.example.homework2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.`interface`.ActivityFragmentComunication

class Fragment2: Fragment(){
    var activityFragmentComunication: ActivityFragmentComunication?=null
    companion object{
        fun newInstance(): Fragment {
            return Fragment2()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        when(context){
            is ActivityFragmentComunication ->
                activityFragmentComunication=context
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment2, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityFragmentComunication?.setUpRecyclerView2();

    }
}