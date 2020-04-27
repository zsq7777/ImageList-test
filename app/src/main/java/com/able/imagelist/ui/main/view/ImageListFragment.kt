package com.able.imagelist.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.able.imagelist.R
import com.able.imagelist.ui.main.adapter.ImageListAdapter
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout

class ImageListPageFragment : Fragment() {

    private lateinit var mRv: RecyclerView
    private lateinit var mRefreshLayout: SmartRefreshLayout
    private val position = lazy { requireArguments().getInt("position") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_imagelist, container, false)
        mRv = view.findViewById(R.id.rv)
        mRefreshLayout = view.findViewById(R.id.refreshLayout)
        mRefreshLayout.setRefreshHeader(MaterialHeader(context))
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRv()
    }

    private fun initRv() {
        mRv.setHasFixedSize(true);
        mRv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        val adapter=ImageListAdapter()
        mRv.adapter = adapter;

//        adapter.replaceAll(getData());

    }


    companion object {

        fun create(position: Int): ImageListPageFragment {
            val ivListFragment = ImageListPageFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            ivListFragment.arguments = bundle
            return ivListFragment
        }
    }
}